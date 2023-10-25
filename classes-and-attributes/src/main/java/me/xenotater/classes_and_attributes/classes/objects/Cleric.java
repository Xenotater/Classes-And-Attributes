package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Cleric extends GenericClass {
  public Cleric() {
    enchantsAllowed.put(ClassItemType.ARMOR, false);
  }
}
