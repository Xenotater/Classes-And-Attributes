package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class BronzeAge extends GenericAttribute {
  public BronzeAge() {
    AttributeName attribute = AttributeName.BRONZE_AGE;
    name = attribute.getName();
    type = attribute.getType();
  }
}
