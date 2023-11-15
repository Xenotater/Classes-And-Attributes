package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Pescitarian extends GenericDiet {
  public Pescitarian() {
    AttributeName attribute = AttributeName.PESCITARIAN;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 0);
    allowedFoods = Arrays.asList(new String[]{"cod", "salmon", "tropical", "pufferfish", "kelp"});
  }
}
