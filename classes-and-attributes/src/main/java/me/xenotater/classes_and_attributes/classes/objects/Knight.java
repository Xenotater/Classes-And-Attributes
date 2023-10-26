package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Knight extends GenericClass {
  public Knight() {
    disallowedInteracts = new String[]{"bow", "crossbow"};
    enchantsAllowed.put(ClassItemType.TRIDENT, false);
  }

  //Hardy
  public void triggerPassive(Player p, Event e) {

  }

  //Adrenaline Rush
  public void triggerActive(Player p, Event e) {
    
  }
}
