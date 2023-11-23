package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class DeadWeight extends GenericCurse {
  public DeadWeight() {
    AttributeName attribute = AttributeName.DEAD_WEIGHT;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof PlayerMoveEvent) {
      Block block = p.getWorld().getBlockAt(p.getLocation());
      if (block.isLiquid()) {
        Vector velocity = p.getVelocity();
        Location playerLoc = p.getLocation();
        Location ground = getGround(p);
        if (playerLoc.getY() > ground.getY() + 2.25)
          p.setVelocity(new Vector(velocity.getX(), -0.33, velocity.getZ()));
      }
    }
    else if (e instanceof EntityToggleGlideEvent) {
      EntityToggleGlideEvent event = (EntityToggleGlideEvent) e;
      if (event.isGliding())
        event.setCancelled(true);
    }
  }

  private Location getGround(Player p) {
    Location pLoc = p.getLocation();
    double y = pLoc.getY();
    while (y > -64) {
      Block block = p.getWorld().getBlockAt(new Location(p.getWorld(), pLoc.getX(), y, pLoc.getZ()));
      if (block.getType().isSolid())
        return block.getLocation();
      y--;
    }
    return null;
  }
}
