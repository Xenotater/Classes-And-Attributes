package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Vegetarian extends GenericDiet {
  public Vegetarian() {
    AttributeName attribute = AttributeName.VEGETARIAN;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.JUMP, -1, 1);
    disallowedFoods = Arrays.asList(new String[]{"cod", "salmon", "tropical", "pufferfish", "beef", "mutton", "chicken", "pork"});
  }
}
