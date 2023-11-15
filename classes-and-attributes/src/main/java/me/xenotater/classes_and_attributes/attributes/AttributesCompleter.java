package me.xenotater.classes_and_attributes.attributes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.common.AbstractTabCompleter;

public class AttributesCompleter extends AbstractTabCompleter {
  List<String> attribtuesList = new ArrayList<>();
  List<String> dietList = new ArrayList<>();
  List<String> curseList = new ArrayList<>();

  public AttributesCompleter() {
    setAttributesList();
    setDietList();
    setCurseList();
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (command.getName().equals("attributes")) {
      return new ArrayList<>();
    }
    else if (command.getName().equals("addattribute") && sender.isOp()) {
      List<String> playerList = new ArrayList<>();
      for (Player p : Bukkit.getOnlinePlayers())
        playerList.add(p.getName());
      if (args.length > 1) {
        Player target = Bukkit.getPlayerExact(args[0]);
        List<String> options = attribtuesList;
        if (target != null) {
          List<String> playerAttributes = Plugin.plugin.dataManager.getAttributeStrings(target.getUniqueId());
          options = ListUtils.subtract(attribtuesList, playerAttributes);
        }
        options.add("Random");
        return filterCompletions(options, args[1]);
      }
      return filterCompletions(playerList, args[0]);
    }
    else if (command.getName().equals("removeattribute") && sender.isOp()) {
      List<String> playerList = new ArrayList<>();
      for (Player p : Bukkit.getOnlinePlayers())
        playerList.add(p.getName());
      if (args.length > 1) {
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target != null) {
          List<String> playerAttributes = Plugin.plugin.dataManager.getAttributeStrings(Bukkit.getPlayerExact(args[0]).getUniqueId());
          for (String attribute : playerAttributes)
            attribute.replace(" ", "_");
          return filterCompletions(playerAttributes, args[1]);
        }
        return filterCompletions(attribtuesList, args[1]);
      }
      return filterCompletions(playerList, args[0]);
    }
    else if (command.getName().equals("changediet") && sender.isOp()) {
      List<String> playerList = new ArrayList<>();
      for (Player p : Bukkit.getOnlinePlayers())
        playerList.add(p.getName());
      if (args.length > 1) {
        return filterCompletions(dietList, args[1]);
      }
      return filterCompletions(playerList, args[0]);
    }
    else if (command.getName().equals("setcurse") && sender.isOp()) {
      List<String> playerList = new ArrayList<>();
      for (Player p : Bukkit.getOnlinePlayers())
        playerList.add(p.getName());
      if (args.length > 1) {
        return filterCompletions(curseList, args[1]);
      }
      return filterCompletions(playerList, args[0]);
    }
    return null;
  }

  private void setAttributesList() {
    List<AttributeName> attributes = AttributeName.regularAttributes;
    for (AttributeName attribute : attributes)
      attribtuesList.add(attribute.getName().replaceAll(" ", "_"));
  }

  private void setDietList() {
    List<AttributeName> diets = AttributeName.dietAttributes;
    for (AttributeName diet : diets)
      dietList.add(diet.getName().replaceAll(" ", "_"));
  }

  private void setCurseList() {
    List<AttributeName> curses = AttributeName.curseAttributes;
    for (AttributeName curse : curses)
      curseList.add(curse.getName().replaceAll(" ", "_"));
    curseList.add("None");
  }
}
