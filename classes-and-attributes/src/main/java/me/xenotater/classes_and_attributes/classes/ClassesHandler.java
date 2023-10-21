package me.xenotater.classes_and_attributes.classes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ClassesHandler implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
    ClassMenu menu = new ClassMenu();
    if (sender instanceof Player) {
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
    else {
      sender.sendMessage(ChatColor.RED + "This command cannot be run from the console.");
      return true;
    }
  }
}