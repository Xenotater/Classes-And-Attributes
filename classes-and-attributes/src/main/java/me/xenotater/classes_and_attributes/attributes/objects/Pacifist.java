package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Pacifist extends GenericCurse {
  public Pacifist() {
    AttributeName attribute = AttributeName.PACIFIST;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
      if (event.getEntity() instanceof Mob)
        p.damage(2);
    }
  }
}
