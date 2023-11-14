package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Scavenger extends GenericAttribute {
  public Scavenger() {
    AttributeName attribute = AttributeName.SCAVENGER;
    name = attribute.getName();
    type = attribute.getType();
  }
}
