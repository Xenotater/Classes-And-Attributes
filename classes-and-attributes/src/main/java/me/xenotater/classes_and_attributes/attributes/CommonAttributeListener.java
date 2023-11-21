package me.xenotater.classes_and_attributes.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.entity.EntityMountEvent;

import com.jeff_media.customblockdata.CustomBlockData;

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
    AttributeName diet = getDiet(player);
    if (diet == null) {
      Runnable dietAssigner = new Runnable() {
        public void run() {
          Plugin.plugin.dataManager.changeDiet(player.getUniqueId(), AttributeName.getRandomDiet().getName());
        }
      };
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, dietAssigner, 5);
    }

    AttributeName curse = getCurse(player);
    if (curse != null) {
      ((GenericCurse) attributes.get(curse)).startTimer(player);
    }
  }

  @EventHandler
  private void onEat(final PlayerItemConsumeEvent e) {
    Player player = e.getPlayer();
    ItemStack item = e.getItem();
    List<AttributeName> playerAttributes = getPlayerAttributes(player);
    AttributeName diet = getDiet(player);

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
        triggerEffect(AttributeName.GOURMAND, player, e);
    }
  }

  @EventHandler
  private void onInteract(final PlayerInteractEvent e) {
    Player player = e.getPlayer();
    Block block = e.getClickedBlock();
    AttributeName diet = getDiet(player);
    List<AttributeName> playerAttributes = getPlayerAttributes(player);
    
    if (block != null && block.getType() == Material.CAKE) {
      ((GenericDiet) attributes.get(diet)).checkFood(player, new ItemStack(Material.CAKE));

      //Gourmand Condition
      if (playerAttributes.contains(AttributeName.GOURMAND))
        triggerEffect(AttributeName.GOURMAND, player, e);
    }

      //Thermophobia Condition
      if (playerAttributes.contains(AttributeName.THERMOPHOBIA))
        triggerEffect(AttributeName.THERMOPHOBIA, player, e);
  }

  @EventHandler
  private void onHeal(final EntityRegainHealthEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      AttributeName curse = getCurse(player);

      //Hemophilia Effect
      if (curse == AttributeName.HEMOPHILIA)
        triggerEffect(curse, player, e);
    }
  }

  @EventHandler
  private void onEffect(final EntityPotionEffectEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      AttributeName diet = getDiet(player);
      AttributeName curse = getCurse(player);
      List<AttributeName> playerAttributes = getPlayerAttributes(player);

      //Chemical Interest Effect
      if (playerAttributes.contains(AttributeName.CHEMICAL_INTEREST))
        triggerEffect(AttributeName.CHEMICAL_INTEREST, player, e);

      //Addictive Personality Effect
      if (playerAttributes.contains(AttributeName.ADDICTIVE))
        triggerEffect(AttributeName.ADDICTIVE, player, e);

      //Cannibal Effect
      if (diet == AttributeName.CANNIBAL)
        triggerEffect(diet, player, e);

      //Voidtouched & Starvation Effect
      if (curse == AttributeName.VOIDTOUCHED || curse == AttributeName.STARVATION)
        triggerEffect(curse, player, e);
    }
  }

  @EventHandler
  private void onDamageByEntity(final EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player) {
      Player player = (Player) e.getDamager();
      AttributeName curse = getCurse(player);
      List<AttributeName> playerAttributes = getPlayerAttributes(player);

      //Pacifist Effect
      if (curse == AttributeName.PACIFIST)
        triggerEffect(curse, player, e);

      //Friend of the Nether Effect
      if (playerAttributes.contains(AttributeName.NETHER_FRIEND))
        triggerEffect(AttributeName.NETHER_FRIEND, player, e);

      //Bronze Age Effect
      if (playerAttributes.contains(AttributeName.BRONZE_AGE))
        triggerEffect(AttributeName.BRONZE_AGE, player, e);
    }
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      List<AttributeName> playerAttributes = getPlayerAttributes(player);

      //Wrathful Effect
      if (playerAttributes.contains(AttributeName.WRATHFUL))
        triggerEffect(AttributeName.WRATHFUL, player, e);
    }
  }

  @EventHandler
  private void onDamage(final EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      List<AttributeName> playerAttributes = getPlayerAttributes(player);

      //Tough Effect
      if (playerAttributes.contains(AttributeName.TOUGH))
        triggerEffect(AttributeName.TOUGH, player, e);
    }
  }

  @EventHandler
  private void onMove(final PlayerMoveEvent e) {
    Player player = e.getPlayer();
    List<AttributeName> playerAttributes = getPlayerAttributes(player);
    AttributeName curse = getCurse(player);

    //Aquaphobia Attribute
    if (playerAttributes.contains(AttributeName.AQUAPHOBIA))
      triggerEffect(AttributeName.AQUAPHOBIA, player, e);

    //Compulsive Miner Effect
    if (playerAttributes.contains(AttributeName.COMPULSIVE_MINER))
      triggerEffect(AttributeName.COMPULSIVE_MINER, player, e);

    //Motion Sensitivity Effect
    if (playerAttributes.contains(AttributeName.MOTION_WEAKNESS))
      triggerEffect(AttributeName.MOTION_WEAKNESS, player, e);

    //Sunlight Sensitivity Effect
    if (playerAttributes.contains(AttributeName.SUNLIGHT_WEAKNESS))
      triggerEffect(AttributeName.SUNLIGHT_WEAKNESS, player, e);

    //Dead Weight Effect
    if (curse == AttributeName.DEAD_WEIGHT)
        triggerEffect(curse, player, e);
  }

  @EventHandler
  private void onAnvil(final PrepareAnvilEvent e) {
    Player player = (Player) e.getInventory().getViewers().get(0);
    List<AttributeName> playerAttributes = getPlayerAttributes(player);

    //Expert Smith Effect
    if (playerAttributes.contains(AttributeName.EXPERT_SMITH))
        triggerEffect(AttributeName.EXPERT_SMITH, player, e);
  }

  @EventHandler
  private void onInventory(final InventoryClickEvent e) {
    Player player = (Player) e.getWhoClicked();
    List<AttributeName> playerAttributes = getPlayerAttributes(player);

    //Expert Smith Effect
    if (playerAttributes.contains(AttributeName.EXPERT_SMITH))
        triggerEffect(AttributeName.EXPERT_SMITH, player, e);
  }

  @EventHandler
  private void onTarget(final EntityTargetEvent e) {
    if (e.getTarget() instanceof Player) {
      Player player = (Player) e.getTarget();
      List<AttributeName> playerAttributes = getPlayerAttributes(player);

      //Friend of the Nether Effect
      if (playerAttributes.contains(AttributeName.NETHER_FRIEND))
        triggerEffect(AttributeName.NETHER_FRIEND, player, e);

      //Well Rested Effect
      if (playerAttributes.contains(AttributeName.WELL_RESTED))
        triggerEffect(AttributeName.WELL_RESTED, player, e);
    }
  }

  @EventHandler
  private void onDeath(final EntityDeathEvent e) {
    if (e.getEntity().getKiller() != null) {
      Player player = e.getEntity().getKiller();
      List<AttributeName> playerAttributes = getPlayerAttributes(player);

      //Scavenger Effect
      if (playerAttributes.contains(AttributeName.SCAVENGER))
        triggerEffect(AttributeName.SCAVENGER, player, e);
    }
  }

  @EventHandler
  private void onPlace(final BlockPlaceEvent e) {
    //Add data for non-natural block placements
    Block block = e.getBlock();
    PersistentDataContainer placedData = new CustomBlockData(block, Plugin.plugin);
    NamespacedKey key = new NamespacedKey(Plugin.plugin, "placed_by_player");
    placedData.set(key, PersistentDataType.BOOLEAN, true);

    Player player = e.getPlayer();
    List<AttributeName> playerAttributes = getPlayerAttributes(player);

    //Bad Taste Effect
    if (playerAttributes.contains(AttributeName.BAD_TASTE))
      triggerEffect(AttributeName.BAD_TASTE, player, e);
  }

  @EventHandler
  private void onBreak(final BlockBreakEvent e) {
    Player player = e.getPlayer();
    List<AttributeName> playerAttributes = getPlayerAttributes(player);

    //Thourough Miner Effect
    if (playerAttributes.contains(AttributeName.THOUROUGH_MINER))
      triggerEffect(AttributeName.THOUROUGH_MINER, player, e);

    //Bronze Age Effect
    if (playerAttributes.contains(AttributeName.BRONZE_AGE))
      triggerEffect(AttributeName.BRONZE_AGE, player, e);

    //Compulsive Miner Effect
    if (playerAttributes.contains(AttributeName.COMPULSIVE_MINER))
      triggerEffect(AttributeName.COMPULSIVE_MINER, player, e);
  }

  @EventHandler
  private void onMount(final EntityMountEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
    List<AttributeName> playerAttributes = getPlayerAttributes(player);

    //Motion Sensitivity Effect
    if (playerAttributes.contains(AttributeName.MOTION_WEAKNESS))
      triggerEffect(AttributeName.MOTION_WEAKNESS, player, e);
    }
  }

  private void triggerEffect(AttributeName attribute, Player player, Event event) {
    attributes.get(attribute).triggerEffect(player, event);
  }

  private List<AttributeName> getPlayerAttributes(Player player) {
    return Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());
  }

  private AttributeName getDiet(Player player) {
    return Plugin.plugin.dataManager.getDiet(player.getUniqueId());
  }

  private AttributeName getCurse(Player player) {
    return Plugin.plugin.dataManager.getCurse(player.getUniqueId());
  }
}