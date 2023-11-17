package me.xenotater.classes_and_attributes.attributes.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class ExpertSmith extends GenericAttribute {
  public ExpertSmith() {
    AttributeName attribute = AttributeName.EXPERT_SMITH;
    name = attribute.getName();
    type = attribute.getType();
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof PrepareAnvilEvent) {
      PrepareAnvilEvent event = (PrepareAnvilEvent) e;
      AnvilInventory anvil = event.getInventory();
      anvil.setMaximumRepairCost(Integer.MAX_VALUE);
      Runnable costCapper = new Runnable() {
        public void run() {
          if (anvil.getRepairCost() > 10)
            anvil.setRepairCost(10);
        }
      };
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, costCapper, 1);
    }
    else if (e instanceof InventoryClickEvent) {
      InventoryClickEvent event = (InventoryClickEvent) e;
      if (event.getClickedInventory() instanceof AnvilInventory && event.getSlotType() == SlotType.RESULT && event.getCurrentItem() != null) {
        AnvilInventory inv = (AnvilInventory) event.getInventory();
        Location anvilLoc = inv.getLocation();
        Block anvil = anvilLoc.getBlock();
        BlockData data = anvil.getBlockData();
        if (anvil.getType() == Material.ANVIL || anvil.getType() == Material.CHIPPED_ANVIL || anvil.getType() == Material.DAMAGED_ANVIL) {
          Runnable anvilReplacer = new Runnable() {
            public void run() {
              anvilLoc.getBlock().setType(anvil.getType());
              anvilLoc.getBlock().setBlockData(data);
            }
          };
          Bukkit.getScheduler().runTaskLater(Plugin.plugin, anvilReplacer, 1);
        }
      }
    }
  }
}
