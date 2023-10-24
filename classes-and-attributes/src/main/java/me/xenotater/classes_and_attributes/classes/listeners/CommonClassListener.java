package me.xenotater.classes_and_attributes.classes.listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.codingforcookies.armorequip.ArmorEquipEvent;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.ClassName;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CommonClassListener implements Listener {
  Map<String, GenericClassListener> listeners = new HashMap<>();

  public CommonClassListener() {
    listeners.put("Assassin", new Assassin());
    listeners.put("Berserker", new Berserker());
    listeners.put("Cleric", new Cleric());
    listeners.put("Knight", new Knight());
    listeners.put("Mage", new Mage());
    listeners.put("Pyromancer", new Pyromancer());
    listeners.put("Shaman", new Shaman());
    listeners.put("Ranger", new Ranger());
  }

  @EventHandler
  private void onEvent(final ArmorEquipEvent e) {
    Player player = e.getPlayer();
    ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());
    ItemStack item = e.getNewArmorPiece();

    if (className != null && item != null) {
      String itemName = item.getType().name().toLowerCase();
      List<String> disallowedList = Arrays.asList(listeners.get(className.getName()).disallowedArmor);
      if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)) && !itemName.contains("turtle")) {
        e.setCancelled(true);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't equip that item with your current class!"));
      }
    }
  }
}
