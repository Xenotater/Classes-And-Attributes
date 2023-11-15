package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Forager extends GenericDiet {
  public Forager() {
    AttributeName attribute = AttributeName.FORAGER;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.FAST_DIGGING, -1, 0);
    allowedFoods = Arrays.asList(new String[]{"berries", "stew", "rabbit", "chicken", "apple", "cod", "salmon", "tropical"});
  }
}
