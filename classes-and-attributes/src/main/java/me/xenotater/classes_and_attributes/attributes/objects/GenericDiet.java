package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.xenotater.classes_and_attributes.Plugin;
import net.md_5.bungee.api.ChatColor;

public abstract class GenericDiet extends GenericAttribute {
  protected PotionEffect buff;
  protected List<String> allowedFoods;
  protected List<String> disallowedFoods;

  public GenericDiet() {
    allowedFoods = new ArrayList<>();
    disallowedFoods = new ArrayList<>();
  }

  @Override
  public boolean addForPlayer(Player p) {
    boolean success = Plugin.plugin.dataManager.setCurse(p.getUniqueId(), name);
    if (success) {
      p.sendMessage(ChatColor.YELLOW + " > " + ChatColor.WHITE + "Your diet was changed to: " + ChatColor.YELLOW + name + ChatColor.WHITE + ".");
      startCooldown(p);
    }
    return success;
  }

  @Override
  public boolean removeForPlayer(Player p) {
    return false; //diets cannot be removed, only changed
  }

  @Override
  public boolean breakForPlayer(Player p) {
    p.sendMessage(ChatColor.YELLOW + "This food isn't in your diet! Diet buffs disabled for 10 minutes.");
    revokeBuff(p);
    startCooldown(p);
    return true;
  }

  public void checkFood(Player p, ItemStack foodItem) {
    if (!isValidFood(p, foodItem))
      breakForPlayer(p);
    else
      grantBuff(p);
  }

  public boolean isValidFood(Player p, ItemStack foodItem) {
    String itemName = foodItem.getType().name().toLowerCase();
    boolean inAllowed = allowedFoods.stream().anyMatch(allowed -> itemName.contains(allowed));
    boolean inDisallowed = disallowedFoods.stream().anyMatch(disallowed -> itemName.contains(disallowed));
    return (inAllowed || allowedFoods.isEmpty()) && !inDisallowed;
  }

  public void grantBuff(Player p) {
    if (!isOnCooldown(p))
      p.addPotionEffect(buff);
  }

  public void revokeBuff(Player p) {
    p.removePotionEffect(buff.getType());
  }

  public boolean isOnCooldown(Player p) {
    if (getCooldown(p) > 0)
      return true;
    return false;
  }

  public long getCooldown(Player p) {
    StopWatch cooldown = Plugin.plugin.dietCooldowns.get(p.getUniqueId());
    if (cooldown != null)
      return 600000 - cooldown.getTime();
    return 0;
  }

  public void startCooldown(Player p) {
    StopWatch cooldown = Plugin.plugin.dietCooldowns.get(p.getUniqueId());
    if (cooldown == null)
      cooldown = new StopWatch();
    if (cooldown.isStarted()) {
      cooldown.stop();
      cooldown.reset();
    }
    cooldown.start();
    Plugin.plugin.dietCooldowns.put(p.getUniqueId(), cooldown);
  }
}
