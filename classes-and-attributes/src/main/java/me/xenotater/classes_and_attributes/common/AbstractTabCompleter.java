package me.xenotater.classes_and_attributes.common;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public abstract class AbstractTabCompleter implements TabCompleter{
  @Override
  public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);

  protected List<String> filterCompletions(List<String> list, String arg) {
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
