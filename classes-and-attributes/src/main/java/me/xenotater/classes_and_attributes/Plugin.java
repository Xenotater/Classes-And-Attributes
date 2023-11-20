package me.xenotater.classes_and_attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.codingforcookies.armorequip.ArmorListener;
import com.codingforcookies.armorequip.DispenserArmorListener;
import com.jeff_media.customblockdata.CustomBlockData;

import me.xenotater.classes_and_attributes.attributes.AttributeMenu;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import me.xenotater.classes_and_attributes.attributes.AttributesCompleter;
import me.xenotater.classes_and_attributes.attributes.AttributesHandler;
import me.xenotater.classes_and_attributes.attributes.CommonAttributeListener;
import me.xenotater.classes_and_attributes.attributes.objects.BronzeAge;
import me.xenotater.classes_and_attributes.attributes.objects.GenericAttribute;
import me.xenotater.classes_and_attributes.classes.ClassMenu;
import me.xenotater.classes_and_attributes.classes.ClassName;
import me.xenotater.classes_and_attributes.classes.ClassesCompleter;
import me.xenotater.classes_and_attributes.classes.ClassesHandler;
import me.xenotater.classes_and_attributes.classes.CommonClassListener;
import me.xenotater.classes_and_attributes.classes.objects.*;
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

  private AttributesHandler attributesHandler = new AttributesHandler();
  private AttributesCompleter attributesCompleter = new AttributesCompleter();
  private AttributeMenu attributeMenu = new AttributeMenu();
  private CommonAttributeListener attributeListener = new CommonAttributeListener();

  public Map<UUID, StopWatch> abilityCooldowns = new HashMap<>();
  public Map<UUID, StopWatch> dietCooldowns = new HashMap<>();
  public Map<UUID, Timer> curseTimers = new HashMap<>();

  public Map<ClassName, GenericClass> classes = new HashMap<>();
  public Map<AttributeName, GenericAttribute> attributes = new HashMap<>();

  public void onEnable()
  {
    plugin = this;
    dataManager.initialize();
    LOGGER.info("Initializing Classes...");
    initClassCommands();
    initClasses();
    LOGGER.info("Initializing Attributes...");
    initAttributeCommands();
    initAttributes();
    LOGGER.info("Initializing events and listeners...");
    registerEvents();
    LOGGER.info("Creating custom recipes...");
    createRecipes();
    CustomBlockData.registerListener(plugin);
    LOGGER.info("Classes & Attributes Enabled");
  }

  public void onDisable()
  {
    LOGGER.info("Classes & Attributes Disabled");
  }

  private void registerEvents() {
    getServer().getPluginManager().registerEvents(armorListener, plugin);
    getServer().getPluginManager().registerEvents(dispenserListener, plugin);
    getServer().getPluginManager().registerEvents(classMenu, plugin);
    getServer().getPluginManager().registerEvents(classListener, plugin);
    getServer().getPluginManager().registerEvents(attributeMenu, plugin);
    getServer().getPluginManager().registerEvents(attributeListener, plugin);
  }

  private void initClassCommands() {
    initCommand("classes", classesHandler, classesCompleter);
    initCommand("setclass", classesHandler, classesCompleter);
    initCommand("removeclass", classesHandler, classesCompleter);
  }

  private void initAttributeCommands() {
    initCommand("attributes", attributesHandler, attributesCompleter);
    initCommand("addattribute", attributesHandler, attributesCompleter);
    initCommand("removeattribute", attributesHandler, attributesCompleter);
    initCommand("changediet", attributesHandler, attributesCompleter);
    initCommand("setcurse", attributesHandler, attributesCompleter);
  }

  private void initCommand(String name, CommandExecutor handler, TabCompleter completer) {
    getCommand(name).setExecutor(handler);
    getCommand(name).setTabCompleter(completer);
  }

  private void initClasses() {
    classes.put(ClassName.ASSASSIN, new Assassin());
    classes.put(ClassName.BERSERKER, new Berserker());
    classes.put(ClassName.CLERIC, new Cleric());
    classes.put(ClassName.KNIGHT, new Knight());
    classes.put(ClassName.MAGE, new Mage());
    classes.put(ClassName.PYROMANCER, new Pyromancer());
    classes.put(ClassName.SHAMAN, new Shaman());
    classes.put(ClassName.RANGER, new Ranger());
    classListener.setClasses(classes);
  }

  private void initAttributes() {
    try {
      for (AttributeName attribute : AttributeName.values()) {
        Class<?> cls = Class.forName("me.xenotater.classes_and_attributes.attributes.objects." + attribute.getName().replace(" ", ""));
        attributes.put(attribute, (GenericAttribute) cls.newInstance());
      }
      attributeListener.setAttributes(attributes);
    }
    catch (Exception e) {
      LOGGER.warning("Error loading Attributes: " + e);
      getServer().getPluginManager().disablePlugin(plugin);
    }
  }

  private void createRecipes() {
    ((Mage) classes.get(ClassName.MAGE)).createBrewerRecipe();
    ((BronzeAge) attributes.get(AttributeName.BRONZE_AGE)).createToolRecipes();
  }
}
