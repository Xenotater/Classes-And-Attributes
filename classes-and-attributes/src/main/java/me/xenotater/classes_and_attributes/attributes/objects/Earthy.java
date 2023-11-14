package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Earthy extends GenericAttribute {
  public Earthy() {
    AttributeName attribute = AttributeName.EARTHY;
    name = attribute.getName();
    type = attribute.getType();
  }
}
