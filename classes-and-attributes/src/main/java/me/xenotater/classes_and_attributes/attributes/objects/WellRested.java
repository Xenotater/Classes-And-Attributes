package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class WellRested extends GenericAttribute {
  public WellRested() {
    AttributeName attribute = AttributeName.WELL_RESTED;
    name = attribute.getName();
    type = attribute.getType();
  }
}
