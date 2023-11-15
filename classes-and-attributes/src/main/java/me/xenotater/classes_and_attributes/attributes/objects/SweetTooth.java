package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class SweetTooth extends GenericDiet {
  public SweetTooth() {
    AttributeName attribute = AttributeName.SWEET_TOOTH;
    name = attribute.getName();
    type = attribute.getType();
    buff = new PotionEffect(PotionEffectType.SPEED, -1, 0);
    allowedFoods = Arrays.asList(new String[]{"apple", "cake", "berries", "fruit", "cookie", "pie", "honey", "melon"});
  }
}
