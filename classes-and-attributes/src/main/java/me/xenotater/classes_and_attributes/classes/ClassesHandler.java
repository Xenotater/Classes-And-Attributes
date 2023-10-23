package me.xenotater.classes_and_attributes.classes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.Plugin;
import net.md_5.bungee.api.ChatColor;

public class ClassesHandler implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
    ClassMenu menu = new ClassMenu();
    if (command.getName().equals("classes")) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "This command cannot be run from the console.");
        return true;
      }
      if (args.length == 1) {
        ClassName className = ClassName.getValue(args[0]);
        if (args[0].equals("Random"))
          className = ClassName.getRandom();
        if (className != null)
          menu.openClass((Player) sender, className);
        else
          return false;
      }
      else if (args.length == 0)
        menu.openClassMenu((Player) sender);
      else
        return false;
      return true;
    }
    else if (command.getName().equals("setclass")) {
      if (args.length == 1) {
        if (!(sender instanceof Player)) {
          sender.sendMessage(ChatColor.WHITE + "/setclass <player> <class>");
          return true;
        }
        ClassName className = ClassName.getValue(args[0]);
        if (args[0].equals("Random"))
          className = ClassName.getRandom();
        if (className != null) {
          menu.openConfirm((Player) sender, className.getName());
          return true;
        }
        else {
          if (sender.isOp())
            sender.sendMessage(ChatColor.WHITE + "/setclass [player] <class>");
          else
            sender.sendMessage(ChatColor.RED + "Invalid Class.");
          return true;
        }
      }
      else if (args.length == 2) {
        if (sender.isOp()) {
          Player target = Bukkit.getPlayerExact(args[0]);
          if (target != null) {
            ClassName className = ClassName.getValue(args[1]);
            if (args[1].equals("Random"))
              className = ClassName.getRandom();
            if (!(sender instanceof Player)) {
              boolean success = Plugin.plugin.dataManager.setClass(target.getUniqueId(), className.getName());
              sender.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s class was updated." : "An error occurred while updating the target's class.");
              if (success)
                target.sendMessage("Your class was updated. You now have the " + className.getName() + " class!");
              return true;
            }
            if (className != null) {
              menu.openConfirm((Player) sender, target, className.getName());
              return true;
            }
            else
              sender.sendMessage(ChatColor.RED + "Invalid Class.");
              return true;
            }
          else {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
          }
        }
        if (sender.isOp()) {
          sender.sendMessage(ChatColor.WHITE + "/setclass [player] <class>");
          return true;
        }
        return false;
      }
      return false;
    }
    else if (command.getName().equals("removeclass")) {
      if (args.length == 0) {
        menu.openConfirm((Player) sender, "No Class");
        return true;
      }
      else if (args.length == 1) {
        if (sender.isOp()) {
          Player target = Bukkit.getPlayerExact(args[0]);
          if (!(sender instanceof Player)) {
              boolean success = Plugin.plugin.dataManager.setClass(target.getUniqueId(), "No Class");
              sender.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s class was updated." : "An error occurred while updating the target's class.");
              if (success)
                target.sendMessage("Your class was updated. You now have no class.");
            return true;
          }
          if (target != null) {
            menu.openConfirm((Player) sender, target, "No Class");
            return true;
          }
          else {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
          }
        }
        return false;
      }
      return false;
    }
    else
      sender.sendMessage(ChatColor.RED + "An error occurred.");
      return true;
  }
}