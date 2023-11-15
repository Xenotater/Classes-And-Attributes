package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Picky extends GenericDiet {
  public Picky() {
    AttributeName attribute = AttributeName.PICKY;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.REGENERATION, -1, 0);
    allowedFoods = Arrays.asList(new String[]{"cooked_pork", "cooked_beef", "cooked_cod", "cooked_salmon", "cooked_mutton", "golden_apple", "golden_carrot"});
  }
}
