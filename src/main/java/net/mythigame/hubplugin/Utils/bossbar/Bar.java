package net.mythigame.hubplugin.Utils.bossbar;

import net.mythigame.hubplugin.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bar {

    private int taskID = -1;
    private BossBar bossBar;

    public Bar(HubPlugin hubPlugin){
    }

    public void addPlayer(Player player){
        bossBar.addPlayer(player);
    }

    public void removePlayer(Player player){
        bossBar.removePlayer(player);
    }

    public void removeAll(){
        bossBar.getPlayers().forEach(p -> bossBar.removePlayer(p));
    }

    public BossBar getBossBar(){
        return bossBar;
    }

    public void createBar(){
        bossBar = Bukkit.createBossBar(format("&3En cours de développement..."), BarColor.PURPLE, BarStyle.SEGMENTED_20);
        bossBar.setVisible(true);
        cast();
    }

    public void cast(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(HubPlugin.getInstance(), new Runnable() {

            int count = -1;
            double progress = 1.0;
            final double time = 1.0 / 60;

            @Override
            public void run() {
                bossBar.setProgress(progress);
                switch (count){
                    case -1:
                        break;
                    case 0:
                        bossBar.setColor(BarColor.PURPLE);
                        bossBar.setTitle(format("&3Bon jeu sur MGC !"));
                        break;
                    case 1:
                        bossBar.setColor(BarColor.PURPLE);
                        bossBar.setTitle(format("&3Site : https://www.mythigame.net"));
                        break;
                    case 2:
                        bossBar.setColor(BarColor.PURPLE);
                        bossBar.setTitle(format("&3Discord : https://discord.gg/Txe9z6T"));
                        break;
                    case 3:
                        bossBar.setColor(BarColor.PURPLE);
                        bossBar.setTitle(format("&3Bienvenue sur MGC !"));
                        break;
                    default:
                        bossBar.setColor(BarColor.PURPLE);
                        bossBar.setTitle(format("&3En cours de développement..."));
                        count = -1;
                        break;
                }
                progress = progress - time;
                if(progress <= 0){
                    count++;
                    progress = 1.0;
                }
            }
        }, 0, 0);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    private String format(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
