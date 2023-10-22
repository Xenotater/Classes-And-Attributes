package me.xenotater.classes_and_attributes.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.ClassName;

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
    fileConfig.set(id + ".class", className);
    return save();
  }

  public ClassName getClass(UUID id) {
    return ClassName.getValue(fileConfig.getString(id + ".class"));
  }
}
