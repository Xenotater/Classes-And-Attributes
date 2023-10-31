package me.xenotater.classes_and_attributes.classes.mage_spellcasting;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.xenotater.classes_and_attributes.common.ItemIcon;

public enum SpellInfo {
  BLESS,
  EMPOWER,
  FLY,
  WARDING,
  BANISH,
  CHARM,
  CONFUSION,
  SUSPEND,
  ENFLAME,
  VOLLEY,
  FLAMETHROW,
  ELECTRIFY,
  COMPANIONS;

  public String getName() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }

  public String getDescription() {
    switch(this) {
      case BLESS:
        return "Nearby players are healed and gain Absorbtion for 5s";
      case EMPOWER:
        return "Nearby players gain Strength 2 and Swiftness for 5s";
      case FLY:
        return "You gain Levitation for 10s";
      case WARDING:
        return "You gain Resistance 2 for 15s";
      case BANISH:
        return "A nearby monster dies";
      case CHARM:
        return "A nearby monster turns on its allies for 15s";
      case CONFUSION:
        return "Nearby enemies lose their target for 5s";
      case SUSPEND:
        return "Nearby enemies gain Levitation for 5s";
      case ENFLAME:
        return "Nearby enemies are set on fire for 10s";
      case VOLLEY:
        return "Fire 20 piercing arrows in rapid succession";
      case FLAMETHROW:
        return "Fire 10 Blaze fireballs";
      case ELECTRIFY:
        return "Summon lightning around you randomly for 5s";
      case COMPANIONS:
        return "Summon 3 Wolves to fight for you for 15s";
      default:
        return null;
    }
  }

  public ItemIcon getIcon() {
    switch(this) {
      case BLESS:
        return genIcon(Material.LIGHT);
      case EMPOWER:
        return genIcon(Material.BLAZE_POWDER);
      case FLY:
        return genIcon(Material.ELYTRA);
      case WARDING:
        return genIcon(Material.NETHERITE_CHESTPLATE);
      case BANISH:
        return genIcon(Material.WITHER_SKELETON_SKULL);
      case CHARM:
        return genIcon(Material.CARROT_ON_A_STICK);
      case CONFUSION:
        return genIcon(Material.COMPASS);
      case SUSPEND:
        return genIcon(Material.FEATHER);
      case ENFLAME:
        return genIcon(Material.FLINT_AND_STEEL);
      case VOLLEY:
        return genIcon(Material.ARROW);
      case FLAMETHROW:
        return genIcon(Material.FIRE_CHARGE);
      case ELECTRIFY:
        return genIcon(Material.LIGHTNING_ROD);
      case COMPANIONS:
        return genIcon(Material.BONE);
      default:
        return null;
    }
  }
  
  public static SpellInfo getRandom() {
    Integer rand = new Random().nextInt(values().length);
    return values()[rand];
  }

  private ItemIcon genIcon(Material mat) {
    return new ItemIcon(ChatColor.LIGHT_PURPLE + getName(), ChatColor.WHITE + getDescription(), mat);
  }
}
