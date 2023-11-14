package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Carnivore extends GenericAttribute {
  public Carnivore() {
    AttributeName attribute = AttributeName.CARNIVORE;
    name = attribute.getName();
    type = attribute.getType();
  }
}
