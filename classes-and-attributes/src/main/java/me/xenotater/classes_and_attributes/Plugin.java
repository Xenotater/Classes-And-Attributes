package me.xenotater.classes_and_attributes;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import me.xenotater.classes_and_attributes.classes.ClassMenu;
import me.xenotater.classes_and_attributes.classes.ClassesCompleter;
import me.xenotater.classes_and_attributes.classes.ClassesHandler;
import me.xenotater.classes_and_attributes.common.DataManager;

/*
 * classes-and-attributes java plugin
 */
public class Plugin extends JavaPlugin
{
  public static Plugin plugin;
  public final Logger LOGGER=Logger.getLogger("classes-and-attributes");
  public DataManager dataManager = new DataManager();
  private ClassesHandler classesHandler = new ClassesHandler();
  private ClassesCompleter classesCompleter = new ClassesCompleter();
  private ClassMenu classMenu = new ClassMenu();

  public void onEnable()
  {
    plugin = this;
    dataManager.initialize();
    getCommand("classes").setExecutor(classesHandler);
    getCommand("classes").setTabCompleter(classesCompleter);
    getCommand("setclass").setExecutor(classesHandler);
    getCommand("removeclass").setExecutor(classesHandler);
    getServer().getPluginManager().registerEvents(classMenu, this);
    LOGGER.info("classes-and-attributes enabled");
  }

  public void onDisable()
  {
    LOGGER.info("classes-and-attributes disabled");
  }
}
