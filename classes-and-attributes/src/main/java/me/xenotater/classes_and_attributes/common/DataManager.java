package me.xenotater.classes_and_attributes.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import me.xenotater.classes_and_attributes.classes.ClassName;
import me.xenotater.classes_and_attributes.classes.objects.Ranger;

public class DataManager {
  private final String filename = "player-data.yml";
  private File dataFile;
  private FileConfiguration fileConfig;

  public DataManager() {}

  public void initialize() {
    dataFile = new File(Plugin.plugin.getDataFolder(), filename);
    fileConfig = YamlConfiguration.loadConfiguration(dataFile);
  }

  private boolean save() {
    try {
      fileConfig.save(dataFile);
    }
    catch (IOException e) {
      Plugin.plugin.LOGGER.warning("Couldn't save player data: " + e);
      return false;
    }
    return true;
  }

  public boolean setClass(UUID id, String className) {
    Player p = Bukkit.getPlayer(id);
    ClassName cName = ClassName.getValue(className);
    if (getClass(id) == ClassName.RANGER) ((Ranger) Plugin.plugin.classes.get(ClassName.RANGER)).disablePassive(p); //remove ranger passive when switching off of it
    Plugin.plugin.LOGGER.info("Updating class for player " + p.getDisplayName() + ": " + className);
    fileConfig.set(id + ".class", className);
    boolean saved = save();
    if (getClass(id) == ClassName.RANGER)  Plugin.plugin.classes.get(ClassName.RANGER).triggerPassive(p , null); //trigger passive if new class is ranger, or retrigger if failed to save
    if (saved) Plugin.plugin.classes.get(cName).checkArmor(p, cName);
    return saved;
  }

  public ClassName getClass(UUID id) {
    return ClassName.getValue(fileConfig.getString(id + ".class"));
  }

  public boolean addAttribute(UUID id, String attribute) {
    Player p = Bukkit.getPlayer(id);
    Plugin.plugin.LOGGER.info("Updating attributes for player " + p.getDisplayName() + ": +" + attribute);
    List<String> attributes = getAttributeStrings(id);
    attributes.add(attribute);
    fileConfig.set(id + ".attributes", attributes);
    return save();
  }

  public boolean removeAttribute(UUID id, String attribute) {
    Player p = Bukkit.getPlayer(id);
    Plugin.plugin.LOGGER.info("Updating attributes for player " + p.getDisplayName() + ": -" + attribute);
    List<String> attributes = getAttributeStrings(id);
    attributes.remove(attribute);
    fileConfig.set(id + ".attributes", attributes);
    return save();
  }

  public List<AttributeName> getAttibutes(UUID id) {
    List<AttributeName> attributes = new ArrayList<>();
    List<String> attributesStrings = getAttributeStrings(id);
    for (String attribute : attributesStrings)
      attributes.add(AttributeName.getValue(attribute));
    return attributes;
  }

  public List<String> getAttributeStrings(UUID id) {
    return fileConfig.getStringList(id + ".attributes");
  }

  public boolean changeDiet(UUID id, String diet) {
    Player p = Bukkit.getPlayer(id);
    Plugin.plugin.LOGGER.info("Updating diet for player " + p.getDisplayName() + ": " + diet);
    fileConfig.set(id + ".diet", diet);
    return save();
  }

  public AttributeName getDiet(UUID id) {
    return AttributeName.getValue(fileConfig.getString(id + ".diet"));
  }

  public boolean setCurse(UUID id, String curse) {
    Player p = Bukkit.getPlayer(id);
    Plugin.plugin.LOGGER.info("Updating curse for player " + p.getDisplayName() + ": " + curse);
    fileConfig.set(id + ".curse", curse);
    return save();
  }

  public AttributeName getCurse(UUID id) {
    return AttributeName.getValue(fileConfig.getString(id + ".curse"));
  }
}