package me.xenotater.classes_and_attributes.classes.listeners;

import org.bukkit.event.Listener;

public abstract class GenericClassListener implements Listener {
  public String[] disallowedArmor;
  public String[] disallowedLeftClicks;
  public String[] disallowedRightClicks;
  public boolean armorEnchantsAllowed;
  public boolean weaponEnchantsAllowed;
  public boolean bowEnchantsAllowed;
}
