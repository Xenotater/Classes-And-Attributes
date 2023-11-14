package me.xenotater.classes_and_attributes.attributes.objects;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class FriendOfTheNether extends GenericAttribute {
  public FriendOfTheNether() {
    AttributeName attribute = AttributeName.NETHER_FRIEND;
    name = attribute.getName();
    type = attribute.getType();
  }
}
