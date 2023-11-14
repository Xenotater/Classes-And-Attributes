package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class ThouroughMiner extends GenericAttribute {
  public ThouroughMiner() {
    AttributeName attribute = AttributeName.THOUROUGH_MINER;
    name = attribute.getName();
    type = attribute.getType();
  }
}
