package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class WellRested extends GenericAttribute {
  public WellRested() {
    AttributeName attribute = AttributeName.WELL_RESTED;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityTargetEvent) {
      EntityTargetEvent event = (EntityTargetEvent) e;
      if (event.getEntity() instanceof Phantom) {
        event.getEntity().remove();
        Plugin.plugin.LOGGER.info("Prevented phantom spawn");
      }
    }
  }
}
