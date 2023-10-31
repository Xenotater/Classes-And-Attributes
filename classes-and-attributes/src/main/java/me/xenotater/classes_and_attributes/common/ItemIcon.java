package me.xenotater.classes_and_attributes.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.xenotater.classes_and_attributes.Plugin;

public class ItemIcon extends ItemStack {
  public ItemIcon(Material type) { 
    setType(type);
    setAmount(1);
  }

  public ItemIcon(String name, Material type) {
    setType(type);
    setName(name);
    setAmount(1);
  }

  public ItemIcon(String name, String texture) {
    setType(Material.PLAYER_HEAD);
    setName(name);
    setTexture(texture);
    setAmount(1);
  }

  public ItemIcon(String name, String lore, Material type) {
    setType(type);
    setName(name);
    addLore(lore);
    setAmount(1);
  }

  public ItemIcon(String name, String lore, String texture) {
    setType(Material.PLAYER_HEAD);
    setName(name);
    addLore(lore);
    setTexture(texture);
    setAmount(1);
  }

  private void setName(String name) {
    ItemMeta meta = getItemMeta();
    meta.setDisplayName(name);
    setItemMeta(meta);
  }

  public void addLore(String text) {
    ItemMeta meta = getItemMeta();
    List<String> lore = meta.getLore() != null ? meta.getLore() : new ArrayList<>();
    lore.add(text);
    meta.setLore(lore);
    setItemMeta(meta);
  }

  private void setTexture(String texture) {
    SkullMeta meta = (SkullMeta) getItemMeta();
    PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID(), null);
    PlayerTextures textures = profile.getTextures();

    try {
      textures.setSkin(new URL("http://textures.minecraft.net/texture/" + texture));
    }
    catch(MalformedURLException e){
      Plugin.plugin.LOGGER.warning("Failed to get icon texture: " + e);
      return;
    }

    profile.setTextures(textures);
    meta.setOwnerProfile(profile);
    setItemMeta(meta);
  }
}
