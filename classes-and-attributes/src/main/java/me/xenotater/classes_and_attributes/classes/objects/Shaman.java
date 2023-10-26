package me.xenotater.classes_and_attributes.classes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Shaman extends GenericClass {
  public Shaman() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "iron_sword", "diamond_sword", "netherite_sword", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.BOW, false);
  }

  //Curse
  public void triggerPassive(Player p, Event e) {

  }

  //Guardian Spirits
  public void triggerActive(Player p, Event e) {

  }
}
