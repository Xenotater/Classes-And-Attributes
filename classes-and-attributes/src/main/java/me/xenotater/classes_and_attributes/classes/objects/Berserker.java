package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Berserker extends GenericClass {
  public Berserker() {
    disallowedArmor = new String[]{"diamond", "netherite"};
    disallowedWeapons = new String[]{"_sword"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
    enchantsAllowed.put(ClassItemType.BOW, false);
  }
}
