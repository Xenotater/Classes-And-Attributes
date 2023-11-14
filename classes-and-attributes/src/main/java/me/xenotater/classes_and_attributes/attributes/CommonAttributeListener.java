package me.xenotater.classes_and_attributes.attributes;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.xenotater.classes_and_attributes.Plugin;
import me.xenotater.classes_and_attributes.attributes.objects.GenericAttribute;

public class CommonAttributeListener implements Listener {
  Map<AttributeName, GenericAttribute> attributes = new HashMap<>();

  public void setAttributes(Map<AttributeName, GenericAttribute> attrMap) {
    attributes = attrMap;
  }

  @EventHandler
  private void onLogin(final PlayerLoginEvent e) {
    Player player = e.getPlayer();
    AttributeName diet = Plugin.plugin.dataManager.getDiet(player.getUniqueId());
    if (diet == null) {
      Runnable dietAssigner = new Runnable() {
        @Override
        public void run() {
          Plugin.plugin.dataManager.changeDiet(player.getUniqueId(), AttributeName.getRandomDiet().getName());
        }
      };
      Bukkit.getScheduler().runTaskLater(Plugin.plugin, dietAssigner, 5);
    }

    AttributeName curse = Plugin.plugin.dataManager.getCurse(player.getUniqueId());
    if (curse != null) {
      
    }
  }
}
