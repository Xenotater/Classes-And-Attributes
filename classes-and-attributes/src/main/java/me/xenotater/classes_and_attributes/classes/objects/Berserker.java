package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Berserker extends GenericClass {
  public Berserker() {
    disallowedArmor = new String[]{"diamond", "netherite"};
    disallowedWeapons = new String[]{"_sword"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
    enchantsAllowed.put(ClassItemType.BOW, false);
  }

  //Rage
  public void triggerPassive(Player p, Event e) {
    if (p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE) == null)
      notifyAbilityTriggered(p, "Rage");
    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1, false, false, true));
  }

  //Bloodlust
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, false, false, true));
      startAbilityCooldown(p);
    }
  }
}
