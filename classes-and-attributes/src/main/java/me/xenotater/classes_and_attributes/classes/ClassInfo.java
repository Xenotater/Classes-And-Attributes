package me.xenotater.classes_and_attributes.classes;

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
        info = new ItemIcon(name.getName(), "cf76c50d0672ca53fbb68c6ac7d1ef4796dd359173f07c8dd40056c5e2e2f132");
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
