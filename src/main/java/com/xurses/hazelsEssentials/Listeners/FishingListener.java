package com.xurses.hazelsEssentials.Listeners;

import com.xurses.hazelsEssentials.Jobs.FishingJob;
import com.xurses.hazelsEssentials.Utility.*;
import com.xurses.hazelsEssentials.HazelsEssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.broadcastMessage;


public class FishingListener implements Listener {



    @EventHandler
    public void onFishing(PlayerFishEvent e) {
        Player player = e.getPlayer();
        Entity caughtEntity = e.getCaught();

        Random rand = new Random();

        if (caughtEntity == null) {
            return;
        }

        String fishedUp = caughtEntity.getName();

        if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH && fishedUp != null) {
            ArrayList rareFishingDrops = (ArrayList) ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "Lists.rareFishingDrops");

            if (rareFishingDrops.contains(fishedUp)) {
                broadcastMessage(ChatColor.RED + player.getName() + " fished up a rare " + fishedUp);
            }

            if (rand.nextFloat() <= 0.01 && caughtEntity instanceof Item) {
                try {
                    List<String>TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "Lists.airBosses");
                    ArrayList<EntityType> airBosses = new ArrayList<>(ArrayLists.convertStringToEntityType((ArrayList<String>) TempArray));
                    BossHandler.spawnCustomMob(player.getLocation(), player, airBosses.get(rand.nextInt(airBosses.size())), 0.01, 10, 1, 10);
                    player.sendMessage("You caught a terrifying monster!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    player.sendMessage(ChatColor.RED + "Failed to spawn the monster: " + ex.getMessage());
                }
            } else {

                if (FishingJob.isFishing(player)) {
                    FishingJob.payForWork(player);
                }
                player.sendMessage("You caught a " + fishedUp);

            }
        }
    }
}



