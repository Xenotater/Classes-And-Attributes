package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Earthy extends GenericDiet {
  public Earthy() {
    AttributeName attribute = AttributeName.EARTHY;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 0);
    allowedFoods = Arrays.asList(new String[]{"potato", "carrot", "bread", "beetroot"});
  }
}
