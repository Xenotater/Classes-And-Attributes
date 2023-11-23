package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Hemophilia extends GenericCurse {
  public Hemophilia() {
    AttributeName attribute = AttributeName.HEMOPHILIA;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityRegainHealthEvent) {
      EntityRegainHealthEvent event = (EntityRegainHealthEvent) e;
      event.setCancelled(true);
    }
  }
}
