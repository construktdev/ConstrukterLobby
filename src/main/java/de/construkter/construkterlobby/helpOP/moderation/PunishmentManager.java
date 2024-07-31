package de.construkter.construkterlobby.helpOP.moderation;

import de.construkter.construkterlobby.helpOP.logger.Logger;
import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PunishmentManager {
    public static void punish(Player player, String reason) {
            Player admin = Bukkit.getPlayer("Construkter");
            player.sendMessage(Prefix.warn() + reason);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,10,1);
            Logger.log("minecraft", player.getName() + " wurde gewarnt: " + reason, admin);
    }
}