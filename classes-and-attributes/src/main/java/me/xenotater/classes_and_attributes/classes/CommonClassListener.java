package me.xenotater.classes_and_attributes.classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BrewingStartEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import com.jeff_media.customblockdata.CustomBlockData;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.objects.GenericClass;
import me.xenotater.classes_and_attributes.classes.objects.Ranger;
import me.xenotater.classes_and_attributes.classes.objects.Shaman;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CommonClassListener implements Listener {
  Map<ClassName, GenericClass> classes = new HashMap<>();

  public void setClasses(Map<ClassName, GenericClass> classMap) {
    classes = classMap;
  }

  @EventHandler
  private void onLogout(final PlayerQuitEvent e) {
    Plugin.plugin.abilityCooldowns.remove(e.getPlayer().getUniqueId());
  }

  @EventHandler
  private void onEquip(final ArmorEquipEvent e) {
    Player player = e.getPlayer();
    ItemStack item = e.getNewArmorPiece();
    ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());

    //Generic Armor Check
    if (className != null && item != null) {
      if (!classes.get(className).isValidArmor(item, className)) {
        e.setCancelled(true);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't equip " + item.getType().name() + " with your current class!"));
      }
    }
  }

  @EventHandler
  private void onInteract(final PlayerInteractEvent e) {
    if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
      Player player = e.getPlayer();
      ItemStack item = e.getItem();
      Block block = e.getClickedBlock();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());

      //Generic Weapon/Shield Check
      if (className != null && item != null) {
        if (!classes.get(className).isValidInteract(item, className)) {
            e.setCancelled(true);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't use " + item.getType().name() + " with your current class!"));
        }
      }

      //Restrict Mage Passive Ability
      if (block != null) {
        Boolean isMasterStand = new CustomBlockData(block, Plugin.plugin).get(new NamespacedKey(Plugin.plugin, "master_brewing_stand"), PersistentDataType.BOOLEAN);
        if (block != null && isMasterStand != null && isMasterStand) {
          if (className != ClassName.MAGE) {
            e.setCancelled(true);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't use that with your current class!"));
          }
        }
      }

      //All Active Abilities
      List<String> disallowedActivates = Arrays.asList(new String[] {"bow", "crossbow", "shield", "trident"});
      if (player.isSneaking() && item != null && !item.getType().isBlock() && !disallowedActivates.contains(item.getType().name().toLowerCase()))
        classes.get(className).triggerActive(player, e);
    }
  }

  @EventHandler
  private void onDamageEntity(final EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player) {
      Player player = (Player) e.getDamager();
      ItemStack item = player.getInventory().getItemInMainHand();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());

      //Generic Weapon Check
      if (className != null && item != null) {
        if (!classes.get(className).isValidWeapon(item, className)) {
          e.setCancelled(true);
          player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't use " + item.getType().name() + " with your current class!"));
        }
      }

      //Assassin & Cleric & Shaman Passive Abilities
      if (!e.isCancelled() && (className == ClassName.ASSASSIN || className == ClassName.CLERIC || className == ClassName.SHAMAN)) {
        classes.get(className).triggerPassive(player, e);
      }
    }

    //Berserker Passive Ability
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
      if (className == ClassName.BERSERKER)
        classes.get(ClassName.BERSERKER).triggerPassive(player, e);
    }

    //Shaman Active Ability
    if (e.getEntity() instanceof Vex)
      ((Shaman) classes.get(ClassName.SHAMAN)).checkSpirit((Vex) e.getEntity(), e);
    if (e.getDamager() instanceof Vex)
      ((Shaman) classes.get(ClassName.SHAMAN)).checkSpirit((Vex) e.getDamager(), e);
  }

  //Assassin Active Ability
  @EventHandler
  private void onTargetEntity(final EntityTargetEvent e) {
    if (e.getTarget() instanceof Player && e.getEntity() instanceof Creature) {
      Player player = (Player) e.getTarget();
      PotionEffect entityConfusion = ((Creature) e.getEntity()).getPotionEffect(PotionEffectType.CONFUSION);
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
      if (className == ClassName.ASSASSIN && classes.get(ClassName.ASSASSIN).isAbilityActive(player))
        e.setCancelled(true);
      if (entityConfusion != null && entityConfusion.getAmplifier() == 1) //Mage Confusion Spell
        e.setCancelled(true);
    }
  }

  //Knight Passive Ability
  @EventHandler
  private void onEffect(final EntityPotionEffectEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
      if (className == ClassName.KNIGHT)
        classes.get(ClassName.KNIGHT).triggerPassive(player, e);
    }
  }

  //Pyromancer Passive Ability
  @EventHandler
  private void onDamage(final EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
      if (className == ClassName.PYROMANCER)
        classes.get(ClassName.PYROMANCER).triggerPassive(player, e);
    }
  }

  //Ranger Passive Ability
  @EventHandler
  private void onLogin(final PlayerLoginEvent e) {
    Player player = e.getPlayer();
    ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
    if (className == ClassName.RANGER) {
      Runnable passiveTrigger = new Runnable() {@Override public void run() {classes.get(ClassName.RANGER).triggerPassive(player, e);}};
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, passiveTrigger, 5); 
    }
    else {
      Runnable disablePassive = new Runnable() {@Override public void run() {((Ranger) classes.get(ClassName.RANGER)).disablePassive(player);}};
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, disablePassive, 5); 
    }
  }

  //Ranger Passive Ability
  @EventHandler
  private void onDamaged(final EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
      if (e.getCause() == DamageCause.FALL && className == ClassName.RANGER)
        e.setCancelled(true);
    }
  }

  //..Ranger Passive Ability
  @EventHandler
  private void onDeath(final PlayerRespawnEvent e) {
    Player player = e.getPlayer();
    ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
    if (className == ClassName.RANGER)
      classes.get(ClassName.RANGER).triggerPassive(player, e);
  }

  //Restrict Mage Passive Ability
  @EventHandler
  private void onCraft(final CraftItemEvent e) {
    Player player = (Player) e.getWhoClicked();
    ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
    String itemName = ChatColor.stripColor(e.getRecipe().getResult().getItemMeta().getDisplayName());
    if (itemName.equals("Master Brewing Stand") && !(className == ClassName.MAGE)) {
      e.setCancelled(true);
      player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't craft that with your current class!"));
    }
  }

  //Mage Passive Ability
  @EventHandler
  private void onPlace(final BlockPlaceEvent e) {
    String blockName = e.getItemInHand().getItemMeta().getDisplayName();
    if (blockName.equals(ChatColor.DARK_PURPLE + "Master Brewing Stand")) {
      Block stand = e.getBlock();
      PersistentDataContainer customStandData = new CustomBlockData(stand, Plugin.plugin);
      NamespacedKey key = new NamespacedKey(Plugin.plugin, "master_brewing_stand");
      customStandData.set(key, PersistentDataType.BOOLEAN, true);
    }
  }

  //Mage Passive Ability
  @EventHandler
  private void onInventory(final InventoryClickEvent e) {
    Player player = (Player) e.getWhoClicked();
    if (e.getInventory().getLocation() != null) {
      Block source = e.getInventory().getLocation().getBlock();
      if (source != null) {
        Boolean isMasterStand = new CustomBlockData(source, Plugin.plugin).get(new NamespacedKey(Plugin.plugin, "master_brewing_stand"), PersistentDataType.BOOLEAN);
        if (isMasterStand != null && isMasterStand) {
          classes.get(ClassName.MAGE).triggerPassive(player, e);
        }
      }
    }
    if (e.getView().getTitle().equals("Spellcasting"))
      classes.get(ClassName.MAGE).triggerActive(player, e);
  }

  //...Mage Passive Ability
  @EventHandler
  private void onBrew(final BrewingStartEvent e) {
    if (e.getBlock() != null) {
      BrewingStand stand = (BrewingStand) e.getBlock().getState();
      Player player = (Player) stand.getInventory().getViewers().get(0);
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
      Boolean isMasterStand = new CustomBlockData(e.getBlock(), Plugin.plugin).get(new NamespacedKey(Plugin.plugin, "master_brewing_stand"), PersistentDataType.BOOLEAN);
      if (className == ClassName.MAGE && isMasterStand != null && isMasterStand) {
        classes.get(ClassName.MAGE).triggerPassive(player, e);
      }
    }
  }

  //Mage Active Ability
  @EventHandler
  private void onClose(final InventoryCloseEvent e) {
    Player player = (Player) e.getViewers().get(0);
    if (e.getView().getTitle().equals("Spellcasting"))
      classes.get(ClassName.MAGE).triggerActive(player, e);
  }

  //Clear unloaded summons
  @EventHandler
  private void onUnload(final ChunkLoadEvent e) {
    Entity[] entities = e.getChunk().getEntities();
    for (Entity entity : entities) {
      String name = entity.getCustomName();
      if (name != null && (name.equals(ChatColor.DARK_GREEN + "Guardian Spirit") || name.equals(ChatColor.LIGHT_PURPLE + "Magic Companion")))
        entity.remove();
    }
  }

  //Mage Active Ability
  @EventHandler
  private void onBreed(final PlayerInteractEntityEvent e) {
    if (e.getRightClicked().getCustomName() != null && e.getRightClicked().getCustomName().equals(ChatColor.LIGHT_PURPLE + "Magic Companion"))
      e.setCancelled(true);
  } 
}
