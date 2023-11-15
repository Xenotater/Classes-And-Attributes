package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Gourmand extends GenericAttribute {
  public Gourmand() {
    AttributeName attribute = AttributeName.GOURMAND;
    name = attribute.getName();
    type = attribute.getType();
  }

  public void checkCondition(Event e) {
    if (e instanceof PlayerItemConsumeEvent) {
      PlayerItemConsumeEvent event = (PlayerItemConsumeEvent) e;
      Player player = event.getPlayer();
      ItemStack item = event.getItem();
      AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());

      if (item.getType().isEdible()) {
        if (!((GenericDiet) Plugin.plugin.attributes.get(diet)).isValidFood(player, item))
          breakForPlayer(player);
      }
    }
    else if (e instanceof PlayerInteractEvent) {
      PlayerInteractEvent event = (PlayerInteractEvent) e;
      Player player = event.getPlayer();
      Block block = event.getClickedBlock();
      AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());

      if (block != null && block.getType() == Material.CAKE) {
        if (!((GenericDiet) Plugin.plugin.attributes.get(diet)).isValidFood(player, new ItemStack(Material.CAKE)))
          breakForPlayer(player);
      }
    }
  }
}
