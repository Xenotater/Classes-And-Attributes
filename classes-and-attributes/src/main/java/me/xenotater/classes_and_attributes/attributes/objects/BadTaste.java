package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class BadTaste extends GenericAttribute {
  public BadTaste() {
    AttributeName attribute = AttributeName.BAD_TASTE;
    name = attribute.getName();
    type = attribute.getType();
  }
}
