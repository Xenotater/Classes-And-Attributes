package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Wrathful extends GenericAttribute {
  public Wrathful() {
    AttributeName attribute = AttributeName.WRATHFUL;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
      if (event.getDamager() instanceof LivingEntity) {
        LivingEntity attacker = (LivingEntity) event.getDamager();
        attacker.damage(2);
      }
    }
  }
}
