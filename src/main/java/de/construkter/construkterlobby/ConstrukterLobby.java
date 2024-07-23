package de.construkter.construkterlobby;

import de.construkter.construkterlobby.commands.AddPremiumCommand;
import de.construkter.construkterlobby.commands.GetCoinsCommand;
import de.construkter.construkterlobby.daily.GUI;
import de.construkter.construkterlobby.events.DisableDefaultEvents;
import de.construkter.construkterlobby.events.JoinEvents;
import de.construkter.construkterlobby.hotbar.Navigator;
import de.construkter.construkterlobby.hotbar.TeleportBackToSpawn;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConstrukterLobby extends JavaPlugin {

    private static ConstrukterLobby instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("ConstruktLobbySystem is enabled");
        getLogger().info("Disabled all Lobby Events!");
        getServer().getPluginManager().registerEvents(new DisableDefaultEvents(), this);
        getServer().getPluginManager().registerEvents(new Navigator(), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new JoinEvents(), this);
        getServer().getPluginManager().registerEvents(new GUI(), this);
        getServer().getPluginManager().registerEvents(new TeleportBackToSpawn(), this);
        getCommand("coins").setExecutor(new GetCoinsCommand());
        getCommand("addpremium").setExecutor(new AddPremiumCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ConstrukterLobby getInstance() {
        return instance;
    }
}
