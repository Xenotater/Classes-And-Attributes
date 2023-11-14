package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class CompulsiveMiner extends GenericAttribute {
  public CompulsiveMiner() {
    AttributeName attribute = AttributeName.COMPULSIVE_MINER;
    name = attribute.getName();
    type = attribute.getType();
  }
}
