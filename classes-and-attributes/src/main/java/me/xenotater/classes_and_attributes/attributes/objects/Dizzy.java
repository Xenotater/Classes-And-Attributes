package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Dizzy extends GenericCurse {
  private PotionEffect curseEffect = new PotionEffect(PotionEffectType.CONFUSION, 20 * 60, 0);

  public Dizzy() {
    AttributeName attribute = AttributeName.DIZZY;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public boolean addForPlayer(Player p) {
    boolean success = super.addForPlayer(p);
    if (success)
      triggerEffect(p, null);
    return success;
  }

  @Override
  public boolean removeForPlayer(Player p) {
    boolean success = super.removeForPlayer(p);
    if (success)
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, new Runnable() {public void run() {p.removePotionEffect(PotionEffectType.CONFUSION);}}, 1);
    return success;
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    handlePotionCurse(p, e, curseEffect);
  }
}
