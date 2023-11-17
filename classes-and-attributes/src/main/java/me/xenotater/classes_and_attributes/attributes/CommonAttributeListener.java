package me.xenotater.classes_and_attributes.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
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
      attributes.get(AttributeName.GOURMAND).checkCondition(player, e);
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
      if (playerAttributes.contains(AttributeName.GOURMAND))
        attributes.get(AttributeName.GOURMAND).checkCondition(player, e);
    }
  }

  @EventHandler
  private void onHeal(final EntityRegainHealthEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());

      //Hemophilia Effect
      if (curse == AttributeName.HEMOPHILIA)
        attributes.get(curse).triggerEffect(player, e);
    }
  }

  @EventHandler
  private void onEffect(final EntityPotionEffectEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());
      AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());
      List<AttributeName> playerAttributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());

      //Chemical Interest
      if (playerAttributes.contains(AttributeName.CHEMICAL_INTEREST))
        attributes.get(AttributeName.CHEMICAL_INTEREST).triggerEffect(player, e);

      //Cannibal Effect
      if (diet == AttributeName.CANNIBAL)
        attributes.get(diet).triggerEffect(player, e);

      //Voidtouched & Starvation Effect
      if (curse == AttributeName.VOIDTOUCHED || curse == AttributeName.STARVATION)
        attributes.get(curse).triggerEffect(player, e);
    }
  }

  @EventHandler
  private void onDamage(final EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player) {
      Player player = (Player) e.getDamager();
      AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());

      //Pacifist Effect
      if (curse == AttributeName.PACIFIST)
        attributes.get(curse).triggerEffect(player, e);
    }
  }

  @EventHandler
  private void onMove(final PlayerMoveEvent e) {
    Player player = e.getPlayer();
    AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());

    //Dead Weight Effect
    if (curse == AttributeName.DEAD_WEIGHT)
      attributes.get(curse).triggerEffect(player, e);
  }

  @EventHandler
  private void onAnvil(final PrepareAnvilEvent e) {
    Player player = (Player) e.getInventory().getViewers().get(0);
    List<AttributeName> playerAttributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());

    //Expert Smith Effect
    if (playerAttributes.contains(AttributeName.EXPERT_SMITH))
      attributes.get(AttributeName.EXPERT_SMITH).triggerEffect(player, e);
  }

  @EventHandler
  private void onInventory(final InventoryClickEvent e) {
    Player player = (Player) e.getViewers().get(0);
    List<AttributeName> playerAttributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());

    //Expert Smith Effect
    if (playerAttributes.contains(AttributeName.EXPERT_SMITH))
      attributes.get(AttributeName.EXPERT_SMITH).triggerEffect(player, e);
  }
}