package de.construkter.construkterlobby.hotbar;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportBackToSpawn implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getBlockY() <= 0){
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + p.getName() + " -1.49 90.00 13.49 771.89 -3.75");
        }
    }
}
