package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;

import me.xenotater.classes_and_attributes.attributes.AttributeName;

public class BadTaste extends GenericAttribute {
  List<String> disallowedBlocks;
  List<String> allowedBlocks;

  public BadTaste() {
    AttributeName attribute = AttributeName.BAD_TASTE;
    name = attribute.getName();
    type = attribute.getType();
    disallowedBlocks = Arrays.asList(new String[]{"oak", "spruce", "jungle", "acacia", "mangrove", "cherry", "warped", "crimson", "bamboo_"});
    allowedBlocks = Arrays.asList(new String[]{"sapling", "leaves", "roots", "fungus", "nylium", "wart"});
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    if (e instanceof BlockPlaceEvent) {
      BlockPlaceEvent event = (BlockPlaceEvent) e;
      Block block = event.getBlock();
      if (!isValidBlock(block))
        breakForPlayer(p);
    }
  }

  private boolean isValidBlock(Block block) {
    String blockName = block.getType().name().toLowerCase();
    boolean inAllowed = allowedBlocks.stream().anyMatch(allowed -> blockName.contains(allowed));
    boolean inDisallowed = disallowedBlocks.stream().anyMatch(disallowed -> blockName.contains(disallowed));
    return (inAllowed || !inDisallowed);
  }
}
