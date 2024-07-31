package de.construkter.construkterlobby;

import de.construkter.construkterlobby.commands.*;
import de.construkter.construkterlobby.daily.GUI;
import de.construkter.construkterlobby.events.DisableDefaultEvents;
import de.construkter.construkterlobby.events.JoinEvents;
import de.construkter.construkterlobby.helpOP.moderation.MessageAPI;
import de.construkter.construkterlobby.hotbar.Navigator;
import de.construkter.construkterlobby.hotbar.TeleportBackToSpawn;
import de.construkter.construkterlobby.manager.Prefix;
import de.construkter.construkterlobby.server.RandomMOTD;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConstrukterLobby extends JavaPlugin {

    private static ConstrukterLobby instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info(Prefix.PluginPrefix() + "Plugin is loading...");
        getServer().getPluginManager().registerEvents(new DisableDefaultEvents(), this);
        getServer().getPluginManager().registerEvents(new Navigator(), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new JoinEvents(), this);
        getServer().getPluginManager().registerEvents(new GUI(), this);
        getServer().getPluginManager().registerEvents(new TeleportBackToSpawn(), this);
        getServer().getPluginManager().registerEvents(new MessageAPI(), this);
        getCommand("coins").setExecutor(new GetCoinsCommand());
        getCommand("addpremium").setExecutor(new AddPremiumCommand());
        getCommand("premium").setExecutor(new CheckPremiumCommand());
        getCommand("addcoins").setExecutor(new AddCoinsCommand());
        getCommand("setcoins").setExecutor(new SetCoinsCommand());
        getCommand("hilfe").setExecutor(new HelpCommand());
        RandomMOTD r = new RandomMOTD();
        r.runTaskTimer(this, 0L, 3000L);
        /*JDA jda = JDABuilder.createDefault("token")
                .setActivity(Activity.playing("Minecraft"))
                .build();
        DiscordLogger.sendDiscordMessage(jda, "Plugin enabled");
         */
        getLogger().info(Prefix.PluginPrefix() + "Plugin is enabled!");
    }

    @Override
    public void onDisable() {

    }

    public static ConstrukterLobby getInstance() {
        return instance;
    }

}
