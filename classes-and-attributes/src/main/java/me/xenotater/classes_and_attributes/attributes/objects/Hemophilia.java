package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Hemophilia extends GenericCurse {
  public Hemophilia() {
    AttributeName attribute = AttributeName.HEMOPHILIA;
    name = attribute.getName();
    type = attribute.getType();
  }
}
