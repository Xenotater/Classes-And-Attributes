package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Gourmand extends GenericAttribute {
  public Gourmand() {
    AttributeName attribute = AttributeName.GOURMAND;
    name = attribute.getName();
    type = attribute.getType();
  }
}
