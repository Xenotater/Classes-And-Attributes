package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;

import com.jeff_media.customblockdata.CustomBlockData;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class CompulsiveMiner extends GenericAttribute {
  Map<UUID, Location> mining = new HashMap<>();

  public CompulsiveMiner() {
    AttributeName attribute = AttributeName.COMPULSIVE_MINER;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof BlockBreakEvent) {
      BlockBreakEvent event = (BlockBreakEvent) e;
      Block block = event.getBlock();
      Boolean isUnnatural = new CustomBlockData(block, Plugin.plugin).get(new NamespacedKey(Plugin.plugin, "placed_by_player"), PersistentDataType.BOOLEAN);
      if (isOre(block) && isUnnatural == null) {
        Location blockLoc = block.getLocation();
        int oreCount = countNearbyOre(p.getWorld(), blockLoc);
        sendActionBarMessage(p, "" + ChatColor.GOLD + oreCount + " ores within 3 blocks of this one");
        if (oreCount > 0)
          mining.put(p.getUniqueId(), blockLoc);
        else
          mining.remove(p.getUniqueId());
      }
    }
    else if (e instanceof PlayerMoveEvent) {
      if (mining.get(p.getUniqueId()) != null) {
        Location playerLoc = p.getLocation();
        Location lastOreLoc = mining.get(p.getUniqueId());
        if (playerLoc.distance(lastOreLoc) > 10) {
          if (countNearbyOre(p.getWorld(), lastOreLoc) > 0)
            breakForPlayer(p);
          mining.remove(p.getUniqueId());
        }
      }
    }
  }

  private boolean isOre(Block b) {
    return b.getType().name().toLowerCase().contains("ore");
  }

  private int countNearbyOre(World w, Location loc) {
    int oreCount = 0;
    for (int i=-3; i<=3; i++) {
      for (int j=-3; j<=3; j++) {
        for (int k=-3; k<=3; k++) {
          Block block = w.getBlockAt(loc.getBlockX() + i, loc.getBlockY() + j, loc.getBlockZ() + k);
          if (isOre(block) && !block.getLocation().equals(loc))
            oreCount++;
        }
      }
    }
    return oreCount;
  }
}
