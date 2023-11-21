package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class SunlightSensitivity extends GenericAttribute {
  public SunlightSensitivity() {
    AttributeName attribute = AttributeName.SUNLIGHT_WEAKNESS;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof PlayerMoveEvent) {
      if (isDay(p.getWorld())) {
        ItemStack helmet = p.getInventory().getHelmet();
        if (!isBlockAbove(p.getWorld(), p.getLocation()) && (helmet == null || helmet.getType() == Material.AIR)) {
          GenericCurse curse = (GenericCurse) Plugin.plugin.attributes.get(Plugin.plugin.dataManager.getCurse(p.getUniqueId()));
          if (curse == null)
            breakForPlayer(p);
          else {
            sendActionBarMessage(p, ChatColor.RED + "Your curse timer won't start until you get out of the sunlight!");
            curse.restartTimer(p);
          }
        }
      }
    }
  }

  private boolean isDay(World w) {
    long time = w.getTime();
    return time > 23500 || time < 12500;
  }

  private boolean isBlockAbove(World w, Location loc) {
    for (int i=loc.getBlockY(); i<=320; i++) {
      Block block = w.getBlockAt(loc.getBlockX(), i, loc.getBlockZ());
      if (block != null && block.getType().isOccluding()) {
        return true;
      }
    }
    return false;
  }
}
