package me.xenotater.classes_and_attributes.attributes;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.objects.GenericCurse;
import me.xenotater.classes_and_attributes.attributes.objects.GenericDiet;
import net.md_5.bungee.api.ChatColor;

public class AttributesHandler implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
    if (command.getName().equals("attributes")) {
      AttributeMenu menu = new AttributeMenu();
      if (!(sender instanceof Player))
        sender.sendMessage(ChatColor.RED + "This command cannot be run from the console.");
      else if (args.length == 0)
        menu.openMenu((Player) sender);
      else
        return false;
      return true;
    }
    else if (command.getName().equals("addattribute")) {
      if (sender instanceof Player && !sender.isOp()) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
      }
      else {
        if (args.length < 2)
          return false;
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
          sender.sendMessage(ChatColor.RED + "Player not found.");
          return true;
        }
        List<AttributeName> playerAttribtues = Plugin.plugin.dataManager.getAttibutes(target.getUniqueId());
        AttributeName attribute = AttributeName.getValue(args[1].replaceAll("_", " "));
        if (args[1].equals("Random")) {
          attribute = getRandomAttribute(target);
          if (attribute == null) {
            sender.sendMessage(ChatColor.WHITE + "No attributes to add.");
          }
        }
        if (attribute == null) {
          sender.sendMessage(ChatColor.RED + "Invalid attribute.");
          return true;
        }
        if (attribute.getType() == AttributeType.DIET) {
          sender.sendMessage(ChatColor.WHITE + "Use /swapdiet <player> <diet>");
          return true;
        }
        if (attribute.getType() == AttributeType.CURSE) {
          sender.sendMessage(ChatColor.WHITE + "Use /curse <player> <curse>");
          return true;
        }
        if (playerAttribtues.contains(attribute)) {
          sender.sendMessage(ChatColor.RED + "Player already has that attribute.");
          return true;
        }
        boolean success = Plugin.plugin.attributes.get(attribute).addForPlayer(target);
        sender.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s attributes were updated." : ChatColor.RED + "An error occurred while updating the target's attributes.");
      }
      return true;
    }
    else if (command.getName().equals("removeattribute")) {
      if (sender instanceof Player && !sender.isOp()) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
      }
      else {
        if (args.length < 2)
          return false;
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
          sender.sendMessage(ChatColor.RED + "Player not found.");
          return true;
        }
        List<AttributeName> playerAttribtues = Plugin.plugin.dataManager.getAttibutes(target.getUniqueId());
        AttributeName attribute = AttributeName.getValue(args[1].replaceAll("_", " "));
        if (attribute == null) {
          sender.sendMessage(ChatColor.RED + "Invalid attribute.");
          return true;
        }
        if (attribute.getType() == AttributeType.DIET) {
          sender.sendMessage(ChatColor.WHITE + "Use /changediet <player> <diet>");
          return true;
        }
        if (attribute.getType() == AttributeType.CURSE) {
          sender.sendMessage(ChatColor.WHITE + "Use /curse <player> <curse>");
          return true;
        }
        if (!playerAttribtues.contains(attribute)) {
          sender.sendMessage(ChatColor.WHITE + "Player does not have that attribute.");
          return true;
        }
        boolean success = Plugin.plugin.attributes.get(attribute).removeForPlayer(target);
        sender.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s attributes were updated." : ChatColor.RED + "An error occurred while updating the target's attributes.");
      }
      return true;
    }
    else if (command.getName().equals("changediet")) {
      if (sender instanceof Player && !sender.isOp()) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
      }
      else {
        if (args.length < 2)
          return false;
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
          sender.sendMessage(ChatColor.RED + "Player not found.");
          return true;
        }
        AttributeName attribute = AttributeName.getValue(args[1].replaceAll("_", " "));
        if (attribute == null || attribute.getType() != AttributeType.DIET) {
          sender.sendMessage(ChatColor.RED + "Invalid diet.");
          return true;
        }
        boolean success = ((GenericDiet) Plugin.plugin.attributes.get(attribute)).addForPlayer(target);
        sender.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s diet was updated." : ChatColor.RED + "An error occurred while updating the target's diet.");
      }
      return true;
    }
    else if (command.getName().equals("setcurse")) {
      if (sender instanceof Player && !sender.isOp()) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
      }
      else {
        if (args.length < 2)
          return false;
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
          sender.sendMessage(ChatColor.RED + "Player not found.");
          return true;
        }
        boolean success;
        if (args[1].equals("None")) {
          AttributeName currentCurse = Plugin.plugin.dataManager.getCurse(target.getUniqueId());
          if (currentCurse != null)
            success = Plugin.plugin.attributes.get(currentCurse).removeForPlayer(target);
          else
            success = true;
        }
        else {
          AttributeName attribute = AttributeName.getValue(args[1].replaceAll("_", " "));
          if (attribute == null || attribute.getType() != AttributeType.CURSE) {
            sender.sendMessage(ChatColor.RED + "Invalid curse.");
            return true;
          }
          success = ((GenericCurse) Plugin.plugin.attributes.get(attribute)).addForPlayer(target);
        }
        sender.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s curse was updated." : ChatColor.RED + "An error occurred while updating the target's curse.");
      }
      return true;
    }
    else
      sender.sendMessage(ChatColor.RED + "An error occurred.");
    return true;
  }

  private AttributeName getRandomAttribute(Player player) {
    AttributeName attribute = null;
    List<AttributeName> playerAttributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());
    if (playerAttributes.isEmpty())
      return attribute;
    boolean found = false;
    while (!found) {
      attribute = AttributeName.getRandom();
      if (!playerAttributes.contains(attribute))
        found = true;
    }
    return attribute;
  }
}
