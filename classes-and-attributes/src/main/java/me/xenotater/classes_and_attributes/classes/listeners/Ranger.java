package me.xenotater.classes_and_attributes.classes.listeners;

public class Ranger extends GenericClassListener {
  public Ranger() {
      disallowedArmor = new String[]{"diamond", "netherite"};
      armorEnchantsAllowed = false;
  }
}
