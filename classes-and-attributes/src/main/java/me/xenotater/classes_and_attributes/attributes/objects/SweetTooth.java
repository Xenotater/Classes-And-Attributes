package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class SweetTooth extends GenericAttribute {
  public SweetTooth() {
    AttributeName attribute = AttributeName.SWEET_TOOTH;
    name = attribute.getName();
    type = attribute.getType();
  }
}
