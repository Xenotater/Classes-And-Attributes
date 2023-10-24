package me.xenotater.classes_and_attributes;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.codingforcookies.armorequip.ArmorListener;
import com.codingforcookies.armorequip.DispenserArmorListener;

import me.xenotater.classes_and_attributes.classes.ClassMenu;
import me.xenotater.classes_and_attributes.classes.ClassesCompleter;
import me.xenotater.classes_and_attributes.classes.ClassesHandler;
import me.xenotater.classes_and_attributes.classes.listeners.CommonClassListener;
import me.xenotater.classes_and_attributes.common.DataManager;

/*
 * classes-and-attributes java plugin
 */
public class Plugin extends JavaPlugin
{
  public static Plugin plugin;
  public final Logger LOGGER=Logger.getLogger("classes-and-attributes");
  public DataManager dataManager = new DataManager();

  private ArmorListener armorListener = new ArmorListener(new ArrayList<String>());
  private DispenserArmorListener dispenserListener = new DispenserArmorListener();

  private ClassesHandler classesHandler = new ClassesHandler();
  private ClassesCompleter classesCompleter = new ClassesCompleter();
  private ClassMenu classMenu = new ClassMenu();
  private CommonClassListener classListener = new CommonClassListener();

  public void onEnable()
  {
    plugin = this;
    dataManager.initialize();
    initClassCommands();
    registerEvents();
    LOGGER.info("classes-and-attributes enabled");
  }

  public void onDisable()
  {
    LOGGER.info("classes-and-attributes disabled");
  }

  private void registerEvents() {
    getServer().getPluginManager().registerEvents(armorListener, plugin);
    getServer().getPluginManager().registerEvents(dispenserListener, plugin);
    getServer().getPluginManager().registerEvents(classMenu, plugin);
    getServer().getPluginManager().registerEvents(classListener, plugin);
  }

  private void initClassCommands() {
    initCommand("classes", classesHandler, classesCompleter);
    initCommand("setclass", classesHandler, classesCompleter);
    initCommand("removeclass", classesHandler, classesCompleter);
  }

  private void initCommand(String name, CommandExecutor handler, TabCompleter completer) {
    getCommand(name).setExecutor(handler);
    getCommand(name).setTabCompleter(completer);
  }
}
