package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class SunlightSensitivity extends GenericAttribute {
  public SunlightSensitivity() {
    AttributeName attribute = AttributeName.SUNLIGHT_WEAKNESS;
    name = attribute.getName();
    type = attribute.getType();
  }
}
