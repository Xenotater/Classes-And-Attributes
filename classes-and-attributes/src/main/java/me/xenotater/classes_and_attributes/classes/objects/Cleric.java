package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Cleric extends GenericClass {
  public Cleric() {
    disallowedWeapons = new String[]{"iron_sword", "iron_axe", "diamond_sword", "diamond_axe", "netherite_sword", "netherite_axe"};
    disallowedInteracts = new String[]{"bow", "crossbow"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
    abilityDuration = 0;
    abilityCooldown = 7000;
  }

  //Radiance
  public void triggerPassive(Player p, Event e) {
    if (e instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
      ItemStack weapon = p.getInventory().getItemInMainHand();
      String weaponType = weapon.getType().name().toLowerCase();
      if (weaponType.contains("_axe") || weaponType.contains("_sword") || weaponType.contains("trident")) {
        Creature target = (Creature) event.getEntity();
        if (target.getPotionEffect(PotionEffectType.WEAKNESS) == null)
          notifyAbilityTriggered(p, "Radiance");
        target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, false, true, false));
        target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 0, false, true, false));
      }
    }
  }

  //Holy Light
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      ItemStack potion = new ItemStack(Material.SPLASH_POTION);
      PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
      potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2, false, true, false), true);
      potion.setItemMeta(potionMeta);
      ThrownPotion pot = p.launchProjectile(ThrownPotion.class);
      pot.setItem(potion);
      startAbilityCooldown(p);
      notifyAbilityTriggered(p, "Holy Light");
    }
    else {
      notifyAbilityCooldown(p, "Holy Light");
    }
  }
}
