package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.Plugin;
import net.md_5.bungee.api.ChatColor;

public abstract class GenericCurse extends GenericAttribute {
  @Override
  public boolean addForPlayer(Player p) {
    boolean success = Plugin.plugin.dataManager.setCurse(p.getUniqueId(), name);
    if (success) {
      p.sendMessage(ChatColor.DARK_PURPLE + " ~ " + ChatColor.WHITE + "You've received the " + ChatColor.DARK_PURPLE + name + ChatColor.WHITE + " curse for 1 minute.");
      startTimer(p);
    }
    return success;
  }

  @Override
  public boolean removeForPlayer(Player p) {
    boolean success = Plugin.plugin.dataManager.setCurse(p.getUniqueId(), "None");
    if (success) {
      p.sendMessage(ChatColor.DARK_PURPLE + " ~ " + ChatColor.WHITE + "You are no longer cursed.");
      Plugin.plugin.curseTimers.get(p.getUniqueId()).cancel();
      Plugin.plugin.curseTimers.put(p.getUniqueId(), null);
    }
    return success;
  }
  
  public void startTimer(Player p) {
    Timer extisting = Plugin.plugin.curseTimers.get(p.getUniqueId());
    if (extisting != null)
      extisting.cancel();
    Timer timer = new Timer();
    TimerTask removeCurse = new TimerTask() {
      public void run() {
        removeForPlayer(p);
      }
    };
    timer.schedule(removeCurse, 60000);
    Plugin.plugin.curseTimers.put(p.getUniqueId(), timer);
  }
}
