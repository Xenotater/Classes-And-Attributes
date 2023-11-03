package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Ranger extends GenericClass {
  public Ranger() {
      disallowedArmor = new String[]{"diamond", "netherite"};
      disallowedWeapons = new String[]{"diamond_sword", "diamond_axe", "netherite_sword", "netherite_axe"};
      disallowedInteracts = new String[]{"shield"};
      enchantsAllowed.put(ClassItemType.ARMOR, false);
      enchantsAllowed.put(ClassItemType.WEAPON, false);
      enchantsAllowed.put(ClassItemType.TRIDENT, false);
      abilityDuration = 0;
      abilityCooldown = 15000;
  }

  //Stride
  public void triggerPassive(Player p, Event e) {
    setEffect(p, new PotionEffect(PotionEffectType.SPEED, -1, 1, false, false, true));
    setEffect(p, new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 0, false, false, true));
    notifyAbilityTriggered(p, "Stride");
  }

  //Teleport
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      p.launchProjectile(EnderPearl.class);
      startAbilityCooldown(p);
      notifyAbilityTriggered(p, "Teleport");
    }
    else {
      notifyAbilityCooldown(p, "Teleport");
    }
  }

  public void disablePassive(Player p) {
    p.removePotionEffect(PotionEffectType.SPEED);
    p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
  }

  private void setEffect(Player p, PotionEffect effect) {
    if (p.getPotionEffect(effect.getType()) == null)
      p.addPotionEffect(effect);
  }
}
