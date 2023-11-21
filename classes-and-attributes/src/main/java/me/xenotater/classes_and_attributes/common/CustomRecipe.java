package me.xenotater.classes_and_attributes.common;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomRecipe extends ShapedRecipe {
  public CustomRecipe(NamespacedKey key, ItemStack result, String[] pattern, Map<Character, Material> ingredients) {
    super(key, result);
    shape(pattern);
    for (Entry<Character, Material> ingredient : ingredients.entrySet()) {
      setIngredient(ingredient.getKey(), ingredient.getValue());
    }
    Bukkit.addRecipe(this);
  }
}
