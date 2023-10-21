package me.xenotater.classes_and_attributes.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ClassesCompleter implements TabCompleter {
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    List<String> list = new ArrayList<>();
    if (command.getName().equals("classes")) {
      if (args.length > 1)
        return list;
      list.add("Assassin");
      list.add("Berserker");
      list.add("Cleric");
      list.add("Knight");
      list.add("Mage");
      list.add("Pyromancer");
      list.add("Shaman");
      list.add("Ranger");
      return filterCompletions(list, args[0]);
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
