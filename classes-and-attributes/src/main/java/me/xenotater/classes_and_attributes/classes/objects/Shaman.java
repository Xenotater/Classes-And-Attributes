package me.xenotater.classes_and_attributes.classes.objects;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.ClassItemType;
import net.md_5.bungee.api.ChatColor;

public class Shaman extends GenericClass {
  public Shaman() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "iron_sword", "diamond_sword", "netherite_sword", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.BOW, false);
    abilityDuration = 20000;
    abilityCooldown = 45000;
  }

  //Curse
  public void triggerPassive(Player p, Event e) {
    if (e instanceof EntityDamageByEntityEvent) {
      EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
      if (event.getEntity() instanceof Creature) {
        Creature target = (Creature) event.getEntity();
        target.setVelocity(p.getLocation().getDirection().multiply(2));
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 0, false, true, false));
        notifyAbilityTriggered(p, "Curse");
      }
    }
  }

  //Guardian Spirits
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      summonSpirit(p);
      summonSpirit(p);
      startAbilityCooldown(p);
      notifyAbilityTriggered(p, "Guardian Spirits");
    }
    else {
      notifyAbilityCooldown(p, "Guardian Spirits");
    }
  }
  private void summonSpirit(Player p) {
    Vex spirit = p.getWorld().spawn(p.getLocation(), Vex.class);
    spirit.setCustomName(ChatColor.DARK_GREEN + "Guardian Spirit");
    spirit.setCustomNameVisible(true);
    spirit.setLifeTicks(400);
    spirit.setBound(p.getLocation());

    //constantly set the spirit's target to the nearest hostile mob
    Runnable targetSetter = new Runnable() {@Override public void run() {spirit.setTarget(getNearbyThreat(p));}};
    BukkitTask targetSetterTask = Bukkit.getScheduler().runTaskTimer(Plugin.plugin, targetSetter, 0, 1);

    //despawn the spirit when the ability ends
    Runnable spiritDespawner = new Runnable() {@Override public void run() {spirit.remove(); targetSetterTask.cancel();}};
    Bukkit.getScheduler().runTaskLater(Plugin.plugin, spiritDespawner, 400);
  }

  private Enemy getNearbyThreat(Player p) {
    List<Entity> nearbyMobs = p.getNearbyEntities(10, 10, 10);
    Location playerLoc = p.getLocation();
    Enemy nearest = null;
    double dist = -1;

    for (Entity e : nearbyMobs) {
      if (e instanceof Enemy && (e.getCustomName() == null || !e.getCustomName().equals(ChatColor.DARK_GREEN + "Guardian Spirit"))) {
        double newDist = playerLoc.distance(e.getLocation());
        if (dist == -1 || newDist < dist) {
          nearest = (Enemy) e;
          dist = newDist;
        }
      }
    }
    return nearest;
  }

  public void checkSpirit(Vex vex, EntityDamageByEntityEvent e) {
    if (vex.getCustomName() != null && vex.getCustomName().equals(ChatColor.DARK_GREEN + "Guardian Spirit")) {
      if (vex.getTarget() instanceof Player)
        vex.remove(); //ability must no longer be active... despawn
      else {
        if (e.getEntity().equals(vex))
          vex.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 5, false, false, false));
        if (e.getDamager().equals(vex))
          ((Creature) e.getEntity()).setTarget(vex); //make spirits distract mobs
      }
    }
  }
}
