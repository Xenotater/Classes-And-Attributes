package me.xenotater.classes_and_attributes.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.Plugin;
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
    if (getClass(id) == ClassName.RANGER) new Ranger().disablePassive(p); //remove ranger passive when switching off of it
    Plugin.plugin.LOGGER.info("Updating class for player " + p.getDisplayName() + ": " + className);
    fileConfig.set(id + ".class", className);
    if (getClass(id) == ClassName.RANGER) new Ranger().triggerPassive(p , null); //trigger passive if new class is ranger, or retrigger if failed to save
    return save();
  }

  public ClassName getClass(UUID id) {
    return ClassName.getValue(fileConfig.getString(id + ".class"));
  }
}
