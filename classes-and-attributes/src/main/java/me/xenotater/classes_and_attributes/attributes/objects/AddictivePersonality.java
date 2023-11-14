package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class AddictivePersonality extends GenericAttribute {
  public AddictivePersonality() {
    AttributeName attribute = AttributeName.ADDICTIVE;
    name = attribute.getName();
    type = attribute.getType();
  }
}
