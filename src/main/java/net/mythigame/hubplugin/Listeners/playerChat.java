package net.mythigame.hubplugin.Listeners;

import net.mythigame.commons.Account;
import net.mythigame.commons.RankUnit;
import net.mythigame.hubplugin.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class playerChat implements Listener {

    private final List<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        Account account = new Account().getAccount(player.getUniqueId());
        if(account != null){
            RankUnit rank = account.getGrade();
            switch (rank){
                case ADMINISTRATEUR:
                    event.setFormat(rank.getPrefix() + "%1$s : %2$s");
                case JOUEUR:
                    event.setFormat("§7%1$s : %2$s");
                default: event.setFormat(rank.getPrefix() + "%1$s : §f%2$s");
            }

            if(cooldown.contains(player)){
                player.sendMessage("§c[Kronos] Merci de patienter entre chaque message !");
                event.setCancelled(true);
                return;
            }

            if(!(rank.getPower() >= RankUnit.MODERATEUR.getPower())){
                cooldown.add(player);
                Bukkit.getScheduler().runTaskLater(HubPlugin.getInstance(), () -> cooldown.remove(player), 2 * 20L);
            }
        }else{
            player.sendMessage("§cUne erreur est survenue, veuillez vous reconnecter !");
            event.setCancelled(true);
        }
    }

}
