package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Picky extends GenericAttribute {
  public Picky() {
    AttributeName attribute = AttributeName.PICKY;
    name = attribute.getName();
    type = attribute.getType();
  }
}
