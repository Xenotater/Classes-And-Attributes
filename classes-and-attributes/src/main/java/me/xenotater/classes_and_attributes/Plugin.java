package me.xenotater.classes_and_attributes;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import me.xenotater.classes_and_attributes.classes.ClassMenu;
import me.xenotater.classes_and_attributes.classes.ClassesCompleter;
import me.xenotater.classes_and_attributes.classes.ClassesHandler;

/*
 * classes-and-attributes java plugin
 */
public class Plugin extends JavaPlugin
{
  public static final Logger LOGGER=Logger.getLogger("classes-and-attributes");
  private ClassesHandler classesHandler = new ClassesHandler();
  private ClassesCompleter classesCompleter = new ClassesCompleter();
  private ClassMenu classMenu = new ClassMenu();

  public void onEnable()
  {
    getCommand("classes").setExecutor(classesHandler);
    getCommand("classes").setTabCompleter(classesCompleter);
    getServer().getPluginManager().registerEvents(classMenu, this);
    LOGGER.info("classes-and-attributes enabled");
  }

  public void onDisable()
  {
    LOGGER.info("classes-and-attributes disabled");
  }
}
