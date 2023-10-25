package me.xenotater.classes_and_attributes.classes.objects;

public class Assassin extends GenericClass {
  public Assassin() {
    disallowedArmor = new String[]{"diamond", "netherite", "helmet", "chestplate"};
    disallowedInteracts = new String[]{"shield"};
  }
}
