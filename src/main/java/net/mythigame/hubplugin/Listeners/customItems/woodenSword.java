package net.mythigame.hubplugin.Listeners.customItems;

import net.mythigame.hubplugin.HubPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@SuppressWarnings("ConstantConditions")
public class woodenSword implements Listener {

    private final HubPlugin hubPlugin;

    public woodenSword(HubPlugin hubPlugin){
        this.hubPlugin = hubPlugin;
    }

    @EventHandler
    public void onWoodenSwordRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();

        if(action == Action.RIGHT_CLICK_AIR && event.getItem().getType() == Material.WOODEN_SWORD){
            event.setCancelled(true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

            try {
                out.writeUTF("Connect");
                out.writeUTF("survie");
            }catch (IOException e){
                e.printStackTrace();
            }

            player.sendPluginMessage(hubPlugin, "BungeeCord", byteArrayOutputStream.toByteArray());
        }
    }

}
