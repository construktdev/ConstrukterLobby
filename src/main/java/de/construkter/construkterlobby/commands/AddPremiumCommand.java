package de.construkter.construkterlobby.commands;

import de.construkter.construkterlobby.manager.DailyManager;
import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddPremiumCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("construkter.lobby.addpremium")) {
            if (!(strings.length == 1)) {
                commandSender.sendMessage(Prefix.errorPrefix() + ChatColor.RED + "Nutzung: /addpremium <player>");
                return true;
            }
            DailyManager.addPremium(strings[0]);
            commandSender.sendMessage(Prefix.PluginPrefix() + ChatColor.GREEN + "Du hast " + strings[0] + " den Premium Rang gegeben!");
            return true;
        }
        return false;
    }
}
