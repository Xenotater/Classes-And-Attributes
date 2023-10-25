package me.xenotater.classes_and_attributes.classes.objects;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Pyromancer extends GenericClass {
  public Pyromancer() {
    disallowedArmor = new String[]{"iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.BOW, false);
  }
}
