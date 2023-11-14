package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import me.xenotater.classes_and_attributes.attributes.AttributeType;

public abstract class GenericAttribute extends Object {
  protected String name;
  protected AttributeType type;

  public void notifyGained(Player p) {

  }

  public void notifyLost(Player p) {

  }

  public void notifyBroken(Player p) {

  }

  public void breakCondition (Player p) {
    
  }

  public void startCurseTimer (Player p) {

  }
}
