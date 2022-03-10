package net.mythigame.hubplugin.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class playerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        event.setDeathMessage(null);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player player)
        if(player.getHealth() - event.getFinalDamage() <= 0){
            event.setCancelled(true);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.getInventory().clear();
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }

}
