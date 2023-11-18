package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.bukkit.entity.Enemy;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class FriendOfTheNether extends GenericAttribute {
  List<Class<? extends Enemy>> netherMobs = new ArrayList<>();
  Map<UUID, Timer> ignoreMobs = new HashMap<>();

  public FriendOfTheNether() {
    AttributeName attribute = AttributeName.NETHER_FRIEND;
    name = attribute.getName();
    type = attribute.getType();
    setNetherMobs();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof EntityTargetEvent) {
      EntityTargetEvent event = (EntityTargetEvent) e;
      if (event.getEntity() instanceof Enemy) {
        Enemy enemy = (Enemy) event.getEntity();
        if (isNetherMob(enemy) && ignoreMobs.get(enemy.getUniqueId()) == null)
          event.setCancelled(true);
      }
    }
    else if (e instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
      if (event.getEntity() instanceof Enemy) {
        Enemy enemy = (Enemy) event.getEntity();
        if (isNetherMob(enemy) && event.getDamager() instanceof Player)
          ignoreMob(enemy);
      }
    }
  }

  private void setNetherMobs() {
    netherMobs.add(PigZombie.class);
    netherMobs.add(Piglin.class);
    netherMobs.add(PiglinBrute.class);
    netherMobs.add(Ghast.class);
    netherMobs.add(WitherSkeleton.class);
    netherMobs.add(MagmaCube.class);
    netherMobs.add(Hoglin.class);
  }

  private boolean isNetherMob(Enemy e) {
    for (Class<? extends Enemy> netherMob : netherMobs) {
      if (netherMob.isInstance(e))
        return true;
    }
    return false;
  }

  //ignore mob when preventing targeting for 5 minutes
  private void ignoreMob(Enemy e) {
    UUID id = e.getUniqueId();
    Timer existing = ignoreMobs.get(id);
    if (existing != null)
      existing.cancel();
    Timer ignoreTimer = new Timer();
    TimerTask stopIgnoring = new TimerTask() {
      public void run() {
        ignoreMobs.get(id).cancel();
        ignoreMobs.remove(id);
      }
    };
    ignoreTimer.schedule(stopIgnoring, 300000);
    ignoreMobs.put(id, ignoreTimer);
  }
}
