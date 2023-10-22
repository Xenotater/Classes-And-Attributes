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
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "This command cannot be run from the console.");
      return true;
    }
    ClassMenu menu = new ClassMenu();
    if (command.getName().equals("classes")) {
      if (args.length == 1) {
        ClassName className = ClassName.getValue(args[0]);
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
        ClassName className = ClassName.getValue(args[0]);
        if (className != null) {
          menu.openConfirm((Player) sender, className.getName(), false);
          return true;
        }
        else
          sender.sendMessage(ChatColor.RED + "Invalid Class.");
          return true;
      }
      else if (args.length == 2) {
        if (sender.isOp()) {
          Player target = Bukkit.getPlayerExact(args[0]);
          if (target != null) {
            ClassName className = ClassName.getValue(args[1]);
            if (className != null) {
              menu.openConfirm((Player) sender, className.getName(), true);
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
        return false;
      }
      return false;
    }
    else if (command.getName().equals("removeclass")) {
      if (args.length == 0) {
        menu.openConfirm((Player) sender, "No Class", false);
        return true;
      }
      else if (args.length == 1) {
        if (sender.isOp()) {
          Player target = Bukkit.getPlayerExact(args[0]);
          if (target != null) {
            menu.openConfirm((Player) sender, "No Class", true);
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