package me.xenotater.classes_and_attributes.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.xenotater.classes_and_attributes.Plugin;

//this class created with the help of NacOJerk: https://www.spigotmc.org/threads/how-to-make-custom-potions-and-brewing-recipes.211002/
public class CustomBrewRecipe {
    private static List<CustomBrewRecipe> recipes = new ArrayList<CustomBrewRecipe>();
    private ItemStack ingridient;
    private ItemStack base;
    private CustomBrewAction action;
    private int brewingTime;
    private boolean perfect;

    public CustomBrewRecipe(ItemStack ingridient, ItemStack base, CustomBrewAction action, int time, boolean perfect) {
        this.ingridient = ingridient;
        this.base = base;
        this.action = action;
        this.brewingTime = time;
        this.perfect = perfect;
        recipes.add(this);
    }

    public CustomBrewRecipe(Material ingridient, Material base, CustomBrewAction action, int time) {
        this(new ItemStack(ingridient), new ItemStack(base), action, time, false);
    }

    public ItemStack getIngridient() {
        return ingridient;
    }

    public ItemStack getBase() {
        return base;
    }

    public CustomBrewAction getAction() {
        return action;
    }

    public int getBrewTime() {
        return brewingTime;
    }

    public boolean isPerfect() {
        return perfect;
    }

    @Nullable
    public static CustomBrewRecipe getRecipe(BrewerInventory inventory) {
        for (CustomBrewRecipe recipe : recipes) {
            int emptyCount = 0;
            if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngridient().getType()) {
                for (int i = 0; i < 3; i++) {
                    if (inventory.getItem(i) == null) {
                        emptyCount++;
                        continue;
                    }
                    if (!(inventory.getItem(i).getType() == recipe.getBase().getType()))
                        return null;
                }
                if (emptyCount == 3)
                    return null;
                return recipe;
            }
            if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngridient())) {
                for (int i = 0; i < 3; i++) {
                    if (inventory.getItem(i) == null) {
                        emptyCount++;
                        continue;
                    }
                    if (!(inventory.getItem(i).isSimilar(recipe.getBase())))
                        return null;
                }
                if (emptyCount == 3)
                    return null;
                return recipe;
            }
        }
        return null;
    }

    public void startBrewing(BrewerInventory inventory) {
        Plugin.plugin.LOGGER.info("BREWING!!!");
        new BrewClock(this, inventory);
    }

    private class BrewClock extends BukkitRunnable {
        private BrewerInventory inventory;
        private CustomBrewRecipe recipe;
        private ItemStack ingridient;
        private BrewingStand stand;
        private int time;
        private ItemStack[] originalContents;

        public BrewClock(CustomBrewRecipe recipe, BrewerInventory inventory) {
            this.recipe = recipe;
            this.inventory = inventory;
            this.ingridient = inventory.getIngredient();
            this.stand = inventory.getHolder();
            this.time = recipe.getBrewTime();
            this.originalContents = inventory.getContents().clone();
            runTaskTimer(Plugin.plugin, 0L, 1L);
        }

        @Override
        public void run() {
            if (time == 0) {
                inventory.setIngredient(new ItemStack(Material.AIR));
                for (int i = 0; i < 3; i++) {
                    if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR)
                        continue;
                    recipe.getAction().brew(inventory, inventory.getItem(i), ingridient);
                }
                cancel();
                return;
            }
            if (!Arrays.equals(originalContents, inventory.getContents())) {
                Plugin.plugin.LOGGER.info("contents: " + inventory.getContents());
                Plugin.plugin.LOGGER.info("real: " + stand.getInventory().getContents().toString());
                stand.setBrewingTime(recipe.getBrewTime());
                cancel();
                return;
            }

            time--;
            stand.setBrewingTime(time);
            stand.update();
        }
    }
}