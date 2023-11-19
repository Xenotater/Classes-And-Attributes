package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Tough extends GenericAttribute {
  public Tough() {
    AttributeName attribute = AttributeName.TOUGH;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityDamageEvent) {
      EntityDamageEvent event = (EntityDamageEvent) e;
      event.setDamage(event.getDamage() * 0.70);
    }
  }
}
