package net.mythigame.hubplugin.Commands;

import net.mythigame.commons.Account;
import net.mythigame.hubplugin.HubPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("NullableProblems")
public class infoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)){
            return true;
        }

        Account account = new Account().getAccount(player.getUniqueId());
        if(account != null){
            player.sendMessage("§9Grade : " + account.getGrade().getName() + "\n" +
                    "§9Coins : " + account.getCoins() + "\n" +
                    "§9Level : " + account.getLevel() + "\n" +
                    "§9Xp : " + account.getXp() + "\n" +
                    "§9Guild : " + account.getGuild());
        }else{
            player.sendMessage("§9[Zeus] Votre compte est introuvable !");
        }
        return true;
    }
}
