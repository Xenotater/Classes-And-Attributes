package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Aquaphobia extends GenericAttribute {
  public Aquaphobia() {
    AttributeName attribute = AttributeName.AQUAPHOBIA;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof PlayerMoveEvent) {
      if (p.isInWater()) {
        GenericCurse curse = (GenericCurse) Plugin.plugin.attributes.get(Plugin.plugin.dataManager.getCurse(p.getUniqueId()));
        if (curse == null)
          breakForPlayer(p);
        else {
          sendActionBarMessage(p, ChatColor.RED + "Your curse timer won't start until you leave the water!");
          curse.restartTimer(p);
        }
      }
    }
  }
}
