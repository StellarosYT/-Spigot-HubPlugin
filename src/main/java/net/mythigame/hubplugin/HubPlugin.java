package net.mythigame.hubplugin;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import net.mythigame.commons.Account;
import net.mythigame.commons.Storage.Redis.RedisAccess;
import net.mythigame.hubplugin.Commands.infoCommand;
import net.mythigame.hubplugin.Listeners.*;
import net.mythigame.hubplugin.Listeners.customItems.woodenSword;
import net.mythigame.hubplugin.Utils.bossbar.Bar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.mythigame.hubplugin.Listeners.playerJoin.hubSpawnLocation;
import static net.mythigame.hubplugin.Utils.scoreboard.PersonnalScoreboard.initPersonnalScoreboard;

@SuppressWarnings("unused")
public final class HubPlugin extends JavaPlugin {

    private static HubPlugin INSTANCE;
    public Bar bossBar;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        INSTANCE = this;
        System.out.println("[HubPlugin] Le plugin a bien démarré.");
        Bukkit.getWorld("hub").setSpawnLocation(hubSpawnLocation);
        Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        registerEvents();
        this.getCommand("info").setExecutor(new infoCommand());
        RedisAccess.init();
        bossBar = new Bar(this);
        bossBar.createBar();

        if(Bukkit.getOnlinePlayers().size() > 0){
            for(Player on : Bukkit.getOnlinePlayers()){
                bossBar.addPlayer(on);
                BPlayerBoard board = Netherboard.instance().getBoard(on);
                initPersonnalScoreboard(on);
            }
        }
        refreshScoreboards();
    }

    @Override
    public void onDisable() {
        System.out.println("[HubPlugin] Le plugin a bien été arrêté.");
        bossBar.removeAll();
        Netherboard.instance().getBoards().forEach((player, bPlayerBoard) -> bPlayerBoard.delete());
        RedisAccess.close();
    }

    public static HubPlugin getInstance() {
        return INSTANCE;
    }

    private void registerEvents(){
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new woodenSword(this), this);
        pluginManager.registerEvents(new blocksEvents(), this);
        pluginManager.registerEvents(new entityDamage(), this);
        pluginManager.registerEvents(new foodLevel(), this);
        pluginManager.registerEvents(new inventory(), this);
        pluginManager.registerEvents(new playerChat(), this);
        pluginManager.registerEvents(new playerDropItem(), this);
        pluginManager.registerEvents(new playerJoin(), this);
        pluginManager.registerEvents(new playerQuit(), this);
        pluginManager.registerEvents(new playerRespawn(), this);
    }

    public void refreshScoreboards(){
        new BukkitRunnable() {

            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    Account account = new Account().getAccount(player.getUniqueId());
                    if(account == null) return;
                    initRefreshSB(account, player);
                    player.setPlayerListName(" " + account.getGrade().getTabPrefix() + player.getDisplayName());
                });
            }
        }.runTaskTimer(this, 60L, 60L);
    }

    public void initRefreshSB(Account account, Player player){
        BPlayerBoard board = Netherboard.instance().getBoard(player);
        String guild = "N/A";
        if(account.getGuild() != null) guild = account.getGuild();
        String rank;
        if(account.getGrade().getName().equals("Joueur")){
            rank = "§7Joueur";
        }else{
            rank = account.getGrade().getPrefix().replace("[", "").replace("]", "").replaceAll(" ", "");
        }
        board.set(ChatColor.RED + "Rang: " + rank, 9); // Rang
        board.set(ChatColor.GOLD + "Coins: " + account.getCoins() + " coins", 7); // Coins
        board.set(ChatColor.DARK_GREEN + "Niveau: " + account.getLevel(), 5); // Niveau
        board.set(ChatColor.DARK_AQUA + "Guild: " + guild, 3); // Guild
    }
}
