package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Scavenger extends GenericAttribute {
  public Scavenger() {
    AttributeName attribute = AttributeName.SCAVENGER;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityDeathEvent) {
      EntityDeathEvent event = (EntityDeathEvent) e;
      Location loc = event.getEntity().getLocation();
      List<ItemStack> drops = event.getDrops();
      Integer rand = new Random().nextInt(2);
      if (rand == 1) {
        sendActionBarMessage(p, ChatColor.GREEN + "Double Drops!");
        for (ItemStack drop : drops) {
          p.getWorld().dropItemNaturally(loc, drop.clone());
        }
        event.setDroppedExp(event.getDroppedExp() * 2);
      }
    }
  }
}
