package net.mythigame.hubplugin.Utils.scoreboard;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import net.mythigame.commons.Account;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PersonnalScoreboard {

    public static void initPersonnalScoreboard(Player player){
        Account account = new Account().getAccount(player.getUniqueId());

        if(account == null) return;

        BPlayerBoard board = Netherboard.instance().createBoard(player, ChatColor.GOLD + "MGC");
        String guild = "N/A";
        if(account.getGuild() != null) guild = account.getGuild();
        String rank;
        if(account.getGrade().getName().equals("Joueur")){
            rank = "§7Joueur";
        }else{
            rank = account.getGrade().getPrefix().replace("[", "").replace("]", "").replaceAll(" ", "");
        }
        board.setAll(
                "",
                "Pseudo: " + player.getName(),
                " ",
                ChatColor.RED + "Rang: " + rank,
                "  ",
                ChatColor.GOLD + "Coins: " + account.getCoins() + " coins",
                "   ",
                ChatColor.DARK_GREEN + "Niveau: " + account.getLevel(),
                "    ",
                ChatColor.DARK_AQUA + "Guild: " + guild,
                "     ",
                ChatColor.GOLD + "www.mythigame.net"
        );

        player.setScoreboard(board.getScoreboard());
    }
}
