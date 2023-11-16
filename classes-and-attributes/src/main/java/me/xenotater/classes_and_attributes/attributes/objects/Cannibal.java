package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Cannibal extends GenericDiet {
  public Cannibal() {
    AttributeName attribute = AttributeName.CANNIBAL;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.HEAL, 1, 1);
    allowedFoods = Arrays.asList(new String[]{"rotten_flesh", "spider_eye", "beef", "mutton", "chicken", "rabbit", "pork", "cod", "salmon", "tropical", "pufferfish"});
    disallowedFoods = Arrays.asList(new String[]{"cooked"});
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityPotionEffectEvent) {
      EntityPotionEffectEvent event = (EntityPotionEffectEvent) e;
      if (event.getNewEffect() != null) {
        PotionEffectType type = event.getNewEffect().getType();
        AttributeName curse = Plugin.plugin.dataManager.getCurse(p.getUniqueId());
        if (type.equals(PotionEffectType.POISON) && !isOnCooldown(p))
          event.setCancelled(true);
        else if (type.equals(PotionEffectType.HUNGER) && !isOnCooldown(p) && curse != AttributeName.STARVATION)
          event.setCancelled(true);
      }
    }
  }
}
