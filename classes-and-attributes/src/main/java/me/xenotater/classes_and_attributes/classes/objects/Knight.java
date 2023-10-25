package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Knight extends GenericClass {
  public Knight() {
    disallowedInteracts = new String[]{"bow", "crossbow"};
    enchantsAllowed.put(ClassItemType.TRIDENT, false);
  }
}
