package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Pyromancer extends GenericClass {
  public Pyromancer() {
    disallowedArmor = new String[]{"iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.BOW, false);
  }

  //Fiery Soul
  public void triggerPassive(Player p, Event e) {

  }

  //Fireball
  public void triggerActive(Player p, Event e) {
    
  }
}
