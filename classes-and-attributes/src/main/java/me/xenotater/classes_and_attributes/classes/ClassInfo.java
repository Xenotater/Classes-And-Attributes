package me.xenotater.classes_and_attributes.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.xenotater.classes_and_attributes.common.ItemIcon;

public class ClassInfo {
  ItemIcon info;
  ItemIcon armor;
  ItemIcon weapons;
  ItemIcon passive;
  ItemIcon active;

  public ClassInfo(ClassName name) {
    setClassInfo(name);
    setArmorInfo(name);
    setWeaponsInfo(name);
    setPassiveInfo(name);
    setActiveInfo(name);
  }

  private void setClassInfo(ClassName name) {
    switch(name) {
      case ASSASSIN:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "cf76c50d0672ca53fbb68c6ac7d1ef4796dd359173f07c8dd40056c5e2e2f132");
        info.addLore(ChatColor.LIGHT_PURPLE + "Someone who specializes in close combat and stealth.");
        info.addLore(ChatColor.LIGHT_PURPLE + "They must be careful with their positioning due to how frail they are.");
        info.addLore(ChatColor.LIGHT_PURPLE + "Deals additional damage when crouching,");
        info.addLore(ChatColor.LIGHT_PURPLE + "and can become invisible for a short time.");
        break;
      case BERSERKER:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "c09741fca109c4cb0b5efaa0634616503051a199e1d44e4e1149ede0bdc49c8a");
        info.addLore(ChatColor.LIGHT_PURPLE + "A warrior focused on speed and brute strength.");
        info.addLore(ChatColor.LIGHT_PURPLE + "Becomes more powerful when hit, and has a dash ability. ");
        break;
      case CLERIC:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "66d1bfb4fae91a0cd27bd62b68aff009a502012206605882ec02cf5ca9045d1c");
        info.addLore(ChatColor.LIGHT_PURPLE + "A paragon of divinity who can debuff enemies in melee combat,");
        info.addLore(ChatColor.LIGHT_PURPLE + "as well as heal and smite undead through a potent splash potion.");
        break;
      case KNIGHT:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "7d4d496b1da07536c94c13124a5833ebe0c5382c8a336aad846c681a28d93563");
        info.addLore(ChatColor.LIGHT_PURPLE + "A warrior focused on being as defensive as possible.");
        info.addLore(ChatColor.LIGHT_PURPLE + "They are immune to negative status effects,");
        info.addLore(ChatColor.LIGHT_PURPLE + "and can activate a short burst of massive damage resistance.");
        break;
      case MAGE:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "edd8fecdba1985baac4df9c3f13e4321653f6ca1e63b7a0ff78dcd73e3f0d5c8");
        info.addLore(ChatColor.LIGHT_PURPLE + "A master of the Arcane whose use of potions");
        info.addLore(ChatColor.LIGHT_PURPLE + "assists them and their allies greatly.");
        info.addLore(ChatColor.LIGHT_PURPLE + "They also have the ability to cast spells");
        info.addLore(ChatColor.LIGHT_PURPLE + "which grant additional, random effects.");
        break;
      case PYROMANCER:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "d8985f82405ccac8f5ae1b9f603142563c0e51f4b84a8e3958d53b993ecb18d3");
        info.addLore(ChatColor.LIGHT_PURPLE + "A combatant with the ability to shrug");
        info.addLore(ChatColor.LIGHT_PURPLE + "off explosions and fire with ease");
        info.addLore(ChatColor.LIGHT_PURPLE + "and cast a mighty fireball.");
        break;
      case SHAMAN:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "22a91fde129a8f132747fc051889d4189a1d0511e7266d3aed57882017ec093d");
        info.addLore(ChatColor.LIGHT_PURPLE + "A spiritually guided warrior who cripples enemies they hit,");
        info.addLore(ChatColor.LIGHT_PURPLE + "and may summon fairy allies to assist them.");
        break;
      case RANGER:
        info = new ItemIcon("" + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "e93ee5bb0c7facca0f3dfe09430c5b84a90e6588d0a1099da85b6eaeb8958f9a");
        info.addLore(ChatColor.LIGHT_PURPLE + "A master of the wild and mobility.");
        info.addLore(ChatColor.LIGHT_PURPLE + "They move quickly over land and sea and fall gracefully.");
        info.addLore(ChatColor.LIGHT_PURPLE + "They also have access to a short range teleport.");
    }
  }

  private void setArmorInfo(ClassName name) {
    switch(name) {
      case ASSASSIN:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.LEATHER_BOOTS);
        armor.addLore(ChatColor.GREEN + "Leather or Iron");
        armor.addLore(ChatColor.RED + "Leggings and Boots only.");
        break;
      case BERSERKER:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.CHAINMAIL_CHESTPLATE);
        armor.addLore(ChatColor.GREEN + "Leather, Gold, Chainmail, or Iron");
        armor.addLore(ChatColor.RED + "No Enchants");
        break;
      case CLERIC:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.DIAMOND_CHESTPLATE);
        armor.addLore(ChatColor.GREEN + "Any Armor");
        armor.addLore(ChatColor.RED + "No Enchants");
        break;
      case KNIGHT:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.IRON_HELMET);
        armor.addLore(ChatColor.GREEN + "Any Armor");
        break;
      case MAGE:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.GOLDEN_HELMET);
        armor.addLore(ChatColor.GREEN + "Leather or Gold");
        armor.addLore(ChatColor.RED + "No Enchants.");
        break;
      case PYROMANCER:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.CHAINMAIL_LEGGINGS);
        armor.addLore(ChatColor.GREEN + "Leather, Gold, or Chainmail");
        break;
      case SHAMAN:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.LEATHER_CHESTPLATE);
        armor.addLore(ChatColor.GREEN + "Leather or Gold");
        break;
      case RANGER:
        armor = new ItemIcon(ChatColor.YELLOW + "Armor", Material.LEATHER_HELMET);
        armor.addLore(ChatColor.GREEN + "Leather, Gold, Chainmail, or Iron");
        armor.addLore(ChatColor.RED + "No Enchants");
        break;
    }
  }

  private void setWeaponsInfo(ClassName name) {
    switch(name) {
      case ASSASSIN:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.CROSSBOW);
        weapons.addLore(ChatColor.GREEN + "Any Weapons");
        weapons.addLore(ChatColor.RED + "No Shields");
        break;
      case BERSERKER:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.DIAMOND_AXE);
        weapons.addLore(ChatColor.GREEN + "Axes, Tridents, Bows, Crossbows, Shields");
        weapons.addLore(ChatColor.RED + "No Swords");
        weapons.addLore(ChatColor.RED + "No Enchants on Bows/Crossbows");
        break;
      case CLERIC:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.TRIDENT);
        weapons.addLore(ChatColor.GREEN + "Wooden, Stone, or Gold Axes and Swords");
        weapons.addLore(ChatColor.RED + "Tridents and Shields");
        weapons.addLore(ChatColor.RED + "No Bows/Crossbows");
        break;
      case KNIGHT:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.IRON_SWORD);
        weapons.addLore(ChatColor.GREEN + "Axes, Swords, Tridents, and Shields");
        weapons.addLore(ChatColor.RED + "No Bows/Crossbows");
        weapons.addLore(ChatColor.RED + "No Enchants on Tridents");
        break;
      case MAGE:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.GOLDEN_SWORD);
        weapons.addLore(ChatColor.GREEN + "Wooden, Stone, or Gold Swords");
        weapons.addLore(ChatColor.GREEN + "Bows/Crossbows");
        weapons.addLore(ChatColor.RED + "No Axes, Tridents, or Shields");
        weapons.addLore(ChatColor.RED + "No Enchants on Bows/Crossbows");
        break;
      case PYROMANCER:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.STONE_SWORD);
        weapons.addLore(ChatColor.GREEN + "Swords and Bows/Crossbows");
        weapons.addLore(ChatColor.RED + "No Axes, Tridents, or Shields");
        weapons.addLore(ChatColor.RED + "No Enchants on Bows/Crossbows");
        break;
      case SHAMAN:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.WOODEN_SWORD);
        weapons.addLore(ChatColor.GREEN + "Wooden, Stone, or Gold Swords");
        weapons.addLore(ChatColor.GREEN + "Bows/Crossbows");
        weapons.addLore(ChatColor.RED + "No Axes, Tridents, or Shields");
        weapons.addLore(ChatColor.RED + "No Enchants on Bows/Crossbows");
        break;
      case RANGER:
        weapons = new ItemIcon(ChatColor.YELLOW +"Weapons", Material.BOW);
        weapons.addLore(ChatColor.GREEN + "Wooden, Stone, Gold, or Iron Axes and Swords");
        weapons.addLore(ChatColor.GREEN + "Bows/Crossbows and Tridents");
        weapons.addLore(ChatColor.RED + "No Shields");
        weapons.addLore(ChatColor.RED + "No Enchants on Axes, Swords, or Tridents");
        break;
    }
  }

  private void setPassiveInfo(ClassName name) {
    switch(name) {
      case ASSASSIN:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.ARROW);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Assassinate");
        passive.addLore(ChatColor.WHITE + "Deal triple damage while crouching");
        break;
      case BERSERKER:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.BLAZE_POWDER);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Rage");
        passive.addLore(ChatColor.WHITE + "After taking damage, gain Strength II for 5s.");
        break;
      case CLERIC:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.GLOWSTONE_DUST);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Radiance");
        passive.addLore(ChatColor.WHITE + "When you hit an enemy with a melee weapon,");
        passive.addLore(ChatColor.WHITE + "they gain Weakness and Glowing for 5 seconds.");
        break;
      case KNIGHT:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.BRICK);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Hardy");
        passive.addLore(ChatColor.WHITE + "Immune to negative status effects.");
        break;
      case MAGE:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.BREWING_STAND);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Master Brewer");
        passive.addLore(ChatColor.WHITE + "Can create a Brewing Stand using a Stick instead of a Blaze Rod,");
        passive.addLore(ChatColor.WHITE + "which is fueled by Coal and can use");
        passive.addLore(ChatColor.WHITE + "any flower in place of Nether Wart.");
        passive.addLore(ChatColor.WHITE + "All potions brew faster using this Stand.");
        passive.addLore(ChatColor.WHITE + "Only Mages may use this special Brewing Stand.");
        break;
      case PYROMANCER:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.FLINT_AND_STEEL);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Firey Soul");
        passive.addLore(ChatColor.WHITE + "Gain permanent Fire Resistance");
        passive.addLore(ChatColor.WHITE + "and immunity to blast damage.");
        break;
      case SHAMAN:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.BONE);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Curse");
        passive.addLore(ChatColor.WHITE + "All attacks have Knockback II and apply");
        passive.addLore(ChatColor.WHITE + "Slowness for 3s.");
        break;
      case RANGER:
        passive = new ItemIcon(ChatColor.YELLOW +"Passive Ability", Material.FEATHER);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Stride");
        passive.addLore(ChatColor.WHITE + "Gain permanent Swiftness II, Dolphins Grace, and Slow Fall.");
        break;
    }
    passive.addLore(ChatColor.GREEN + "Always Active");
  }

  private void setActiveInfo(ClassName name) {
    switch(name) {
      case ASSASSIN:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.ELYTRA);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Cloak");
        active.addLore(ChatColor.WHITE + "Gain Invisibility and Haste for 5s.");
        active.addLore(ChatColor.WHITE + "Enemies won't target a cloaked Assassin.");
        active.addLore(ChatColor.WHITE + "10s Cooldown.");
        break;
      case BERSERKER:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.REDSTONE);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Bloodlust");
        active.addLore(ChatColor.WHITE + "Gain Swiftness III for 5s.");
        active.addLore(ChatColor.WHITE + "10s Cooldown.");
        break;
      case CLERIC:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.LIGHT);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Holy Light");
        active.addLore(ChatColor.WHITE + "Throw an Instant Health III splash potion.");
        active.addLore(ChatColor.WHITE + "7s Cooldown.");
        break;
      case KNIGHT:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.LIGHTNING_ROD);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Adrenaline Rush");
        active.addLore(ChatColor.WHITE + "Gain Resistance III for 5s.");
        active.addLore(ChatColor.WHITE + "15s Cooldown.");
        break;
      case MAGE:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.BLAZE_ROD);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Spellcasting");
        active.addLore(ChatColor.WHITE + "Choose between 3 effects, which may deal");
        active.addLore(ChatColor.WHITE + "damage, debuff enemies, or buff allies.");
        active.addLore(ChatColor.WHITE + "5s Cooldown.");
        break;
      case PYROMANCER:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.FIRE_CHARGE);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Fireball");
        active.addLore(ChatColor.WHITE + "Fire a Ghast's fireball.");
        active.addLore(ChatColor.WHITE + "10s Cooldown.");
        break;
      case SHAMAN:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.SOUL_LANTERN);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Guardian Spirits");
        active.addLore(ChatColor.WHITE + "Summon 2 Vexes for 20s which will attack enemies.");
        active.addLore(ChatColor.WHITE + "45s Cooldown.");
        break;
      case RANGER:
        active = new ItemIcon(ChatColor.YELLOW +"Active Ability", Material.ENDER_PEARL);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Teleport");
        active.addLore(ChatColor.WHITE + "Throw an Ender Pearl.");
        active.addLore(ChatColor.WHITE + "15s Cooldown.");
        break;
    }
    active.addLore(ChatColor.GREEN + "Shift + Right Click with an Item to Use");
  }
}
