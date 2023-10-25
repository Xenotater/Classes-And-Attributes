package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Ranger extends GenericClass {
  public Ranger() {
      disallowedArmor = new String[]{"diamond", "netherite"};
      enchantsAllowed.put(ClassItemType.ARMOR, false);
  }
}
