package me.xenotater.classes_and_attributes.classes;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.common.ItemIcon;

public class ClassMenu implements Listener {
  Inventory menu = Bukkit.createInventory(null, 9, "Classes");
  boolean isGeneral = true;

  public void openClassMenu(Player player) {
    isGeneral = true;
    Plugin.LOGGER.info("general? " + isGeneral);
    menu.addItem(new ItemIcon("Random Class", "9d9cc58ad25a1ab16d36bb5d6d493c8f5898c2bf302b64e325921c41c35867"));
    menu.addItem(new ItemIcon("Assassin", "cf76c50d0672ca53fbb68c6ac7d1ef4796dd359173f07c8dd40056c5e2e2f132"));
    menu.addItem(new ItemIcon("Berserker", "c09741fca109c4cb0b5efaa0634616503051a199e1d44e4e1149ede0bdc49c8a"));
    menu.addItem(new ItemIcon("Cleric", "66d1bfb4fae91a0cd27bd62b68aff009a502012206605882ec02cf5ca9045d1c"));
    menu.addItem(new ItemIcon("Knight", "7d4d496b1da07536c94c13124a5833ebe0c5382c8a336aad846c681a28d93563"));
    menu.addItem(new ItemIcon("Mage", "edd8fecdba1985baac4df9c3f13e4321653f6ca1e63b7a0ff78dcd73e3f0d5c8"));
    menu.addItem(new ItemIcon("Pyromancer", "d8985f82405ccac8f5ae1b9f603142563c0e51f4b84a8e3958d53b993ecb18d3"));
    menu.addItem(new ItemIcon("Shaman", "22a91fde129a8f132747fc051889d4189a1d0511e7266d3aed57882017ec093d"));
    menu.addItem(new ItemIcon("Ranger", "e93ee5bb0c7facca0f3dfe09430c5b84a90e6588d0a1099da85b6eaeb8958f9a"));
    player.openInventory(menu);
  }

  public void openClass(Player player, ClassName className) {
    isGeneral = false;
    player.openInventory(menu);
  }

  @EventHandler
  public void onInventoryClick(final InventoryClickEvent e) {
    if (!e.getView().getTitle().equals("Classes"))
      return;

    e.setCancelled(true);

    if (e.getClickedInventory() instanceof PlayerInventory)
      return;

    final ItemStack clickedIcon = e.getCurrentItem();
    final Player player = (Player) e.getWhoClicked();

    if (clickedIcon != null && !clickedIcon.getType().isAir() && isGeneral) {
      String itemName = clickedIcon.getItemMeta().getDisplayName();
      if (itemName.equals("Random Class")) {
        Integer rand = new Random().nextInt(ClassName.values().length);
        openClass(player, ClassName.values()[rand]);
      }
      else
        openClass(player, ClassName.getValue(itemName));
    }
  }

  @EventHandler
  public void onInventoryClick(final InventoryDragEvent e) {
      if (e.getView().getTitle().equals("Classes")) {
        e.setCancelled(true);
      }
  }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
      isGeneral = true;
    }
}