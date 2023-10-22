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
        info = new ItemIcon("" + ChatColor.RESET + ChatColor.BLUE + ChatColor.BOLD + name.getName(), "cf76c50d0672ca53fbb68c6ac7d1ef4796dd359173f07c8dd40056c5e2e2f132");
        info.addLore(ChatColor.LIGHT_PURPLE + "Someone who specializes in close combat and stealth.");
        info.addLore(ChatColor.LIGHT_PURPLE + "They must be careful with their positioning due to how frail they are.");
        info.addLore(ChatColor.LIGHT_PURPLE + "Deals additional damage when crouching, and can become invisible for a short time.");
        armor = new ItemIcon("" + ChatColor.RESET + ChatColor.YELLOW + "Armor", Material.LEATHER_BOOTS);
        armor.addLore(ChatColor.GREEN + "Leather or Iron " + ChatColor.WHITE + "(Leggings and Boots only)");
        weapons = new ItemIcon("" + ChatColor.RESET + ChatColor.YELLOW +"Weapons", Material.CROSSBOW);
        weapons.addLore(ChatColor.GREEN + "Any Weapons");
        weapons.addLore(ChatColor.RED + "No Shields");
        passive = new ItemIcon("" + ChatColor.RESET + ChatColor.YELLOW +"Passive Ability", Material.ARROW);
        passive.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Assassinate");
        passive.addLore(ChatColor.WHITE + "Deal triple damage while crouching");
        active = new ItemIcon("" + ChatColor.RESET + ChatColor.YELLOW +"Active Ability", Material.ELYTRA);
        active.addLore("" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "Cloak");
        active.addLore(ChatColor.WHITE + "Gain Invisibility and Haste for 5s.");
        active.addLore(ChatColor.WHITE + "10s Cooldown.");
        break;
      case BERSERKER:
        break;
      case CLERIC:

        break;
      case KNIGHT:

        break;
      case MAGE:

        break;
      case PYROMANCER:

        break;
      case SHAMAN:

        break;
      case RANGER:

        break;
    }
  }

  private void setArmorInfo(ClassName name) {
    
  }

  private void setWeaponsInfo(ClassName name) {
    
  }

  private void setPassiveInfo(ClassName name) {
    
  }

  private void setActiveInfo(ClassName name) {
    
  }
}
