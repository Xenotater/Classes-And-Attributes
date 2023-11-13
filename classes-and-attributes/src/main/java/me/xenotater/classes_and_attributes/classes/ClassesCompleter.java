package me.xenotater.classes_and_attributes.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.common.AbstractTabCompleter;

public class ClassesCompleter extends AbstractTabCompleter {
  List<String> classesList = new ArrayList<>();

  public ClassesCompleter() {
    setClassesList();
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (command.getName().equals("classes")) {
      if (args.length > 1)
        return new ArrayList<>();
      return filterCompletions(classesList, args[0]);
    }
    else if (command.getName().equals("setclass")) {
      if (args.length > 1) {
        if (sender.isOp()) {
          if (args.length > 2)
            return new ArrayList<>();
          return filterCompletions(classesList, args[1]);
        }
        return new ArrayList<>();
      }
      if (sender.isOp()) {
        List<String> playerList = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers())
          playerList.add(p.getName());
        return filterCompletions(playerList, args[0]);
      }
      return filterCompletions(classesList, args[0]);
    }
    else if (command.getName().equals("removeclass")) {
      List<String> playerList = new ArrayList<>();
      if (sender.isOp()) {
        if (args.length > 1)
          return playerList;
        for (Player p : Bukkit.getOnlinePlayers())
          playerList.add(p.getName());
        return filterCompletions(playerList, args[0]);
      }
      return playerList;
    }
    return null;
  }

  private void setClassesList() {
    ClassName[] classes = ClassName.values();
    for (ClassName className : classes)
      classesList.add(className.getName());
  }
}
