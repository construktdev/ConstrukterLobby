package de.construkter.construkterlobby.commands;

import de.construkter.construkterlobby.manager.DailyManager;
import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckPremiumCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String playerName = commandSender.getName();
        if (commandSender instanceof Player) {
            if (DailyManager.hasPremium(playerName)){
                commandSender.sendMessage(Prefix.PluginPrefix() + ChatColor.DARK_AQUA + "Du hast " + ChatColor.GOLD + "" + ChatColor.BOLD + "Premium" + ChatColor.DARK_AQUA + "!");
                Player p = (Player) commandSender;
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10 ,1);
                return true;
            }
            commandSender.sendMessage(Prefix.PluginPrefix() + ChatColor.RED + "Du hast kein " + ChatColor.GOLD + "" + ChatColor.BOLD + "Premium" + ChatColor.RED + "!");
            return true;
        }
        commandSender.sendMessage(Prefix.errorPrefix() + ChatColor.DARK_RED + "Nur Spieler können diesen Befehl nutzen");
        return true;
    }
}
