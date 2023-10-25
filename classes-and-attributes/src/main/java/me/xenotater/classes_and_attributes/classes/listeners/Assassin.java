package me.xenotater.classes_and_attributes.classes.listeners;

public class Assassin extends GenericClassListener {
  public Assassin() {
    disallowedArmor = new String[]{"diamond", "netherite", "helmet", "chestplate"};
    armorEnchantsAllowed = true;
  }
}
