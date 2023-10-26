package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Cleric extends GenericClass {
  public Cleric() {
    disallowedWeapons = new String[]{"iron_sword", "iron_axe", "diamond_sword", "diamond_axe", "netherite_sword", "netherite_axe"};
    disallowedInteracts = new String[]{"bow", "crossbow"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
  }

  //Radiance
  public void triggerPassive(Player p, Event e) {

  }

  //Holy Light
  public void triggerActive(Player p, Event e) {
    
  }
}
