package net.mythigame.hubplugin.Listeners;
import net.mythigame.commons.Account;
import net.mythigame.commons.RankUnit;
import net.mythigame.commons.Storage.Redis.RedisAccess;
import net.mythigame.hubplugin.HubPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import static net.mythigame.hubplugin.Utils.scoreboard.PersonnalScoreboard.initPersonnalScoreboard;

public class playerJoin implements Listener {

    public static Location hubSpawnLocation = new Location(Bukkit.getWorld("hub"), -11.5, 41, 20);

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(HubPlugin.getInstance(), () -> {
            final RedisAccess redisAccess = RedisAccess.INSTANCE;
            final RedissonClient redissonClient = redisAccess.getRedissonClient();
            final RBucket<Account> accountRBucket = redissonClient.getBucket("account:" + player.getUniqueId());
            final Account account = accountRBucket.get();
        });

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setExp(0);
        player.setLevel(0);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setVisualFire(false);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.setInvulnerable(true);
        player.setCollidable(false);
        player.setArrowsInBody(0);
        player.setCanPickupItems(false);
        player.teleport(hubSpawnLocation);
        setJoinerInventory(player);

        Account account = new Account().getAccount(player.getUniqueId());
        if(account != null){
            if(account.getGrade().getPower() <= RankUnit.JOUEUR.getPower()){
                event.setJoinMessage("§7[§2+§7] " + player.getDisplayName());
            }else{
                event.setJoinMessage(account.getGrade().getPrefix() + player.getDisplayName() + "§r a rejoins le hub.");
            }
            player.setPlayerListName(" " + account.getGrade().getTabPrefix() + player.getDisplayName());
            initPersonnalScoreboard(player);
            if(!HubPlugin.getInstance().bossBar.getBossBar().getPlayers().contains(player)){
                HubPlugin.getInstance().bossBar.addPlayer(player);
            }
            player.sendTitle("Bienvenue, ", account.getGrade().getTabPrefix() + player.getName(),
                    10, 70, 20
            );
        }else {
            event.setJoinMessage(null);
            player.kickPlayer("Une erreur est survenue, veuillez vous reconnecter.");
        }
    }

    private void setJoinerInventory(Player player){
        player.getInventory().setItem(0, new ItemStack(Material.WOODEN_SWORD));
        player.updateInventory();
    }

}
