package de.construkter.construkterlobby.commands;

import de.construkter.construkterlobby.daily.CoinsManager;
import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetCoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Object[] args = strings;
        int amount = 0;
        try {
            amount =  Integer.parseInt((String) args[0]);
        } catch (Exception e){
            if (commandSender.hasPermission("construkter.lobby.errors.castException")){
                commandSender.sendMessage(Prefix.PluginPrefix() + "Fehler beim parsen einen Integers: \n" + e.getMessage());
                commandSender.sendMessage(Prefix.errorPrefix() + "Usage: /setcoins <amount> <player>");
                return true;
            }
        }
        if (commandSender.hasPermission("construkter.lobby.coins.set")) {
            if (args.length != 2){
                commandSender.sendMessage(Prefix.errorPrefix() + "Usage: /setcoins <amount> <player>");
                return true;
            }
            if (!(args[1] instanceof String)){
                commandSender.sendMessage(Prefix.errorPrefix() + "Usage: /setcoins <amount> <player>");
                return true;
            }
            CoinsManager.setCoins(amount, strings[1]);
            commandSender.sendMessage(Prefix.coinsPrefix() + "Du hast die Coins des Spielers " + strings[1] + " auf " + strings[0] + " gesetzt.");
            return true;
        }
        return false;
    }
}
