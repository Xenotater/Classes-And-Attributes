package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Vegetarian extends GenericAttribute {
  public Vegetarian() {
    AttributeName attribute = AttributeName.VEGETARIAN;
    name = attribute.getName();
    type = attribute.getType();
  }
}
