package de.construkter.construkterlobby.daily;

import de.construkter.construkterlobby.manager.Prefix;
import de.construkter.construkterlobby.manager.DailyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.FileNotFoundException;

public class GUI implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() == null) return;
        Material clicked = event.getClickedBlock().getType();
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        if (clicked == Material.ENDER_CHEST){
            event.setCancelled(true);
            createGUI(player);
        }
    }

    private static void createGUI(Player player) {
        org.bukkit.inventory.Inventory dailyRewards = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Daily Rewards");
        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        ItemMeta goldMeta = gold.getItemMeta();
        assert goldMeta != null;
        goldMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Standard Reward");
        gold.setItemMeta(goldMeta);
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = diamond.getItemMeta();
        assert diamondMeta != null;
        diamondMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Premium Reward");
        diamond.setItemMeta(diamondMeta);

        dailyRewards.setItem(11, diamond);
        dailyRewards.setItem(15, gold);

        player.openInventory(dailyRewards);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws FileNotFoundException {
        String player = event.getWhoClicked().getName();
        Player p = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Daily Rewards")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            //daily bereits eingelöst
            if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {
                if (DailyManager.hasClaimedDailyRewardNormal(player)){
                    event.getWhoClicked().sendMessage(Prefix.coinsPrefix() + ChatColor.RED + "Du hast deine tägliche Belohnung bereits abgeholt!");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
                    p.closeInventory();
                    return;
                }
                CoinsManager.addCoins(5, player);
                event.getWhoClicked().sendMessage(Prefix.coinsPrefix() + ChatColor.DARK_AQUA + "Du hast " + ChatColor.GOLD + "" + ChatColor.BOLD + "5" + ChatColor.DARK_AQUA + " Coins erhalten.");
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10 ,1);
                DailyManager.addClaimedDailyRewardNormal(player);
            }
            if (event.getCurrentItem().getType() == Material.DIAMOND) {
                if (!(DailyManager.hasPremium(player))){
                    event.getWhoClicked().sendMessage(Prefix.coinsPrefix() + ChatColor.RED + "Du hast kein Premium!");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
                    p.closeInventory();
                    return;
                }
                if (DailyManager.hasClaimedDailyReward(player)){
                    event.getWhoClicked().sendMessage(Prefix.coinsPrefix() + ChatColor.RED + "Du hast deine tägliche Belohnung bereits abgeholt!");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
                    p.closeInventory();
                    return;
                }
                CoinsManager.addCoins(10, player);
                event.getWhoClicked().sendMessage(Prefix.coinsPrefix() + ChatColor.DARK_AQUA + "Du hast " + ChatColor.GOLD + "" + ChatColor.BOLD + "10" + ChatColor.DARK_AQUA + " Coins erhalten.");
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10 ,1);
                DailyManager.addClaimedDailyReward(player);
            }
        }
    }
}
