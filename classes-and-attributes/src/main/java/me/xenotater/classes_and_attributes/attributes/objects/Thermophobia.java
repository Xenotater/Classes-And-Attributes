package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class Thermophobia extends GenericAttribute {
  List<String> disallowedItems;
  List<String> disallowedBlocks;

  public Thermophobia() {
    AttributeName attribute = AttributeName.THERMOPHOBIA;
    name = attribute.getName();
    type = attribute.getType();
    disallowedItems = Arrays.asList(new String[]{"flint_and_steel", "fire_charge"});
    disallowedBlocks = Arrays.asList(new String[]{"furnace", "smoker", "campfire"});
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof PlayerInteractEvent) {
      PlayerInteractEvent event = (PlayerInteractEvent) e;
      if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        ItemStack mainHand = p.getInventory().getItemInMainHand();
        ItemStack offHand = p.getInventory().getItemInOffHand();
        Block block = event.getClickedBlock();
        if (isNotValid(mainHand) || isNotValid(offHand) || isNotValid(block))
          breakForPlayer(p);
      }
    }
  }

  private boolean isNotValid(ItemStack item) {
    String name = item.getType().name().toLowerCase();
    return disallowedItems.stream().anyMatch(disallowed -> name.contains(disallowed));
  }

  private boolean isNotValid(Block block) {
    String name = block.getType().name().toLowerCase();
    return disallowedBlocks.stream().anyMatch(disallowed -> name.contains(disallowed));
  }
}
