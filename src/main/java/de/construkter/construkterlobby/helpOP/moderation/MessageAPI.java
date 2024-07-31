package de.construkter.construkterlobby.helpOP.moderation;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageAPI implements Listener {
    static String[] badwords = {
            "penis",
            "hure",
            "nigga",
            "niger",
            "niga",
            "niger",
            "schlampe",
            "bastard"
    };
    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        for (String badword : badwords) {
            if (event.getMessage().toLowerCase().contains(badword)) {
                event.setCancelled(true);
                PunishmentManager.punish(event.getPlayer(), "Phrase 1: Benutzen verbotener Wörter");
            }
        }
    }
}
