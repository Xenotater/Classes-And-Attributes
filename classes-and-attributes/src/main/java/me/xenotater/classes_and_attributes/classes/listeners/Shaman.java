package me.xenotater.classes_and_attributes.classes.listeners;

public class Shaman extends GenericClassListener {
  public Shaman() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    armorEnchantsAllowed = true;
  }
}
