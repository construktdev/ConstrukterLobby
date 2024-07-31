package de.construkter.construkterlobby.commands;

import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetCoinsCommand implements CommandExecutor {
    private static final String FILE_NAME = "coins.txt";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Map<String, Integer> coinMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(" ");
                if (split.length == 2){
                    String username = split[0];
                    int userCoins = Integer.parseInt(split[1]);
                    coinMap.put(username, userCoins);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            commandSender.sendMessage(Prefix.errorPrefix() + ChatColor.RED + "Es ist ein Fehler aufgteretn!");
        }
        String name = commandSender.getName();
        int playerCoions = coinMap.get(name);
        commandSender.sendMessage(Prefix.PluginPrefix() + ChatColor.DARK_AQUA + "Du hast " + ChatColor.GOLD + "" + ChatColor.BOLD + playerCoions + ChatColor.DARK_AQUA + " Coins gesammelt!");
        return true;
    }
}
