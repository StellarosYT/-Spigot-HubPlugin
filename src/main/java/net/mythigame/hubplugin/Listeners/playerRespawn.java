package net.mythigame.hubplugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static net.mythigame.hubplugin.Listeners.playerJoin.hubSpawnLocation;


public class playerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event){
        event.setRespawnLocation(hubSpawnLocation);
    }

}
