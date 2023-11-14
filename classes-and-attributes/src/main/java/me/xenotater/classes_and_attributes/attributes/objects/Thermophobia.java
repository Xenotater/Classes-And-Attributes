package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Thermophobia extends GenericAttribute {
  public Thermophobia() {
    AttributeName attribute = AttributeName.THERMOPHOBIA;
    name = attribute.getName();
    type = attribute.getType();
  }
}
