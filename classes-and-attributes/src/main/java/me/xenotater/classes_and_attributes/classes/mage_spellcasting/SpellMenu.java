package me.xenotater.classes_and_attributes.classes.mage_spellcasting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.xenotater.classes_and_attributes.common.ItemIcon;

public class SpellMenu {
  public void openMenu(Player player) {
    Inventory menu = Bukkit.createInventory(null, 9, "Spellcasting");
    for (int i=0; i<9; i++) {
      if (i<3 || i>5)
        menu.setItem(i, new ItemIcon("", Material.GRAY_STAINED_GLASS_PANE));
      else {
        ItemIcon icon = SpellInfo.getRandom().getIcon();
        boolean duplicate = true;
        while (duplicate) {
          duplicate = false;
          for (ItemStack item : menu.getContents())
            if (icon.isSimilar(item)) {
              duplicate = true;
              icon = SpellInfo.getRandom().getIcon();
            }
        }
        menu.setItem(i, icon);
      }
    }
    player.openInventory(menu);
  }
}
