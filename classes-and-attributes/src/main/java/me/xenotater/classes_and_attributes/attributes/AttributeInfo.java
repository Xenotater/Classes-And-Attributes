package me.xenotater.classes_and_attributes.attributes;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.xenotater.classes_and_attributes.common.ItemIcon;

public class AttributeInfo extends ItemIcon {
  public AttributeInfo(AttributeName attribute) {
    super(attribute.getColoredName(), getIconMaterial(attribute));
    setLore(attribute);
  }

  private static Material getIconMaterial(AttributeName attribute) {
    switch(attribute) {
      case CARNIVORE:
        return Material.BEEF;
      case PESCITARIAN:
        return Material.COD;
      case SWEET_TOOTH:
        return Material.CAKE;
      case VEGETARIAN:
        return Material.CARROT;
      case EXPERT_SMITH:
        return Material.ANVIL;
      case NETHER_FRIEND:
        return Material.NETHERRACK;
      case SCAVENGER:
        return Material.RABBIT_HIDE;
      case THOUROUGH_MINER:
        return Material.DIAMOND_PICKAXE;
      case WELL_RESTED:
        return Material.RED_BED;
      case WRATHFUL:
        return Material.IRON_SWORD;
      case AQUAPHOBIA:
        return Material.WATER_BUCKET;
      case BRONZE_AGE:
        return Material.COPPER_INGOT;
      case MONOPHOBIA:
        return Material.MUSIC_DISC_11;
      case SUNLIGHT_WEAKNESS:
        return Material.TINTED_GLASS;
      case THERMOPHOBIA:
        return Material.FLINT_AND_STEEL;
      case CLUMSY:
        return Material.EGG;
      case HEMOPHILIA:
        return Material.REDSTONE;
      case PACIFIST:
        return Material.SHIELD;
      default:
        return null;
    }
  }

  private void setLore(AttributeName attribute) {
    switch(attribute) {
      case CARNIVORE:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"If it's not the muscle tissue of another");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "once living being, I don't want it.\"");
        addLore(ChatColor.WHITE + "You can only eat meats and Rotten Flesh, fish not included.");
        addLore(ChatColor.WHITE + "You gain Strength 1 when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case PESCITARIAN:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I'd rather be fishing.\"");
        addLore(ChatColor.WHITE + "You can only eat fish and Dried Kelp.");
        addLore(ChatColor.WHITE + "You gain Night Vision when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case SWEET_TOOTH:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Okay, but hear me out… Diamond dentures!\"");
        addLore(ChatColor.WHITE + "You can only eat sweet foods:");
        addLore(ChatColor.WHITE + "Fruit, Berries, Cookies, Melons, Pumpkin Pie, and Honey.");
        addLore(ChatColor.WHITE + "You gain Swiftness 1 when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case VEGETARIAN:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"My iron is low but my spirit is strong.\"");
        addLore(ChatColor.WHITE + "You cannot eat meat or fish.");
        addLore(ChatColor.WHITE + "You gain Jump Boost 2 when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case EXPERT_SMITH:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"You'd be shocked at what you can do");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "with just duct tape and a welding torch.\"");
        addLore(ChatColor.WHITE + "Repairing an item using an Anvil does not damage the");
        addLore(ChatColor.WHITE + "Anvil or increase the item's repair price.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case NETHER_FRIEND:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"How do you do, fellow demons?\"");
        addLore(ChatColor.WHITE + "All hostile Nether mobs become neutral towards you.");
        addLore(ChatColor.WHITE + "Also, hitting a Zombified Pigman will not cause others to attack you.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case SCAVENGER:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"What's wrong? Y'all afraid of a little chopped liver?\"");
        addLore(ChatColor.WHITE + "When you kill a mob, you have a 50% chance of getting double item drops.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case THOUROUGH_MINER:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Ooh, shiny.\"");
        addLore(ChatColor.WHITE + "When you mine a naturally generated block,");
        addLore(ChatColor.WHITE + "you have a 50% chance of getting double drops.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case WELL_RESTED:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Yeah, turns out all I needed to stop the nightmares");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "was a good pillow. Who'd have thought?\"");
        addLore(ChatColor.WHITE + "Phantoms do not spawn around you.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case WRATHFUL:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"You're not getting away that easily.\"");
        addLore(ChatColor.WHITE + "You are considered to have Thorns 2.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case AQUAPHOBIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Have you SEEN what lives down there?!\"");
        addLore(ChatColor.WHITE + "You cannot come into contact with water. ");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case BRONZE_AGE:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Nah, never liked Iron much. Why change what works?\"");
        addLore(ChatColor.WHITE + "You may only use tools made out of Copper.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case MONOPHOBIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"G-Guys?");
        addLore(ChatColor.WHITE + "You may not be more than 50 blocks away from another player.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case SUNLIGHT_WEAKNESS:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I am not beating the Vampire allegations.\"");
        addLore(ChatColor.WHITE + "You may only use tools made out of Copper.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case THERMOPHOBIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I got burned once… never again.\"");
        addLore(ChatColor.WHITE + "You cannot use Furnaces, Campfires,");
        addLore(ChatColor.WHITE + "Flint and Steel, or Fire Charges.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case CLUMSY:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Placeholder\"");
        addLore(ChatColor.WHITE + "You cannot jump unless you are sneaking.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case HEMOPHILIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Placeholder\"");
        addLore(ChatColor.WHITE + "You heal 50% slower.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case PACIFIST:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Placeholder\"");
        addLore(ChatColor.WHITE + "All mobs are considered to have Thorns 2.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
    }
    return;
  }
}