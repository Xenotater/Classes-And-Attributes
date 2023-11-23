package me.xenotater.classes_and_attributes.classes.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Knight extends GenericClass {
  private List<PotionEffectType> negativeEffects = new ArrayList<>();
  
  public Knight() {
    disallowedInteracts = new String[]{"bow", "crossbow"};
    enchantsAllowed.put(ClassItemType.TRIDENT, false);
    abilityDuration = 10000;
    abilityCooldown = 25000;
    setNegativeEffects();
  }

  //Hardy
  public void triggerPassive(Player p, Event e) {
    if (e instanceof EntityPotionEffectEvent) {
      EntityPotionEffectEvent event = (EntityPotionEffectEvent) e;
      if (event.getNewEffect() != null) {
        PotionEffectType effect = event.getNewEffect().getType();
        if (negativeEffects.contains(effect)) {
          if (isCurseEffect(p, effect))
            return;
          event.setCancelled(true);
          notifyAbilityTriggered(p, "Hardy");
        }
      }
    }
  }

  //Adrenaline Rush
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2, false, false, true));
      startAbilityCooldown(p);
      notifyAbilityTriggered(p, "Adrenaline Rush");
    }
    else {
      notifyAbilityCooldown(p, "Adrenaline Rush");
    }
  }

  private void setNegativeEffects() {
    negativeEffects.add(PotionEffectType.SLOW);
    negativeEffects.add(PotionEffectType.SLOW_DIGGING);
    negativeEffects.add(PotionEffectType.HARM);
    negativeEffects.add(PotionEffectType.CONFUSION);
    negativeEffects.add(PotionEffectType.BLINDNESS);
    negativeEffects.add(PotionEffectType.HUNGER);
    negativeEffects.add(PotionEffectType.WEAKNESS);
    negativeEffects.add(PotionEffectType.POISON);
    negativeEffects.add(PotionEffectType.WITHER);
    negativeEffects.add(PotionEffectType.LEVITATION);
    negativeEffects.add(PotionEffectType.UNLUCK);
    negativeEffects.add(PotionEffectType.DARKNESS);
  }

  private boolean isCurseEffect(Player p, PotionEffectType effect) {
    AttributeName curse = Plugin.plugin.dataManager.getCurse(p.getUniqueId());
    return (effect.equals(PotionEffectType.HUNGER) && curse == AttributeName.STARVATION) ||
    (effect.equals(PotionEffectType.DARKNESS) && curse == AttributeName.VOIDTOUCHED) ||
    (effect.equals(PotionEffectType.CONFUSION) && curse == AttributeName.DIZZY);
  }
}
