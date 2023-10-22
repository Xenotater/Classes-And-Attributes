package me.xenotater.classes_and_attributes.classes;

import java.util.Random;

public enum ClassName {
  ASSASSIN("Assassin"),
  BERSERKER("Berserker"),
  CLERIC("Cleric"),
  KNIGHT("Knight"),
  MAGE("Mage"),
  PYROMANCER("Pyromancer"),
  SHAMAN("Shaman"),
  RANGER("Ranger");
  
  private final String name;

  private ClassName(String name) {this.name = name;}

  public String getName() {return this.name;}

  public static ClassName getValue(String name) {
    for (ClassName c : values()) {
      if (c.name.equals(name))
        return c;
    }
    return null;
  }

  public static ClassName getRandom() {
    Integer rand = new Random().nextInt(values().length);
    return values()[rand];
  }
}
