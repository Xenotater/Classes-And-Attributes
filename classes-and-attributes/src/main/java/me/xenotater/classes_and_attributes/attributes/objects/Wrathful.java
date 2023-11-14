package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Wrathful extends GenericAttribute {
  public Wrathful() {
    AttributeName attribute = AttributeName.WRATHFUL;
    name = attribute.getName();
    type = attribute.getType();
  }
}
