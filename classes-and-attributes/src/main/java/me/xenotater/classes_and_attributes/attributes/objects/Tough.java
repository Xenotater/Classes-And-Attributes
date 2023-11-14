package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Tough extends GenericAttribute {
  public Tough() {
    AttributeName attribute = AttributeName.TOUGH;
    name = attribute.getName();
    type = attribute.getType();
  }
}
