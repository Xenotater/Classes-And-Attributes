package me.xenotater.classes_and_attributes.attributes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.ListUtils;
import org.bukkit.ChatColor;

public enum AttributeName {
  CANNIBAL("Cannibal", AttributeType.DIET),
  CARNIVORE("Carnivore", AttributeType.DIET),
  EARTHY("Earthy", AttributeType.DIET),
  FORAGER("Forager", AttributeType.DIET),
  PESCITARIAN("Pescitarian", AttributeType.DIET),
  PICKY("Picky", AttributeType.DIET),
  SWEET_TOOTH("Sweet Tooth", AttributeType.DIET),
  VEGETARIAN("Vegetarian", AttributeType.DIET),
  CHEMICAL_INTEREST("Chemical Interest", AttributeType.POSITIVE),
  EXPERT_SMITH("Expert Smith", AttributeType.POSITIVE),
  NETHER_FRIEND("Friend Of The Nether", AttributeType.POSITIVE),
  SCAVENGER("Scavenger", AttributeType.POSITIVE),
  THOUROUGH_MINER("Thourough Miner", AttributeType.POSITIVE),
  TOUGH("Tough", AttributeType.POSITIVE),
  WELL_RESTED("Well Rested", AttributeType.POSITIVE),
  WRATHFUL("Wrathful", AttributeType.POSITIVE),
  ADDICTIVE("Addictive Personality", AttributeType.NEGATIVE),
  AQUAPHOBIA("Aquaphobia", AttributeType.NEGATIVE),
  BAD_TASTE("Bad Taste", AttributeType.NEGATIVE),
  BRONZE_AGE("Bronze Age", AttributeType.NEGATIVE),
  COMPULSIVE_MINER("Compulsive Miner", AttributeType.NEGATIVE),
  GOURMAND("Gourmand", AttributeType.NEGATIVE),
  MOTION_WEAKNESS("Motion Sensitivity", AttributeType.NEGATIVE),
  SUNLIGHT_WEAKNESS("Sunlight Sensitivity", AttributeType.NEGATIVE),
  THERMOPHOBIA("Thermophobia", AttributeType.NEGATIVE),
  CLUMSY("Clumsy", AttributeType.CURSE),
  DEAD_WEIGHT("Dead Weight", AttributeType.CURSE),
  HEMOPHILIA("Hemophilia", AttributeType.CURSE),
  PACIFIST("Pacifist", AttributeType.CURSE),
  STARVATION("Starvation", AttributeType.CURSE),
  VOIDTOUCHED("Voidtouched", AttributeType.CURSE);
  
  private final String name;
  private final AttributeType type;

  private AttributeName(String name, AttributeType type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public AttributeType getType() {
    return type;
  }

  public String getColoredName() {
    return type.getColor() + name;
  }

  public static AttributeName getValue(String name) {
    for (AttributeName a : values()) {
      if (a.name.equals(name))
        return a;
    }
    return null;
  }

  public static List<AttributeName> dietAttributes = Arrays.asList(new AttributeName[]{CANNIBAL, CARNIVORE, EARTHY, FORAGER, PESCITARIAN, PICKY, SWEET_TOOTH, VEGETARIAN});
  public static List<AttributeName> curseAttributes = Arrays.asList(new AttributeName[]{CLUMSY, DEAD_WEIGHT, HEMOPHILIA, PACIFIST, STARVATION, VOIDTOUCHED});
  public static List<AttributeName> regularAttributes = ListUtils.subtract(ListUtils.subtract(Arrays.asList(values()), dietAttributes), curseAttributes);
  
  public static AttributeName getRandom() {
    Integer rand = new Random().nextInt(regularAttributes.size());
    return regularAttributes.get(rand);
  }

  public static AttributeName getRandomDiet() {
      Integer rand = new Random().nextInt(dietAttributes.size());
      return dietAttributes.get(rand);
  }

  public static AttributeName getRandomCurse() {
      Integer rand = new Random().nextInt(curseAttributes.size());
      return curseAttributes.get(rand);
  }
}
