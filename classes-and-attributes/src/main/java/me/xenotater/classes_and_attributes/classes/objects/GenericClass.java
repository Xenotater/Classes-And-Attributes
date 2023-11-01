package me.xenotater.classes_and_attributes.classes.objects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.ClassItemType;
import me.xenotater.classes_and_attributes.classes.ClassName;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public abstract class GenericClass extends Object{
  protected String[] disallowedArmor;
  protected String[] disallowedWeapons;
  protected String[] disallowedInteracts;
  protected Map<ClassItemType, Boolean> enchantsAllowed = new HashMap<>();
  protected long abilityCooldown;
  protected long abilityDuration;
  
  public GenericClass() {
    disallowedArmor = new String[]{};
    disallowedInteracts = new String[]{};
    disallowedWeapons = new String[]{};
    for (ClassItemType itemType : ClassItemType.values())
      enchantsAllowed.put(itemType, true);
  }

  public String[] getDisallowedArmor() {
    return disallowedArmor;
  }

  public String[] getDisallowedWeapons() {
    return disallowedWeapons;
  }

  public String[] getDisallowedInteracts() {
    return disallowedInteracts;
  }

  public boolean getEnchantsAllowed(ClassItemType itemType) {
    return enchantsAllowed.get(itemType);
  }

  public abstract void triggerPassive(Player p, Event e);
  public abstract void triggerActive(Player p, Event e);

  public void notifyAbilityTriggered(Player p, String name) {
    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "** Used " + name + " **"));
  }

  public void notifyAbilityCooldown(Player p, String name) {
    long time = getAbilityCooldown(p);
    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Cannot use " + name + " for " + ((time / 1000) + 1) + "s"));
  }

  public void startAbilityCooldown(Player p) {
    StopWatch cooldown = Plugin.plugin.abilityCooldowns.get(p.getUniqueId());
    if (cooldown == null)
      cooldown = new StopWatch();
    if (cooldown.isStarted()) {
      cooldown.stop();
      cooldown.reset();
    }
    cooldown.start();
    Plugin.plugin.abilityCooldowns.put(p.getUniqueId(), cooldown);
  }

  public boolean isAbilityReady(Player p) {
    if (getAbilityCooldown(p) > 0)
      return false;
    return true;
  }

  public long getAbilityCooldown(Player p) {
    StopWatch cooldown = Plugin.plugin.abilityCooldowns.get(p.getUniqueId());
    if (cooldown != null)
      return abilityCooldown - cooldown.getTime();
    return 0;
  }

  public boolean isAbilityActive(Player p) {
    long cooldownTime = getAbilityCooldown(p);
    return cooldownTime > abilityCooldown - abilityDuration;
  }

  public void checkArmor(Player player, ClassName className) {
    ItemStack[] armor = player.getInventory().getArmorContents();
    for (ItemStack item : armor) {
      if (item != null && !isValidArmor(item, className)) {
        ItemStack replacement = item.clone();
        item.setAmount(0);
        player.getWorld().dropItem(player.getLocation(), replacement);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't equip " + replacement.getType().name() + " with your current class!"));
      }
    }
  }
  
  public boolean isValidArmor(ItemStack item, ClassName className) {
    String itemName = item.getType().name().toLowerCase();
    List<String> disallowedList = Arrays.asList(getDisallowedArmor());
    boolean enchantsAllowed = getEnchantsAllowed(ClassItemType.ARMOR);
    
    if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)) && !itemName.contains("turtle"))
      return false;
    if (item.getEnchantments().size() > 0 && !enchantsAllowed)
      return false;
    return true;
  }

  public boolean isValidInteract(ItemStack item, ClassName className) {
    String itemName = item.getType().name().toLowerCase();
    List<String> disallowedList = Arrays.asList(getDisallowedInteracts());
    boolean tridentEnchantsAllowed = getEnchantsAllowed(ClassItemType.TRIDENT);
    boolean bowEnchantsAllowed = getEnchantsAllowed(ClassItemType.BOW);

    if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)))
      return false;
    if (itemName.equals("trident") && item.getEnchantments().size() > 0 && !tridentEnchantsAllowed)
      return false;
    if ((itemName.equals("bow") || itemName.equals("crossbow")) && item.getEnchantments().size() > 0 && !bowEnchantsAllowed)
      return false;
    return true;
  }

  public boolean isValidWeapon(ItemStack item, ClassName className) {
    String itemName = item.getType().name().toLowerCase();
    List<String> disallowedList = Arrays.asList(getDisallowedWeapons());
    boolean weaponEnchantsAllowed = getEnchantsAllowed(ClassItemType.WEAPON);
    boolean tridentEnchantsAllowed = getEnchantsAllowed(ClassItemType.TRIDENT);

    if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)))
      return false;
    if (itemName.equals("trident") && item.getEnchantments().size() > 0 && !tridentEnchantsAllowed)
      return false;
    else if (item.getEnchantments().size() > 0 && !weaponEnchantsAllowed)
      return false;
    return true;
  }
}
