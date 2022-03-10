package net.mythigame.hubplugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class foodLevel implements Listener {

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

}
