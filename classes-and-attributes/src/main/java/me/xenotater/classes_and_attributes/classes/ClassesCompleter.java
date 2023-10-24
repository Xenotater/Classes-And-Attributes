package me.xenotater.classes_and_attributes.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ClassesCompleter implements TabCompleter {
  String[] classes = {"Random", "Assassin", "Berserker", "Cleric", "Knight", "Mage", "Pyromancer", "Shaman", "Ranger"};
  List<String> classesList = Arrays.asList(classes);

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

  private List<String> filterCompletions(List<String> list, String arg) {
    if (arg.equals(""))
      return list;
    List<String> filteredList = new ArrayList<>();
    for (String option: list) {
      if (option.regionMatches(true, 0, arg, 0, arg.length()))
        filteredList.add(option);
    }
    return filteredList;
  }
}
