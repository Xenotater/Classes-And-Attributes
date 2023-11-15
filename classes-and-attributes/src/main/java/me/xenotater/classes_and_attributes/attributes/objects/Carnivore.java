package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Carnivore extends GenericDiet {
  public Carnivore() {
    AttributeName attribute = AttributeName.CARNIVORE;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 0);
    allowedFoods = Arrays.asList(new String[]{"beef", "chicken", "mutton", "rabbit", "rotten_flesh"});
  }
}
