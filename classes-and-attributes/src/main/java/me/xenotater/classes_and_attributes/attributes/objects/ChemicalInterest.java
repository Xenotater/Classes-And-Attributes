package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;
import org.bukkit.potion.PotionEffect;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class ChemicalInterest extends GenericAttribute {
  public ChemicalInterest() {
    AttributeName attribute = AttributeName.CHEMICAL_INTEREST;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityPotionEffectEvent) {
      EntityPotionEffectEvent event = (EntityPotionEffectEvent) e;
      Cause cause = event.getCause();
      if (cause == Cause.AREA_EFFECT_CLOUD || cause == Cause.POTION_DRINK || cause == Cause.POTION_SPLASH) {
        if (event.getNewEffect() != null) {
          if (event.getOldEffect() != null)
            p.removePotionEffect(event.getOldEffect().getType());
          PotionEffect currentEffect = event.getNewEffect();
          PotionEffect newEffect = new PotionEffect(currentEffect.getType(), currentEffect.getDuration() * 2, currentEffect.getAmplifier());
          p.addPotionEffect(newEffect);
          event.setCancelled(true);
        }
      }
    }
  }
}
