package de.construkter.construkterlobby.helpOP.logger;

import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.entity.Player;

public class Logger{
    public static void log(String channel, String message, Player player) {
        if (channel.equals("minecraft")) {
            player.sendMessage(Prefix.helpOP() + message);
            return;
        }
        if (channel.equals("discord")) {

        }
    }
}
