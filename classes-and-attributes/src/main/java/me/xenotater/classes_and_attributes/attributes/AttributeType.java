package me.xenotater.classes_and_attributes.attributes;

import net.md_5.bungee.api.ChatColor;

public enum AttributeType {
  DIET,
  POSITIVE,
  NEGATIVE,
  CURSE;

  public ChatColor getColor() {
    if (this == DIET)
      return ChatColor.YELLOW;
    else if (this == POSITIVE)
      return ChatColor.GREEN;
    else if (this == NEGATIVE)
      return ChatColor.RED;
    else if (this == CURSE)
      return ChatColor.DARK_PURPLE;
    return ChatColor.RESET;
  }
}