package net.mythigame.hubplugin.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class inventory implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)){
            event.setCancelled(true);
        }else event.setCancelled(!((Player) event.getWhoClicked()).getPlayer().getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onPickup(InventoryPickupItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickupArrow(PlayerPickupArrowEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemConsumeEvent(PlayerItemConsumeEvent event){
        event.setCancelled(true);
    }

}
