package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Starvation extends GenericCurse {
  public Starvation() {
    AttributeName attribute = AttributeName.STARVATION;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public boolean addForPlayer(Player p) {
    boolean success = super.addForPlayer(p);
    if (success)
      triggerEffect(p, null);
    return success;
  }

  @Override
  public boolean removeForPlayer(Player p) {
    boolean success = super.removeForPlayer(p);
    if (success)
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, new Runnable() {public void run() {p.removePotionEffect(PotionEffectType.HUNGER);}}, 1);
    return success;
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e == null) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 60, 1));
    }
    else if (e instanceof EntityPotionEffectEvent) {
      EntityPotionEffectEvent event = (EntityPotionEffectEvent) e;
      Action action = event.getAction();
      
      if (action != Action.ADDED) {
        if (event.getOldEffect().getType().equals(PotionEffectType.HUNGER)) 
          event.setCancelled(true);
      }
    }
  }
}
