package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class DeadWeight extends GenericCurse {
  public DeadWeight() {
    AttributeName attribute = AttributeName.DEAD_WEIGHT;
    name = attribute.getName();
    type = attribute.getType();
  }
}
