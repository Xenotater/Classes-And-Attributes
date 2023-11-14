package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Pacifist extends GenericAttribute {
  public Pacifist() {
    AttributeName attribute = AttributeName.PACIFIST;
    name = attribute.getName();
    type = attribute.getType();
  }
}
