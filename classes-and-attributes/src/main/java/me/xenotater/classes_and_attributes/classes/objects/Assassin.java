package me.xenotater.classes_and_attributes.classes.objects;

import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Assassin extends GenericClass {
  public Assassin() {
    disallowedArmor = new String[]{"diamond", "netherite", "helmet", "chestplate"};
    disallowedInteracts = new String[]{"shield"};
    abilityDuration = 5000;
    abilityCooldown = 10000;
  }

  //Assassinate
  public void triggerPassive(Player p, Event e) {
    if (e instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
      if (p.isSneaking()) {
        event.setDamage(event.getDamage() * 3);
        notifyAbilityTriggered(p, "Assassinate");
      }
    }
  }

  //Cloak
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1, false, false, true));
      p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 3, false, false, true));
      passifyNearbyMobs(p);
      startAbilityCooldown(p);
      notifyAbilityTriggered(p, "Cloak");
    }
    else {
      notifyAbilityCooldown(p, "Cloak");
    }
  }

  private void passifyNearbyMobs(Player p) {
    List<Entity> nearbyEntities = p.getNearbyEntities(50, 50, 50);
    for (Entity e : nearbyEntities) {
      if (e instanceof Creature) {
        Creature mob = (Creature) e;
        if (mob.getTarget() != null && mob.getTarget().equals(p))
          mob.setTarget(null);
      }
    }
  }
}
