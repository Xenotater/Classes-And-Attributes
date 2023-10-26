package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Mage extends GenericClass {
  public Mage() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
    enchantsAllowed.put(ClassItemType.BOW, false);
  }

  //Master Brewer
  public void triggerPassive(Player p, Event e) {

  }

  //Spellcasting
  public void triggerActive(Player p, Event e) {
    
  }
}
