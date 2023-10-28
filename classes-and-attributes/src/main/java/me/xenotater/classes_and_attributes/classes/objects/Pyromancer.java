package me.xenotater.classes_and_attributes.classes.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.xenotater.classes_and_attributes.classes.ClassItemType;

public class Pyromancer extends GenericClass {
  private List<DamageCause> immunities = new ArrayList<>();
  public Pyromancer() {
    disallowedArmor = new String[]{"iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.BOW, false);
    abilityDuration = 0;
    abilityCooldown = 10000;
    setImmunities();
  }

  //Fiery Soul
  public void triggerPassive(Player p, Event e) {
    if (e instanceof EntityDamageEvent) {
      EntityDamageEvent event = (EntityDamageEvent) e;
      DamageCause cause = event.getCause();
      if (immunities.contains(cause)) {
        event.setCancelled(true);
        notifyAbilityTriggered(p, "Fiery Soul");
      }
    }
  }

  //Fireball
  public void triggerActive(Player p, Event e) {
    if (isAbilityReady(p)) {
      Fireball fireball = p.launchProjectile(Fireball.class);
      fireball.setDirection(p.getLocation().getDirection());
      startAbilityCooldown(p);
      notifyAbilityTriggered(p, "Fireball");
    }
    else {
      notifyAbilityCooldown(p, "Fireball");
    }
  }

  private void setImmunities() {
    immunities.add(DamageCause.BLOCK_EXPLOSION);
    immunities.add(DamageCause.ENTITY_EXPLOSION);
    immunities.add(DamageCause.FIRE);
    immunities.add(DamageCause.FIRE_TICK);
    immunities.add(DamageCause.LAVA);
    immunities.add(DamageCause.HOT_FLOOR);
  }
}
