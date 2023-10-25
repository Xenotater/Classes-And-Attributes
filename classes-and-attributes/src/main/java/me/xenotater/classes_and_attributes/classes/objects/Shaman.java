package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Shaman extends GenericClass {
  public Shaman() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "iron_sword", "diamond_sword", "netherite_sword", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.BOW, false);
  }
}
