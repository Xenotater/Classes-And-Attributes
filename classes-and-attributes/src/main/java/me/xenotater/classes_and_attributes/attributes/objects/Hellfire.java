package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Hellfire extends GenericCurse {
  private Map<UUID, Integer> onFire = new HashMap<>();

  public Hellfire() {
    AttributeName attribute = AttributeName.HELLFIRE;
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
    Integer taskId = onFire.get(p.getUniqueId());
    if (taskId != null)
      Bukkit.getScheduler().cancelTask(taskId);
    onFire.remove(p.getUniqueId());
    p.setVisualFire(false);
    return super.removeForPlayer(p);
  }

  public void triggerEffect(Player p, Event e) {
    if (e == null) {
      BukkitTask fireSetter = new BukkitRunnable() {
        int count = 0;
        public void run() {
          p.setVisualFire(true);
          if (count % 5 == 0)
            p.damage(0.15);
        }
      }.runTaskTimer(Plugin.plugin, 0, 0);
      onFire.put(p.getUniqueId(), fireSetter.getTaskId());
    }
  }

}
