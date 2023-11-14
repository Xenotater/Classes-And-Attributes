package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Cannibal extends GenericAttribute {
  public Cannibal() {
    AttributeName attribute = AttributeName.CANNIBAL;
    name = attribute.getName();
    type = attribute.getType();
  }
}
