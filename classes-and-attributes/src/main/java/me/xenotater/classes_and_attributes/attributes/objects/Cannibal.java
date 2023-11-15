package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Cannibal extends GenericDiet {
  public Cannibal() {
    AttributeName attribute = AttributeName.CANNIBAL;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.HEAL, 1, 1);
    allowedFoods = Arrays.asList(new String[]{"rotten_flesh", "spider_eye", "raw", "tropical", "pufferfish"});
  }
}
