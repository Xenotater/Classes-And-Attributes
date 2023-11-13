package me.xenotater.classes_and_attributes.attributes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.xenotater.classes_and_attributes.Plugin;

public class CommonAttributeListener implements Listener {
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
  }
}
