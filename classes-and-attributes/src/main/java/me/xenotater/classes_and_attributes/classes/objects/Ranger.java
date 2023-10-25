package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Ranger extends GenericClass {
  public Ranger() {
      disallowedArmor = new String[]{"diamond", "netherite"};
      disallowedWeapons = new String[]{"diamond_sword", "diamond_axe", "netherite_sword", "netherite_axe"};
      disallowedInteracts = new String[]{"shield"};
      enchantsAllowed.put(ClassItemType.ARMOR, false);
      enchantsAllowed.put(ClassItemType.WEAPON, false);
      enchantsAllowed.put(ClassItemType.TRIDENT, false);
  }
}
