package net.mythigame.hubplugin.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class entityDamage implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)){
            event.setCancelled(true);
        }else event.setCancelled(!((Player) event.getEntity()).getGameMode().equals(GameMode.SURVIVAL));
    }

    @EventHandler
    public void onEntityDamageEventByEntity(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)){
            event.setCancelled(true);
        }else event.setCancelled(!((Player) event.getEntity()).getGameMode().equals(GameMode.SURVIVAL));
    }

    @EventHandler
    public void protectCrops(PlayerInteractEvent event){
        if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.FARMLAND)
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent event){
        if(!(event.getEntity() instanceof Player)){
            event.setCancelled(true);
        }else event.setCancelled(!((Player) event.getEntity()).getPlayer().getGameMode().equals(GameMode.CREATIVE));
    }

}
