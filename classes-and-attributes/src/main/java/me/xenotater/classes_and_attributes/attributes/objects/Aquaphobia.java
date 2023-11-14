package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Aquaphobia extends GenericAttribute {
  public Aquaphobia() {
    AttributeName attribute = AttributeName.AQUAPHOBIA;
    name = attribute.getName();
    type = attribute.getType();
  }
}
