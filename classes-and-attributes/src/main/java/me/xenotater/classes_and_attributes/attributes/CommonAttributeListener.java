package me.xenotater.classes_and_attributes.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.objects.GenericAttribute;
import me.xenotater.classes_and_attributes.attributes.objects.GenericCurse;
import me.xenotater.classes_and_attributes.attributes.objects.GenericDiet;

public class CommonAttributeListener implements Listener {
  Map<AttributeName, GenericAttribute> attributes = new HashMap<>();

  public void setAttributes(Map<AttributeName, GenericAttribute> attrMap) {
    attributes = attrMap;
  }

  @EventHandler
  private void onLogin(final PlayerLoginEvent e) {
    Player player = e.getPlayer();
    AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());
    if (diet == null) {
      Runnable dietAssigner = new Runnable() {
        @Override
        public void run() {
          Plugin.plugin.dataManager.changeDiet(player.getUniqueId(), AttributeName.getRandomDiet().getName());
        }
      };
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, dietAssigner, 5);
    }

    AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());
    if (curse != null) {
      ((GenericCurse) attributes.get(curse)).startTimer(player);
    }
  }

  @EventHandler
  private void onEat(final PlayerItemConsumeEvent e) {
    Player player = e.getPlayer();
    ItemStack item = e.getItem();
    List<AttributeName> playerAttributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());
    AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());

    if (item.getType().isEdible())
      ((GenericDiet) attributes.get(diet)).checkFood(player, item);

    if (diet == AttributeName.CANNIBAL) {
      if (item.getType().equals(Material.ROTTEN_FLESH))
        player.removePotionEffect(PotionEffectType.HUNGER);
      if (item.getType().equals(Material.SPIDER_EYE))
        player.removePotionEffect(PotionEffectType.POISON);
    }

    //Gourmand Condition
    if (playerAttributes.contains(AttributeName.GOURMAND)) {
      attributes.get(AttributeName.GOURMAND).checkCondition(e);
    }
  }

  @EventHandler
  private void onInteract(final PlayerInteractEvent e) {
    Player player = e.getPlayer();
    Block block = e.getClickedBlock();
    AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());
    List<AttributeName> playerAttributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());
    
    if (block != null && block.getType() == Material.CAKE) {
      ((GenericDiet) attributes.get(diet)).checkFood(player, new ItemStack(Material.CAKE));;

      //Gourmand Condition
      if (playerAttributes.contains(AttributeName.GOURMAND)) {
        attributes.get(AttributeName.GOURMAND).checkCondition(e);
      }
    }
  }
}
