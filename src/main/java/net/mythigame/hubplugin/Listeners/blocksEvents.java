package net.mythigame.hubplugin.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

public class blocksEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onHarvestBlockEvent(PlayerHarvestBlockEvent event){
        event.setCancelled(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE));
    }

}
