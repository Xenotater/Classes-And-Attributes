package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class ChemicalInterest extends GenericAttribute {
  public ChemicalInterest() {
    AttributeName attribute = AttributeName.CHEMICAL_INTEREST;
    name = attribute.getName();
    type = attribute.getType();
  }
}
