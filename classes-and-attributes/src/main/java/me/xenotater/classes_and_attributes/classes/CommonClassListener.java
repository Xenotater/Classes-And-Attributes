package me.xenotater.classes_and_attributes.classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.codingforcookies.armorequip.ArmorEquipEvent;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.classes.objects.Assassin;
import me.xenotater.classes_and_attributes.classes.objects.Berserker;
import me.xenotater.classes_and_attributes.classes.objects.Cleric;
import me.xenotater.classes_and_attributes.classes.objects.GenericClass;
import me.xenotater.classes_and_attributes.classes.objects.Knight;
import me.xenotater.classes_and_attributes.classes.objects.Mage;
import me.xenotater.classes_and_attributes.classes.objects.Pyromancer;
import me.xenotater.classes_and_attributes.classes.objects.Ranger;
import me.xenotater.classes_and_attributes.classes.objects.Shaman;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CommonClassListener implements Listener {
  Map<String, GenericClass> listeners = new HashMap<>();

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
  private void onEquip(final ArmorEquipEvent e) {
    Player player = e.getPlayer();
    ItemStack item = e.getNewArmorPiece();
    ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());

    if (className != null && item != null) {
      if (!isValidArmor(item, className)) {
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
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());

      if (className != null && item != null) {
        if (!isValidInteract(item, className)) {
            e.setCancelled(true);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't use " + item.getType().name() + " with your current class!"));
        }
      }
    }
  }

  @EventHandler
  private void onDamageEntity(final EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player) {
      Player player = (Player) e.getDamager();
      ItemStack item = player.getInventory().getItemInMainHand();
      ClassName className = Plugin.plugin.dataManager.getClass(player.getUniqueId());

      if (className != null && item != null) {
        if (!isValidWeapon(item, className)) {
          e.setCancelled(true);
          player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't use " + item.getType().name() + " with your current class!"));
        }
      }
    }
  }
  
  public boolean isValidArmor(ItemStack item, ClassName className) {
    String itemName = item.getType().name().toLowerCase();
    GenericClass classListener = listeners.get(className.getName());
    List<String> disallowedList = Arrays.asList(classListener.getDisallowedArmor());
    boolean enchantsAllowed = classListener.getEnchantsAllowed(ClassItemType.ARMOR);
    
    if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)) && !itemName.contains("turtle"))
      return false;
    if (item.getEnchantments().size() > 0 && !enchantsAllowed)
      return false;
    return true;
  }

  public boolean isValidInteract(ItemStack item, ClassName className) {
    String itemName = item.getType().name().toLowerCase();
    GenericClass classListener = listeners.get(className.getName());
    List<String> disallowedList = Arrays.asList(classListener.getDisallowedInteracts());
    boolean tridentEnchantsAllowed = classListener.getEnchantsAllowed(ClassItemType.TRIDENT);
    boolean bowEnchantsAllowed = classListener.getEnchantsAllowed(ClassItemType.BOW);

    if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)))
      return false;
    if (itemName.equals("trident") && item.getEnchantments().size() > 0 && !tridentEnchantsAllowed)
      return false;
    if ((itemName.equals("bow") || itemName.equals("crossbow")) && item.getEnchantments().size() > 0 && !bowEnchantsAllowed)
      return false;
    return true;
  }

  public boolean isValidWeapon(ItemStack item, ClassName className) {
    String itemName = item.getType().name().toLowerCase();
    GenericClass classListener = listeners.get(className.getName());
    List<String> disallowedList = Arrays.asList(classListener.getDisallowedWeapons());
    boolean weaponEnchantsAllowed = classListener.getEnchantsAllowed(ClassItemType.WEAPON);
    boolean tridentEnchantsAllowed = classListener.getEnchantsAllowed(ClassItemType.TRIDENT);

    if (disallowedList.stream().anyMatch(disallowed -> itemName.contains(disallowed)))
      return false;
    if (itemName.equals("trident") && item.getEnchantments().size() > 0 && !tridentEnchantsAllowed)
      return false;
    else if (item.getEnchantments().size() > 0 && !weaponEnchantsAllowed)
      return false;
    return true;
  }
}
