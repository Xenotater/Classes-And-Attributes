package me.xenotater.classes_and_attributes.classes.listeners;

public class Pyromancer extends GenericClassListener {
  public Pyromancer() {
    disallowedArmor = new String[]{"iron", "diamond", "netherite"};
    armorEnchantsAllowed = true;
  }
}
