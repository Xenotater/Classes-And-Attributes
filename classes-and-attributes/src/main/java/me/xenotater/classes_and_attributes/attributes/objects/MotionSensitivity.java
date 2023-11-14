package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class MotionSensitivity extends GenericAttribute {
  public MotionSensitivity() {
    AttributeName attribute = AttributeName.MOTION_WEAKNESS;
    name = attribute.getName();
    type = attribute.getType();
  }
}
