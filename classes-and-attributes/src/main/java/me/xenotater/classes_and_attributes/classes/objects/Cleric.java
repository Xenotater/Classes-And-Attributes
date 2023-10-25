package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Cleric extends GenericClass {
  public Cleric() {
    disallowedWeapons = new String[]{"iron_sword", "iron_axe", "diamond_sword", "diamond_axe", "netherite_sword", "netherite_axe"};
    disallowedInteracts = new String[]{"bow", "crossbow"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
  }
}
