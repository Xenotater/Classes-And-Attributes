package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import me.xenotater.classes_and_attributes.attributes.AttributeType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public abstract class GenericAttribute extends Object {
  protected String name;
  protected AttributeType type;

  public boolean addForPlayer(Player p) {
    boolean success = Plugin.plugin.dataManager.addAttribute(p.getUniqueId(), name);
    if (success)
      p.sendMessage(ChatColor.GREEN + " + " + ChatColor.WHITE + "You've gained the " + type.getColor() + name + ChatColor.WHITE + " attribute.");
    return success;
  }

  public boolean removeForPlayer(Player p) {
    boolean success = Plugin.plugin.dataManager.removeAttribute(p.getUniqueId(), name);
    if (success)
      p.sendMessage(ChatColor.RED + " - " + ChatColor.WHITE + "You've lost the " + type.getColor() + name + ChatColor.WHITE + " attribute.");
    return success;
  }

  public boolean breakForPlayer(Player p) {
    p.sendMessage(ChatColor.RED + "You broke a condition for the " + name + " attribute!");
    Plugin.plugin.dataManager.addFailure(p.getUniqueId());
    return cursePlayer(p);
  }

  public boolean cursePlayer(Player p) {
    AttributeName curse = Plugin.plugin.dataManager.getCurse(p.getUniqueId());
    if (curse == null)
      curse = AttributeName.getRandomCurse();
    return Plugin.plugin.attributes.get(curse).addForPlayer(p);
  }

  public void sendActionBarMessage(Player p, String message) {
    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
  }

  public void triggerEffect(Player p, Event e) {
    throw new UnsupportedOperationException("Non-Generic Attributes should override this method.");
  }
}
