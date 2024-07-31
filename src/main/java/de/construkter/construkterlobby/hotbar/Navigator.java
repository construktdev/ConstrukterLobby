package de.construkter.construkterlobby.hotbar;

import de.construkter.construkterlobby.manager.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Objects;

public class Navigator implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack compass = new ItemStack(Material.CLOCK);
        ItemMeta meta = compass.getItemMeta();
        if (meta != null){
            meta.setDisplayName(ChatColor.AQUA + "Navigator");
            compass.setItemMeta(meta);
        }

        ItemStack daily = new ItemStack(Material.DIAMOND);
        ItemMeta dailyMeta = daily.getItemMeta();
        if (dailyMeta != null){
            dailyMeta.setDisplayName(ChatColor.AQUA + "Daily Rewards");
            daily.setItemMeta(dailyMeta);
        }

        ItemStack spawn = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta spawnMeta = spawn.getItemMeta();
        if (spawnMeta != null){
            spawnMeta.setDisplayName(ChatColor.AQUA + "Spawn");
            spawn.setItemMeta(spawnMeta);
        }
        player.getInventory().setItem(4, spawn);
        player.getInventory().setItem(8, daily);
        player.getInventory().setItem(0, compass);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Menu")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
                Player player = (Player) event.getWhoClicked();
                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("jump");

                    player.sendPluginMessage(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ConstrukterLobby")), "BungeeCord", b.toByteArray());
                    player.sendMessage(Prefix.PluginPrefix() + ChatColor.GREEN + "Du wurdest erfolgreich mit 'jump' verbunden!");
                } catch (Exception e) {
                    player.sendMessage(Prefix.errorPrefix() + ChatColor.RED + "Es gab einen Fehler beim Verbinden mit 'jump'.");
                    e.printStackTrace();
                }
                player.closeInventory();
            } else if (event.getCurrentItem().getType() == Material.RED_BED) {
                Player player = (Player) event.getWhoClicked();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tp " + player.getName() + " -1.49 90.00 13.49 771.89 -3.75");
                player.sendMessage(Prefix.errorPrefix() +  ChatColor.RED + "Kein Bedwars Server, auf den du beitreten kannst gefunden!");
                player.closeInventory();
            } else if (event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                Player player = (Player) event.getWhoClicked();
                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("varo");

                    player.sendPluginMessage(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ConstrukterLobby")), "BungeeCord", b.toByteArray());
                    player.sendMessage(Prefix.PluginPrefix() + ChatColor.GREEN + "Du wurdest erfolgreich mit 'varo' verbunden!");
                } catch (Exception e) {
                    player.sendMessage(Prefix.errorPrefix() + ChatColor.RED + "Es gab einen Fehler beim Verbinden mit 'varo'.");
                    e.printStackTrace();
                }
                player.closeInventory();
            } else if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
                Player player = (Player) event.getWhoClicked();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tp " + player.getName() + " -1.49 90.00 13.49 771.89 -3.75");
                player.sendMessage(Prefix.errorPrefix() +  ChatColor.RED + "Kein Skywars Server, auf den du beitreten kannst gefunden!");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.CLOCK && event.getItem().getType() != Material.GRASS_BLOCK) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            Inventory menu = Bukkit.createInventory(null, 18, "Menu");

            // Create the grass block item
            ItemStack grassBlock = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta meta = grassBlock.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("§aSquiizyy Jump 'N' Run Event");
                grassBlock.setItemMeta(meta);
            }

            ItemStack redBed = new ItemStack(Material.RED_BED);
            ItemMeta bedMeta = redBed.getItemMeta();
            if (bedMeta != null) {
                bedMeta.setDisplayName(ChatColor.RED + "Bedwars");
                redBed.setItemMeta(bedMeta);
            }

            ItemStack sb = new ItemStack(Material.GRASS_BLOCK);
            ItemMeta sbMeta = sb.getItemMeta();
            if (sbMeta != null) {
                sbMeta.setDisplayName(ChatColor.GOLD + "SkyBlock");
                sb.setItemMeta(sbMeta);
            }

            ItemStack comingSoon = new ItemStack(Material.BARRIER);
            ItemMeta comingSoonMeta = comingSoon.getItemMeta();
            if (comingSoonMeta != null) {
                comingSoonMeta.setDisplayName(ChatColor.RED + "Coming Soon");
                comingSoon.setItemMeta(comingSoonMeta);
            }

            ItemStack varo = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta varoMeta = varo.getItemMeta();
            if (varoMeta != null) {
                varoMeta.setDisplayName(ChatColor.GOLD + "Varo (classic)");
                varo.setItemMeta(varoMeta);
            }

            menu.setItem(8, sb);
            menu.setItem(4, grassBlock);
            menu.setItem(0, redBed);
            menu.setItem(9, varo);
            menu.setItem(13, comingSoon);
            menu.setItem(17, comingSoon);

            player.openInventory(menu);
        }

        if (event.getItem() != null && event.getItem().getType() == Material.DIAMOND) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + event.getPlayer().getName() + " 6.40 93.00 3.18 735.89 2.25");
        }

        if (event.getItem() != null && event.getItem().getType() == Material.GRASS_BLOCK) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tp " + event.getPlayer().getName() + " -1.49 90.00 13.49 771.89 -3.75");

        }
    }
}
