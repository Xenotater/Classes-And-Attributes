package me.xenotater.classes_and_attributes.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.common.ItemIcon;

public class ClassMenu implements Listener {
  List<String> nonClassNames = new ArrayList<>();

  public ClassMenu() {
    nonClassNames.add("Back");
    nonClassNames.add("Cancel");
    nonClassNames.add("Select Class");
    nonClassNames.add("Confirm");
  }

  public void openClassMenu(Player player) {
    Inventory menu = Bukkit.createInventory(null, 9, "Classes");
    ClassName currentClass = Plugin.plugin.dataManager.getClass(player.getUniqueId());
    menu.addItem(new ItemIcon(ChatColor.WHITE + "Random Class", "9d9cc58ad25a1ab16d36bb5d6d493c8f5898c2bf302b64e325921c41c35867"));
    menu.addItem(new ItemIcon(getClassColor("Assassin", currentClass) + "Assassin", "cf76c50d0672ca53fbb68c6ac7d1ef4796dd359173f07c8dd40056c5e2e2f132"));
    menu.addItem(new ItemIcon(getClassColor("Berserker", currentClass) + "Berserker", "c09741fca109c4cb0b5efaa0634616503051a199e1d44e4e1149ede0bdc49c8a"));
    menu.addItem(new ItemIcon(getClassColor("Cleric", currentClass) + "Cleric", "66d1bfb4fae91a0cd27bd62b68aff009a502012206605882ec02cf5ca9045d1c"));
    menu.addItem(new ItemIcon(getClassColor("Knight", currentClass) + "Knight", "7d4d496b1da07536c94c13124a5833ebe0c5382c8a336aad846c681a28d93563"));
    menu.addItem(new ItemIcon(getClassColor("Mage", currentClass) + "Mage", "edd8fecdba1985baac4df9c3f13e4321653f6ca1e63b7a0ff78dcd73e3f0d5c8"));
    menu.addItem(new ItemIcon(getClassColor("Pyromancer", currentClass) + "Pyromancer", "d8985f82405ccac8f5ae1b9f603142563c0e51f4b84a8e3958d53b993ecb18d3"));
    menu.addItem(new ItemIcon(getClassColor("Shaman", currentClass) + "Shaman", "22a91fde129a8f132747fc051889d4189a1d0511e7266d3aed57882017ec093d"));
    menu.addItem(new ItemIcon(getClassColor("Ranger", currentClass) + "Ranger", "e93ee5bb0c7facca0f3dfe09430c5b84a90e6588d0a1099da85b6eaeb8958f9a"));
    player.openInventory(menu);
  }

  private ChatColor getClassColor(String className, ClassName currentClass) {
    if (currentClass != null && currentClass.getName().equals(className))
      return ChatColor.GREEN;
    else
      return ChatColor.BLUE;
  }

  public void openClass(Player player, ClassName className) {
    ClassInfo info = new ClassInfo(className);
    Inventory menu = Bukkit.createInventory(null, 9, className.getName() + " Class Info");
    menu.addItem(new ItemIcon(ChatColor.RED + "Back", "f84f597131bbe25dc058af888cb29831f79599bc67c95c802925ce4afba332fc"));
    menu.setItem(1, new ItemIcon("", Material.GRAY_STAINED_GLASS_PANE));
    menu.addItem(info.info);
    menu.addItem(info.armor);
    menu.addItem(info.weapons);
    menu.addItem(info.passive);
    menu.addItem(info.active);
    menu.setItem(7, new ItemIcon("", Material.GRAY_STAINED_GLASS_PANE));
    ItemIcon selectIcon = new ItemIcon(ChatColor.GREEN + "Select Class", "a79a5c95ee17abfef45c8dc224189964944d560f19a44f19f8a46aef3fee4756");
    selectIcon.addLore("" + ChatColor.BLUE + ChatColor.UNDERLINE + ChatColor.BOLD + className.getName());
    menu.addItem(selectIcon);
    player.openInventory(menu);
  }

  public void openConfirm(Player player, String choice) {
    Inventory menu = Bukkit.createInventory(null, 9, "Confirm Class Change");
    menu.addItem(new ItemIcon(ChatColor.RED + "Cancel", Material.BARRIER));
    for (int i=1;i<=7;i++)
      menu.setItem(i, new ItemIcon("", Material.GRAY_STAINED_GLASS_PANE));
    ItemIcon confirm = new ItemIcon(ChatColor.GREEN + "Confirm", "a79a5c95ee17abfef45c8dc224189964944d560f19a44f19f8a46aef3fee4756");
    confirm.addLore(ChatColor.RED + "Your class will be changed and you will lose");
    confirm.addLore(ChatColor.RED + "any benefits from your previous class.");
    confirm.addLore("" + ChatColor.BLUE + ChatColor.UNDERLINE + ChatColor.BOLD + choice);
    menu.addItem(confirm);
    player.openInventory(menu);
  }

  public void openConfirm(Player sender, Player target, String choice) {
    Inventory menu = Bukkit.createInventory(null, 9, "Confirm Class Change");
    menu.addItem(new ItemIcon(ChatColor.RED + "Cancel", Material.BARRIER));
    for (int i=1;i<=7;i++)
      menu.setItem(i, new ItemIcon("", Material.GRAY_STAINED_GLASS_PANE));
    ItemIcon confirm = new ItemIcon(ChatColor.GREEN + "Confirm", "a79a5c95ee17abfef45c8dc224189964944d560f19a44f19f8a46aef3fee4756");
    confirm.addLore(ChatColor.RED  + target.getDisplayName() + "'s class will be changed and they will");
    confirm.addLore(ChatColor.RED + "lose any benefits from their previous class.");
    confirm.addLore("" + ChatColor.BLUE + ChatColor.UNDERLINE + ChatColor.BOLD + choice);
    menu.addItem(confirm);
    sender.openInventory(menu);
  }

  @EventHandler
  public void onInventoryClick(final InventoryClickEvent e) {
    String invName = e.getView().getTitle();
    if (!(invName.equals("Classes") || invName.contains("Class Info") || invName.equals("Confirm Class Change")))
      return;

    e.setCancelled(true);

    if (e.getClickedInventory() instanceof PlayerInventory)
      return;

    final ItemStack clickedIcon = e.getCurrentItem();

    if (clickedIcon == null || clickedIcon.getType().isAir())
      return;

    final String iconName = ChatColor.stripColor(clickedIcon.getItemMeta().getDisplayName());
    final Player player = (Player) e.getWhoClicked();
    final boolean isClassIcon = !nonClassNames.contains(iconName);
    final List<String> lore = clickedIcon.getItemMeta().getLore();

    if (isClassIcon) {
      if (iconName.equals("Random Class")) {
        openClass(player, ClassName.getRandom());
        return;
      }
      else
        openClass(player, ClassName.getValue(iconName));
        return;
    }

    switch(iconName) {
      case "Back":
        openClassMenu(player);
        break;
      case "Select Class":
        openConfirm(player, ChatColor.stripColor(lore.get(0)));
        break;
      case "Cancel":
        player.closeInventory();
        break;
      case "Confirm":
        String firstLore = ChatColor.stripColor(lore.get(0));
        String className = ChatColor.stripColor(lore.get(2));
        String successMsg = className.equals("No Class") ? "You now have no class." : "You now have the " + className + " class!";
        boolean success;
        player.closeInventory();
        if (firstLore.startsWith("Your") && !firstLore.contains("they")) {
          success = Plugin.plugin.dataManager.setClass(player.getUniqueId(), className);
          player.sendMessage(success ? ChatColor.GREEN + successMsg : ChatColor.DARK_RED + "An error occurred while updating your class.");
        }
        else {
          Player target = Bukkit.getPlayerExact(ChatColor.stripColor(firstLore.substring(0, firstLore.indexOf('\''))));
          success = Plugin.plugin.dataManager.setClass(target.getUniqueId(), className);
          player.sendMessage(success ? ChatColor.GREEN + target.getDisplayName() + "'s class was updated." : "An error occurred while updating the target's class.");
          if (success)
            target.sendMessage("Your class was updated. " + successMsg);
        }
        break;
    }
  }
}