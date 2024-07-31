package de.construkter.construkterlobby.events;

import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvents implements Listener {
    String pass = null;
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + player.getName() + " -1.49 90.00 13.49 771.89 -3.75");
            player.sendTitle(ChatColor.AQUA + "Willkommen, " + player.getName(), ChatColor.DARK_AQUA + "Auf " + ChatColor.BOLD +  "Construkter.de" + ChatColor.AQUA + " Viel Spaß beim spielen!", 0, 100, 5 );
            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "-----------------------");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Herzlich Willkommen " + player.getName());
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Nutze den Navigator um einem Server beizutreten!");
            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "-----------------------");
            event.setJoinMessage(null);
            if (!(event.getPlayer().hasPermission("construkter.lobby.join.op"))){
                return;
            }
            player.sendMessage("");
            player.sendMessage(Prefix.adminMessage() + ChatColor.DARK_GREEN + "Für das Admin Panel gehe auf https://panel.construkter.de/lobby");

    }
}