package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class AddictivePersonality extends GenericAttribute {
  public AddictivePersonality() {
    AttributeName attribute = AttributeName.ADDICTIVE;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityPotionEffectEvent) {
      EntityPotionEffectEvent event = (EntityPotionEffectEvent) e;
      PotionEffect effect = event.getNewEffect();
      Cause cause = event.getCause();
      if (effect != null) {
        if (cause == Cause.AREA_EFFECT_CLOUD || cause == Cause.POTION_DRINK || cause == Cause.POTION_SPLASH || (cause == Cause.FOOD && effect.getType().equals(PotionEffectType.ABSORPTION)))
          curseToss(p);
      }
    }
  }

  private void curseToss(Player p) {
    Integer rand = new Random().nextInt(2);
    if (rand == 1) {
      p.sendMessage(ChatColor.RED + "Your addiction cursed you!");
      cursePlayer(p);
    }
  }
}
