package me.xenotater.classes_and_attributes.classes.mage_spellcasting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import me.xenotater.classes_and_attributes.Plugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SpellHandler {
  public void castSpell(Player p, String name) {
    Plugin.plugin.LOGGER.info("casting: " + name);
    List<Entity> multiTargets;
    Entity singleTarget;
    switch (name) {
      case "Bless": 
        multiTargets = getAllNearby(p, Player.class, 20);
        if (!multiTargets.isEmpty()) {
          affectEntities(multiTargets, new PotionEffect(PotionEffectType.HEAL, 1, 99));
          affectEntities(multiTargets, new PotionEffect(PotionEffectType.ABSORPTION, 100, 4));
        }
        affectEntity(p, new PotionEffect(PotionEffectType.HEAL, 1, 99));
        affectEntity(p, new PotionEffect(PotionEffectType.ABSORPTION, 100, 3));
        break;
      case "Empower":
        multiTargets = getAllNearby(p, Player.class, 20);
        if (!multiTargets.isEmpty()) {
          affectEntities(multiTargets, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
          affectEntities(multiTargets, new PotionEffect(PotionEffectType.SPEED, 100, 0));
        }
        affectEntity(p, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
        affectEntity(p, new PotionEffect(PotionEffectType.SPEED, 100, 0));
        break;
      case "Fly":
        affectEntity(p, new PotionEffect(PotionEffectType.LEVITATION, 200, 0));
        break;
      case "Warding":
        affectEntity(p, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1));
        break;
      case "Banish":
        singleTarget = getRandomNearby(p, Monster.class, 20);
        if (singleTarget != null)
          singleTarget.remove();
        break;
      case "Charm":
        singleTarget = getRandomNearby(p, Monster.class, 20);
        if (singleTarget != null) {
          Runnable targeter = new Runnable() {
            public void run() {
              ((Monster) singleTarget).setTarget((Monster) getRandomNearby(p, Monster.class, 20));
            }
          };
          scheduleRepeating(targeter, 20, 300);
        }
        break;
      case "Confusion":
        multiTargets = getAllNearby(p, Monster.class, 20);
        if (!multiTargets.isEmpty()) {
          Runnable unTargeter = new Runnable() {
            public void run() {
              for (Entity target : multiTargets) {
                ((Monster) target).setTarget(null);
                ((Monster) target).setLastDamageCause(null);
              }
            }
          };
          scheduleRepeating(unTargeter, 1, 100);
        }
        break;
      case "Suspend":
        multiTargets = getAllNearby(p, Monster.class, 20);
        if (!multiTargets.isEmpty())
          affectEntities(multiTargets, new PotionEffect(PotionEffectType.LEVITATION, 100, 0));
        break;
      case "Enflame":
        multiTargets = getAllNearby(p, Monster.class, 20);
        for (Entity target : multiTargets) {
          ((Damageable) target).setFireTicks(200);
        }
        break;
      case "Volley":
        Runnable arrowShooter = new Runnable() {
          public void run() {
            Arrow arrow = p.launchProjectile(Arrow.class);
            arrow.setShooter(p);
            arrow.setPierceLevel(2);
            arrow.setPickupStatus(PickupStatus.DISALLOWED);
            p.playSound(p, Sound.ENTITY_ARROW_SHOOT, 1, 1);
          }
        };
        scheduleRepeating(arrowShooter, 1, 20);
        break;
      case "Flamethrow":
        Runnable flameShooter = new Runnable() {
          public void run() {
            SmallFireball fireball = p.launchProjectile(SmallFireball.class);
            fireball.setDirection(p.getLocation().getDirection());
            fireball.setShooter(p);
            p.playSound(p, Sound.ITEM_FIRECHARGE_USE, 1, 1);
          }
        };
        scheduleRepeating(flameShooter, 2, 20);
        break;
      case "Electrify":
        Runnable lightingCaller = new Runnable() {
          public void run() {
            int bound = 20;
            for (int i=0; i<3; i++) {
              boolean found = false;
              int count = 0;
              while (!found && count < 255) {
                found = true;
                Location playerLoc = p.getLocation();
                double randX = playerLoc.getX() + new Random().nextInt(bound) - 10;
                double randZ = playerLoc.getZ() + new Random().nextInt(bound) - 10;
                boolean onSurface = playerLoc.getY() == p.getWorld().getHighestBlockYAt(playerLoc.getBlockX(), playerLoc.getBlockZ());
                double y = onSurface ? p.getWorld().getHighestBlockYAt((int) randX, (int) randZ) : playerLoc.getY(); 
                Location summonLoc = new Location(p.getWorld(), randX, y, randZ);
                Collection<Entity> targets = p.getWorld().getNearbyEntities(summonLoc, 2, 0, 2);
                for (Entity target : targets) {
                  if (target instanceof Player)
                    found = false;
                }
                if (found) {
                  LightningStrike strike = (LightningStrike) p.getWorld().spawnEntity(summonLoc, EntityType.LIGHTNING);
                  strike.setCausingPlayer(p);
                }
                  
                else
                  count ++;
              }
            }
          }
        };
        scheduleRepeating(lightingCaller, 20, 100);
        break;
      case "Companions": 
        for (int i=0; i<3; i++) {
          Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
          wolf.setAdult();
          wolf.setOwner(p);
          wolf.setCollarColor(DyeColor.PURPLE);
          wolf.setAngry(true);
          wolf.setTarget((Monster) getRandomNearby(p, Monster.class, 20));
          wolf.setCustomName(ChatColor.LIGHT_PURPLE + "Magic Companion");
          wolf.setCustomNameVisible(true);
          wolf.setRemoveWhenFarAway(true);
          scheduleDespawn(wolf, 300);
        }
        break;
      default:
        return;
    }
    notifySpellCast(p, name);
  }

  private List<Entity> getAllNearby(Entity center, Class<? extends Entity> clazz, int radius) {
    List<Entity> nearby = new ArrayList<>();
    List<Entity> enitities = center.getNearbyEntities(radius, radius, radius);
    for (Entity e : enitities)
      if (clazz.isInstance(e) && !e.equals(center))
        nearby.add(e);
    return nearby;
  }

  private Entity getRandomNearby(Entity caster, Class<? extends Entity> clazz, int radius) {
    List<Entity> nearby = getAllNearby(caster, clazz, radius);
    if (nearby.isEmpty())
      return null;
    Integer rand = new Random().nextInt(nearby.size());
    return nearby.get(rand);
  }

  private void affectEntities(List<Entity> targets, PotionEffect effect) {
    for (Entity target : targets) {
      if (target instanceof LivingEntity)
        affectEntity((LivingEntity) target, effect);
    }
  }

  private void affectEntity(LivingEntity target, PotionEffect effect) {
    target.addPotionEffect(effect);
  }

  private void scheduleRepeating(Runnable runnable, int period, int duration) {
    BukkitTask task = Bukkit.getScheduler().runTaskTimer(Plugin.plugin, runnable, 1, period);
    Bukkit.getScheduler().runTaskLater(Plugin.plugin, new Runnable() {public void run() {task.cancel();}}, duration);
  }

  private void scheduleDespawn(Entity toDespawn, int time) {
    Bukkit.getScheduler().runTaskLater(Plugin.plugin, new Runnable() {public void run() {toDespawn.remove();}}, time);
  }

  private void notifySpellCast(Player p, String name) {
    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.LIGHT_PURPLE + "** Cast " + name + " **"));
  }
}
