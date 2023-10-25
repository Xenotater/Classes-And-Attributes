package me.xenotater.classes_and_attributes.classes.objects;

import java.util.HashMap;
import java.util.Map;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public abstract class GenericClass extends Object{
  protected String[] disallowedArmor;
  protected String[] disallowedWeapons;
  protected String[] disallowedInteracts;
  protected Map<ClassItemType, Boolean> enchantsAllowed = new HashMap<>();
  
  public GenericClass() {
    disallowedArmor = new String[]{};
    disallowedInteracts = new String[]{};
    disallowedWeapons = new String[]{};
    for (ClassItemType itemType : ClassItemType.values())
      enchantsAllowed.put(itemType, true);
  }

  public String[] getDisallowedArmor() {
    return disallowedArmor;
  }

  public String[] getDisallowedWeapons() {
    return disallowedWeapons;
  }

  public String[] getDisallowedInteracts() {
    return disallowedInteracts;
  }

  public boolean getEnchantsAllowed(ClassItemType itemType) {
    return enchantsAllowed.get(itemType);
  }
}
