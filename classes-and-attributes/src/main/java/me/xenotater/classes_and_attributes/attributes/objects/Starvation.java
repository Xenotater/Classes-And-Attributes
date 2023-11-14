package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Starvation extends GenericAttribute {
  public Starvation() {
    AttributeName attribute = AttributeName.STARVATION;
    name = attribute.getName();
    type = attribute.getType();
  }
}
