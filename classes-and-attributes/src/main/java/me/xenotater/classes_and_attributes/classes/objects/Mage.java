package me.xenotater.classes_and_attributes.classes.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BrewingStartEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;


import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.ClassItemType;
import me.xenotater.classes_and_attributes.classes.mage_spellcasting.SpellMenu;
import me.xenotater.classes_and_attributes.common.CustomBrewRecipe;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Mage extends GenericClass {
  List<Material> flowers = new ArrayList<>();
  SpellMenu spells = new SpellMenu();
  
  public Mage() {
    disallowedArmor = new String[]{"chainmail", "iron", "diamond", "netherite"};
    disallowedWeapons = new String[]{"_axe", "trident"};
    disallowedInteracts = new String[]{"shield", "trident"};
    enchantsAllowed.put(ClassItemType.ARMOR, false);
    enchantsAllowed.put(ClassItemType.BOW, false);
    setFlowers();
    createRecipies();
    abilityDuration = 0;
    abilityCooldown = 15000;
  }

  //Master Brewer
  public void triggerPassive(Player p, Event e) {
    if (e instanceof InventoryClickEvent) { //handle custom brewing
      InventoryClickEvent event = (InventoryClickEvent) e;
      if (event.getClickedInventory() instanceof BrewerInventory) {
        BrewerInventory standInv = (BrewerInventory) event.getClickedInventory();
        BrewingStand stand = standInv.getHolder();

        //refuel if fuel empty & using custom fuel
        if (stand.getFuelLevel() == 0 && standInv.getFuel() != null && standInv.getFuel().getType() == Material.COAL) {
          stand.setFuelLevel(20);
          stand.update();
          stand.getInventory().getFuel().setAmount(standInv.getFuel().getAmount() - 1); //have to get inv after update
        }

        ItemStack currentItem = event.getCurrentItem();
        ItemStack newItem = event.getCursor();
        if (event.getSlot() == 4 && newItem.getType() == Material.COAL) {
          handleBrewItem(event, standInv, currentItem, newItem, true, false);
          notifyAbilityTriggered(p, "Master Brewer");
        }
        else if (event.getSlot() == 3 && flowers.contains(newItem.getType())) {
          handleBrewItem(event, standInv, currentItem, newItem, false, false);
          notifyAbilityTriggered(p, "Master Brewer");
        }
      }
      else if (event.getClick().equals(ClickType.SHIFT_LEFT)) { //..also allow shift-clicking
        if (event.getCurrentItem() != null) {
          ItemStack newItem = event.getCurrentItem().clone();
          ItemStack currentItem;
          BrewerInventory standInv = (BrewerInventory) event.getView().getTopInventory();

          if (newItem.getType() == Material.COAL) {
            currentItem = standInv.getFuel();
            Plugin.plugin.LOGGER.info("current: " + currentItem);
            if (currentItem == null || currentItem.getType() == Material.AIR) {
              event.setCancelled(true);
              handleBrewItem(event, standInv, currentItem, newItem, true, true);
              notifyAbilityTriggered(p, "Master Brewer");
            }
          }
          else if (flowers.contains(newItem.getType())) {
            currentItem = standInv.getIngredient();
            Plugin.plugin.LOGGER.info("current: " + currentItem);
            if (currentItem == null || currentItem.getType() == Material.AIR) {
              event.setCancelled(true);
              handleBrewItem(event, standInv, currentItem, newItem, false, true);
              notifyAbilityTriggered(p, "Master Brewer");
            }
          }
        }
      }
    }
    else if (e instanceof BrewingStartEvent) { //speed up brewing for all potions
      BrewingStartEvent event = (BrewingStartEvent) e;
      event.setTotalBrewTime(100);
      notifyAbilityTriggered(p, "Master Brewer");
    }
  }

  //Spellcasting
  public void triggerActive(Player p, Event e) {
    if (e instanceof PlayerInteractEvent) {
      if (isAbilityReady(p))
        spells.openMenu(p);
      else
        notifyAbilityCooldown(p, "Spellcasting");
    }
    else if (e instanceof InventoryClickEvent) {
      InventoryClickEvent event = (InventoryClickEvent) e;
      if (event.getCurrentItem() != null) {
        String spell = event.getCurrentItem().getItemMeta().getDisplayName();
        event.setCancelled(true);
        if (!spell.isEmpty()) {
          p.closeInventory();
          notifySpellCast(p, spell);
        }
      }
    }
    else if (e instanceof InventoryCloseEvent) {
      notifyAbilityTriggered(p, "Spellcasting");
      startAbilityCooldown(p);
    }
  }

  public static void createBrewerRecipe() {
    ItemStack stand = new ItemStack(Material.BREWING_STAND);
    ItemMeta meta = stand.getItemMeta();
    meta.setDisplayName(ChatColor.DARK_PURPLE + "Master Brewing Stand");
    stand.setItemMeta(meta);
    NamespacedKey key = new NamespacedKey(Plugin.plugin, "master_brewing_stand");
    ShapedRecipe recipe = new ShapedRecipe(key, stand);
    recipe.shape("   ", " S ", "CCC");
    recipe.setIngredient('S', Material.STICK);
    recipe.setIngredient('C', Material.COBBLESTONE);
    Bukkit.addRecipe(recipe);
  }

  private void setFlowers() {
    flowers.add(Material.POPPY);
    flowers.add(Material.DANDELION);
    flowers.add(Material.BLUE_ORCHID);
    flowers.add(Material.ALLIUM);
    flowers.add(Material.AZURE_BLUET);
    flowers.add(Material.RED_TULIP);
    flowers.add(Material.PINK_TULIP);
    flowers.add(Material.WHITE_TULIP);
    flowers.add(Material.ORANGE_TULIP);
    flowers.add(Material.OXEYE_DAISY);
    flowers.add(Material.CORNFLOWER);
    flowers.add(Material.LILY_OF_THE_VALLEY);
    flowers.add(Material.TORCHFLOWER);
    flowers.add(Material.WITHER_ROSE);
    flowers.add(Material.LILAC);
    flowers.add(Material.PEONY);
    flowers.add(Material.ROSE_BUSH);
    flowers.add(Material.SUNFLOWER);
    flowers.add(Material.PINK_PETALS);
    flowers.add(Material.SPORE_BLOSSOM);
    flowers.add(Material.CHORUS_FLOWER);
  }
  
  private void createRecipies() {
    ItemStack water = new ItemStack(Material.POTION);
    PotionMeta waterMeta = (PotionMeta) water.getItemMeta();
    waterMeta.setBasePotionData(new PotionData(PotionType.WATER));
    water.setItemMeta(waterMeta);

    for (Material flower : flowers) {
      new CustomBrewRecipe(new ItemStack(flower), water, (inventory, item, ingridient) -> {
        if (!(item.getType() == Material.POTION) && ((PotionMeta) item.getItemMeta()).getBasePotionData().getType() == PotionType.WATER)
          return;
        if (!flowers.contains(ingridient.getType()))
          return;
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.AWKWARD));
        item.setItemMeta(meta);
        BrewingStand stand = inventory.getHolder();
        stand.setFuelLevel(stand.getFuelLevel() - 1);
        stand.update();
        stand.getLocation().getWorld().playSound(stand.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
      }, 100, true);
    }
  }

  private void handleBrewItem(InventoryClickEvent event, BrewerInventory stand, ItemStack currentItem, ItemStack newItem, boolean isFuel, boolean isShiftClick) {
    BrewingStand source = stand.getHolder();
    Runnable itemSwitch = new Runnable() {
      @SuppressWarnings("deprecation")
      @Override
      public void run() {
        if (isFuel) {
          if (!isShiftClick)
            event.setCursor(currentItem);
          else
            event.getCurrentItem().setAmount(0);
          if (source.getFuelLevel() == 0) {
            newItem.setAmount(newItem.getAmount() - 1);
            source.setFuelLevel(20);
            source.update();
          }
          stand.setFuel(newItem);
        }
        else {
          if (currentItem == null || currentItem.getType() == Material.AIR) {
            ItemStack ingredient = newItem.clone();
            ingredient.setAmount(1);
            stand.setIngredient(ingredient);
            newItem.setAmount(newItem.getAmount() - 1);
            if (!isShiftClick)
              event.setCursor(newItem);
            else
              event.getCurrentItem().setAmount(event.getCurrentItem().getAmount() - 1);
            brewAwkwardPot(stand);
          }
        }
      }
    };
    Bukkit.getScheduler().scheduleSyncDelayedTask(Plugin.plugin, itemSwitch, 1);
    ((Player) event.getView().getPlayer()).updateInventory();
  }

  private void brewAwkwardPot(BrewerInventory stand) {
    if (stand.getIngredient() == null || stand.getHolder().getFuelLevel() == 0)
      return;
    CustomBrewRecipe awkwardRecipe = CustomBrewRecipe.getRecipe(stand);
    if (awkwardRecipe != null)
      awkwardRecipe.startBrewing(stand);
  }

  private void notifySpellCast(Player p, String name) {
    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.LIGHT_PURPLE + "** Cast " + name + " **"));
  }
}
