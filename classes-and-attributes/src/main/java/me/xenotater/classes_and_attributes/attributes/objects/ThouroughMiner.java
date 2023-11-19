package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Collection;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.jeff_media.customblockdata.CustomBlockData;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import net.md_5.bungee.api.ChatMessageType;

public class ThouroughMiner extends GenericAttribute {
  public ThouroughMiner() {
    AttributeName attribute = AttributeName.THOUROUGH_MINER;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof BlockBreakEvent) {
      BlockBreakEvent event = (BlockBreakEvent) e;
      Block block = event.getBlock();
      Location loc = block.getLocation();
      Collection<ItemStack> drops = block.getDrops();
      Boolean isUnnatural = new CustomBlockData(block, Plugin.plugin).get(new NamespacedKey(Plugin.plugin, "placed_by_player"), PersistentDataType.BOOLEAN);

      if (isUnnatural == null && event.isDropItems()) {
        Integer rand = new Random().nextInt(2);
        if (rand == 1) {
          sendPlayerMessage(p, ChatMessageType.ACTION_BAR, ChatColor.GREEN + "Double Drops!");
          for (ItemStack drop : drops) {
            p.getWorld().dropItemNaturally(loc, drop.clone());
          }
          event.setExpToDrop(event.getExpToDrop() * 2);
        }
      }
    }
  }
}
