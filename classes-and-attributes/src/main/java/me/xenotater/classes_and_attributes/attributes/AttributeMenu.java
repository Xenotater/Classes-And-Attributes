package me.xenotater.classes_and_attributes.attributes;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.xenotater.classes_and_attributes.Plugin;

public class AttributeMenu implements Listener  {
  public void openMenu(Player player) {
    AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());
    AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());
    int curseVal = curse != null ? 1 : 0;
    List<AttributeName> attributes = Plugin.plugin.dataManager.getAttibutes(player.getUniqueId());
    int numAttrs = attributes != null ? attributes.size() : 0;
    int numRows = (int) Math.ceil((numAttrs + 1 + curseVal) / 9);

    Inventory menu = Bukkit.createInventory(null, 9 * numRows, "Attributes");
    menu.addItem(new AttributeInfo(diet));
    for (AttributeName attribute : attributes) {
      menu.addItem(new AttributeInfo(attribute));
    }
    if (curse != null)
      menu.addItem(new AttributeInfo(curse));
    player.openInventory(menu);
  }

  @EventHandler
  public void onInventoryClick(final InventoryClickEvent e) {
    String invName = e.getView().getTitle();
    if (invName.equals("Attributes"))
      e.setCancelled(true);
  }
}
