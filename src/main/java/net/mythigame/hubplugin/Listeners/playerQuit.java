package net.mythigame.hubplugin.Listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.mythigame.commons.Account;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerQuit implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        final Player player = event.getPlayer();
        final ByteArrayDataOutput output = ByteStreams.newDataOutput();
        Account account = new Account().getAccount(player.getUniqueId());
        if(account == null) return;
        event.setQuitMessage("§7[§c-§7] " + player.getName());
    }

}
