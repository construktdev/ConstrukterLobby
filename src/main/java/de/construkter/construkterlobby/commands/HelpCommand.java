package de.construkter.construkterlobby.commands;

import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage(Prefix.PluginPrefix() + "Command-Liste");
        command(player);
        return true;
    }

    private static void command(Player player) {
        player.sendMessage(ChatColor.AQUA + "/help" + ChatColor.DARK_AQUA + "-> Zeigt die diese Liste");
        player.sendMessage(ChatColor.AQUA + "/addcoins" + ChatColor.DARK_AQUA + "-> " + ChatColor.DARK_RED + "[ADMIN] " + ChatColor.DARK_AQUA + "Gebe einem Nutzer eine bestimmte Anzahl an Coins");
        player.sendMessage(ChatColor.AQUA + "/setcoins" + ChatColor.DARK_AQUA + "-> " + ChatColor.DARK_RED + "[ADMIN] " + ChatColor.DARK_AQUA + "Bestimme die Anzahl an Coins von einem Spieler");
        player.sendMessage(ChatColor.AQUA + "/coins" + ChatColor.DARK_AQUA + "-> Zeigt dir wie viele Coins du besitzt");
        player.sendMessage(ChatColor.AQUA + "/addpremium" + ChatColor.DARK_AQUA + "-> " + ChatColor.DARK_RED + "[ADMIN] " + ChatColor.DARK_AQUA + "Gebe einem Nutzer manuell den Premium-Rang");
        player.sendMessage(ChatColor.AQUA + "/premium" + ChatColor.DARK_AQUA + "-> Zeigt dir ob du den Premium-Rang besitzt");
    }
}
