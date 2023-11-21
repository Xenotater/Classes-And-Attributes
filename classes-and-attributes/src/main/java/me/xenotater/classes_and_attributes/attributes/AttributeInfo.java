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
      case CANNIBAL:
        return Material.ROTTEN_FLESH;
      case CARNIVORE:
        return Material.BEEF;
      case EARTHY:
        return Material.BEETROOT;
      case FORAGER:
        return Material.BROWN_MUSHROOM;
      case PESCITARIAN:
        return Material.COD;
      case PICKY:
        return Material.GOLDEN_APPLE;
      case SWEET_TOOTH:
        return Material.CAKE;
      case VEGETARIAN:
        return Material.CARROT;
      case CHEMICAL_INTEREST:
        return Material.POTION;
      case EXPERT_SMITH:
        return Material.ANVIL;
      case NETHER_FRIEND:
        return Material.NETHERRACK;
      case SCAVENGER:
        return Material.RABBIT_HIDE;
      case THOUROUGH_MINER:
        return Material.DIAMOND_PICKAXE;
      case TOUGH:
        return Material.NETHERITE_CHESTPLATE;
      case WELL_RESTED:
        return Material.RED_BED;
      case WRATHFUL:
        return Material.IRON_SWORD;
      case ADDICTIVE:
        return Material.SUGAR;
      case AQUAPHOBIA:
        return Material.WATER_BUCKET;
      case BAD_TASTE:
        return Material.BIRCH_WOOD;
      case BRONZE_AGE:
        return Material.COPPER_INGOT;
      case COMPULSIVE_MINER:
        return Material.RAW_GOLD;
      case GOURMAND:
        return Material.GOLDEN_CARROT;
      case MOTION_WEAKNESS:
        return Material.MINECART;
      case SUNLIGHT_WEAKNESS:
        return Material.TINTED_GLASS;
      case THERMOPHOBIA:
        return Material.FLINT_AND_STEEL;
      case DEAD_WEIGHT:
        return Material.IRON_BLOCK;
      case HEMOPHILIA:
        return Material.REDSTONE;
      case PACIFIST:
        return Material.SHIELD;
      case STARVATION:
        return Material.POISONOUS_POTATO;
      case VOIDTOUCHED:
        return Material.ECHO_SHARD;
      default:
        return null;
    }
  }

  private void setLore(AttributeName attribute) {
    switch(attribute) {
      case CANNIBAL:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Flesh...\"");
        addLore(ChatColor.WHITE + "Your diet includes uncooked meats, Rotten Flesh, and Spider Eyes.");
        addLore(ChatColor.WHITE + "You become immune to Hunger and Poison when you follow this diet.");
        addLore(ChatColor.WHITE + "Consuming foods in your diet grants Instant Health II.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case CARNIVORE:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I dunno, I just grow all my food at");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "home, how I've always done it.\"");
        addLore(ChatColor.WHITE + "Your diet includes potatoes, carrots, beetroots, and bread.");
        addLore(ChatColor.WHITE + "You gain Strength I when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case EARTHY:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"If it's not the muscle tissue of another");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "once living being, I don't want it.\"");
        addLore(ChatColor.WHITE + "Your diet includes meats and Rotten Flesh, fish not included.");
        addLore(ChatColor.WHITE + "You gain Resistance I when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case FORAGER:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I just eat what I can find.");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "Fish, berries, bugs, small game.\"");
        addLore(ChatColor.WHITE + "Your diet includes berries, rabbit, chicken, fish,");
        addLore(ChatColor.WHITE + "apples, and Mushroom and Suspicious Stews");
        addLore(ChatColor.WHITE + "You gain Haste I when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case PESCITARIAN:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I'd rather be fishing.\"");
        addLore(ChatColor.WHITE + "Your diet includes fish and Dried Kelp.");
        addLore(ChatColor.WHITE + "You gain Night Vision when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case PICKY:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Only the best.\"");
        addLore(ChatColor.WHITE + "Your diet includes cooked pork, beef, fish, and mutton,");
        addLore(ChatColor.WHITE + "as well as Golden Apples and Golden Carrots.");
        addLore(ChatColor.WHITE + "You gain Regeneration I when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case SWEET_TOOTH:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Okay, but hear me out… Diamond dentures!\"");
        addLore(ChatColor.WHITE + "Your diet includes sweet foods:");
        addLore(ChatColor.WHITE + "Fruit, Berries, Cookies, Melons, Pumpkin Pie, and Honey.");
        addLore(ChatColor.WHITE + "You gain Swiftness 1 when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case VEGETARIAN:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"My iron is low but my spirit is strong.\"");
        addLore(ChatColor.WHITE + "Your diet exludes meat and fish.");
        addLore(ChatColor.WHITE + "You gain Jump Boost 2 when you follow this diet.");
        addLore(ChatColor.YELLOW + "Diet Attribute");
        break;
      case CHEMICAL_INTEREST:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Potion Seller...\"");
        addLore(ChatColor.WHITE + "When you gain an effect from a Potion, it lasts twice as long.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case EXPERT_SMITH:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"You'd be shocked at what you can do");
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "with just duct tape and a welding torch.\"");
        addLore(ChatColor.WHITE + "Repairing an item using an Anvil does not damage the");
        addLore(ChatColor.WHITE + "Anvil and won't cost more than 10 levels.");
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
      case TOUGH:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Eh, I'll shrug it off.\"");
        addLore(ChatColor.WHITE + "You take 30% less damage from all sources.");
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
        addLore(ChatColor.WHITE + "You are considered to have Thorns 2 at all times.");
        addLore(ChatColor.WHITE + "This stacks with the actual Thorns Enchantment.");
        addLore(ChatColor.GREEN + "Positive Attribute");
        break;
      case ADDICTIVE:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"C'mon, just one more...\"");
        addLore(ChatColor.WHITE + "When you gain an effect from a Potion or Golden Apple,");
        addLore(ChatColor.WHITE + "it has a high chance of imposing a Curse on you.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case AQUAPHOBIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Have you SEEN what lives down there?!\"");
        addLore(ChatColor.WHITE + "You cannot come into contact with water.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case BRONZE_AGE:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Nah, never liked Iron much. Why change what works?\"");
        addLore(ChatColor.WHITE + "You may only use tools made out of Copper.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case BAD_TASTE:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I like it so much, I think I'll use it for everything!\"");
        addLore(ChatColor.WHITE + "The only type of wood you can build with is Birch.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case COMPULSIVE_MINER:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"We're gonna be rich!\"");
        addLore(ChatColor.WHITE + "You must mine any ores within 3 blocks of the last ore you mined");
        addLore(ChatColor.WHITE + "before moving on to a new location.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case GOURMAND:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I like what I like, okay?\"");
        addLore(ChatColor.WHITE + "You cannot eat foods outside of your diet.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case SUNLIGHT_WEAKNESS:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I am not beating the Vampire allegations.\"");
        addLore(ChatColor.WHITE + "You may only use tools made out of Copper.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case MOTION_WEAKNESS:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Please, before I throw up in the minecart.\"");
        addLore(ChatColor.WHITE + "You may not ride Boats, Minecarts, Pigs, Horses, or Striders.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case THERMOPHOBIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"I got burned once… never again.\"");
        addLore(ChatColor.WHITE + "You cannot use Furnaces, Campfires,");
        addLore(ChatColor.WHITE + "Flint and Steel, or Fire Charges.");
        addLore(ChatColor.RED + "Negative Attribute");
        break;
      case DEAD_WEIGHT:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"No further... I can't...\"");
        addLore(ChatColor.WHITE + "You cannot swim in Water or Lava, instead sinking to the bottom.");
        addLore(ChatColor.RED + "Curse Attribute");
        break;
      case HEMOPHILIA:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Guys... The bleeding isn't stopping!\"");
        addLore(ChatColor.WHITE + "You heal 50% slower.");
        addLore(ChatColor.RED + "Curse Attribute");
        break;
      case PACIFIST:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"This is going to hurt me more than it hurts you.\"");
        addLore(ChatColor.WHITE + "All mobs are considered to have Thorns 2.");
        addLore(ChatColor.RED + "Curse Attribute");
        break;
      case STARVATION:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"So... Hungry...\"");
        addLore(ChatColor.WHITE + "You gain Hunger I, even if you would normally be immune.");
        addLore(ChatColor.DARK_PURPLE + "Curse Attribute");
        break;
      case VOIDTOUCHED:
        addLore("" + ChatColor.GRAY + ChatColor.ITALIC + "\"Where... Where is everyone?!\"");
        addLore(ChatColor.WHITE + "You gain Darkness I, even if you would normally be immune.");
        addLore(ChatColor.DARK_PURPLE + "Curse Attribute");
        break;
    }
    return;
  }
}