package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class ExpertSmith extends GenericAttribute {
  public ExpertSmith() {
    AttributeName attribute = AttributeName.EXPERT_SMITH;
    name = attribute.getName();
    type = attribute.getType();
  }
}
