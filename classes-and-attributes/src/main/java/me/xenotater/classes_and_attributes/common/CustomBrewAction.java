package me.xenotater.classes_and_attributes.common;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public interface CustomBrewAction {
    public void brew(BrewerInventory inventory , ItemStack item , ItemStack ingridient);
}