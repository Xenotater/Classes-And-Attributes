package me.xenotater.classes_and_attributes.classes.listeners;

public class Mage extends GenericClassListener {
  public Mage() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    armorEnchantsAllowed = false;
  }
}
