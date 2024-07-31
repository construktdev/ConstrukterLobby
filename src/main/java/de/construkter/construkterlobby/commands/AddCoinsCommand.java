package de.construkter.construkterlobby.commands;

import de.construkter.construkterlobby.daily.CoinsManager;
import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AddCoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Object[] args = strings;
        int amount = 0;
        try {
            amount = Integer.parseInt((String) args[0]);
        } catch (Exception e){
            if (commandSender.hasPermission("construkter.lobby.errors.castException")){
                commandSender.sendMessage(Prefix.PluginPrefix() + "Fehler beim parsen einen Integers: \n" + e.getMessage());
                commandSender.sendMessage(Prefix.errorPrefix() + "Usage: /addcoins <amount> <player>");
                return true;
            }
        }
        if (!(args[1] instanceof String)){
            commandSender.sendMessage(Prefix.errorPrefix() + "Usage: /addcoins <amount> <player>");
            return true;
        }
        if (commandSender.hasPermission("construkter.lobby.coins.add")) {
            if (strings.length != 2){
                commandSender.sendMessage(Prefix.errorPrefix() + "Usage: /addcoins <amount> <player>");
                return true;
            }
            CoinsManager.addCoins(amount, args[1].toString());
            commandSender.sendMessage(Prefix.coinsPrefix() + "Du hast " + amount + " Coin(s) zu " + args[1].toString() + "'s Konto hinzugefügt");
            return true;
        }
        return false;
    }
}
