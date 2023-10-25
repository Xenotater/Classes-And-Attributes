package me.xenotater.classes_and_attributes.classes.listeners;

public class Berserker extends GenericClassListener {
  public Berserker() {
    disallowedArmor = new String[]{"diamond", "netherite"};
    armorEnchantsAllowed = false;
  }
}
