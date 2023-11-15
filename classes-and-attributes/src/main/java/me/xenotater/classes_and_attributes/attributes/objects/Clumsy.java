package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Clumsy extends GenericCurse {
  public Clumsy() {
    AttributeName attribute = AttributeName.CLUMSY;
    name = attribute.getName();
    type = attribute.getType();
  }
}
