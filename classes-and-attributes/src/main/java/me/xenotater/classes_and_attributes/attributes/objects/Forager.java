package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Forager extends GenericAttribute {
  public Forager() {
    AttributeName attribute = AttributeName.FORAGER;
    name = attribute.getName();
    type = attribute.getType();
  }
}
