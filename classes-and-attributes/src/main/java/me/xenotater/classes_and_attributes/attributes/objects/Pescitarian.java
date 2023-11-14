package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Pescitarian extends GenericAttribute {
  public Pescitarian() {
    AttributeName attribute = AttributeName.PESCITARIAN;
    name = attribute.getName();
    type = attribute.getType();
  }
}
