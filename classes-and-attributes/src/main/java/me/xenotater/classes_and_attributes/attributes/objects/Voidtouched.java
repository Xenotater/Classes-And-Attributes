package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Voidtouched extends GenericAttribute {
  public Voidtouched() {
    AttributeName attribute = AttributeName.VOIDTOUCHED;
    name = attribute.getName();
    type = attribute.getType();
  }
}
