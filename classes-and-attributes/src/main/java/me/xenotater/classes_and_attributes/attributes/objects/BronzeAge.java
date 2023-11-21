package me.xenotater.classes_and_attributes.attributes.objects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.AttributeName;
import net.md_5.bungee.api.ChatColor;

public class BronzeAge extends GenericAttribute {
  Map<UUID, Integer> digging = new HashMap<>();
  List<String> tools;

  public BronzeAge() {
    AttributeName attribute = AttributeName.BRONZE_AGE;
    name = attribute.getName();
    type = attribute.getType();
    tools = Arrays.asList(new String[]{"_pickaxe", "_axe", "_shovel", "_hoe"});
  }

  @Override
  public void triggerEffect(Player p, Event e) {
    ItemStack tool = p.getInventory().getItemInMainHand();
    if (tool != null && tool.getType() != Material.AIR) {
      boolean isBronze = tool.getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Bronze");
      boolean isTool = tools.stream().anyMatch(disallowed -> tool.getType().name().toLowerCase().contains(disallowed));
      if ((e instanceof EntityDamageByEntityEvent || e instanceof BlockBreakEvent) && isTool && !isBronze)
        breakForPlayer(p);
    }
  }

  public void createToolRecipes() {
    createRecipe(Material.IRON_AXE, "Bronze Axe", new String[] {"BB " , "BS ", " S "});
    createRecipe(Material.IRON_AXE, "Bronze Axe", new String[] {" BB" , " SB", " S "}, 2);
    createRecipe(Material.IRON_PICKAXE, "Bronze Pickaxe", new String[] {"BBB" , " S ", " S "});
    createRecipe(Material.IRON_SHOVEL, "Bronze Shovel", new String[] {" B " , " S ", " S "});
    createRecipe(Material.IRON_HOE, "Bronze Hoe", new String[] {"BB " , " S ", " S "});
    createRecipe(Material.IRON_HOE, "Bronze Hoe", new String[] {" BB" , " S ", " S "}, 2);
  }

  private void createRecipe(Material item, String name, String[] pattern) {
    createRecipe(item, name, pattern, 1);
  }

  private void createRecipe(Material item, String name, String[] pattern, int idNum) {
    ItemStack tool = new ItemStack(item);
    Damageable meta = (Damageable) tool.getItemMeta();
    meta.setDisplayName(ChatColor.GOLD + name);
    meta.setDamage(50);
    meta.setLore(Arrays.asList(new String[]{"" + ChatColor.GRAY + ChatColor.ITALIC + "\"It's only slightly worse than iron!\""}));
    tool.setItemMeta(meta);
    NamespacedKey key = new NamespacedKey(Plugin.plugin, name.toLowerCase().replace(" ", "_") + idNum);
    ShapedRecipe recipe = new ShapedRecipe(key, tool);
    recipe.shape(pattern[0], pattern[1], pattern[2]);
    recipe.setIngredient('B', Material.COPPER_INGOT);
    recipe.setIngredient('S', Material.STICK);
    Bukkit.addRecipe(recipe);
  }
}
