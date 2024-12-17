package com.xurses.hazelsEssentials.Utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class Debuffs implements Listener {

    private static final HashMap<UUID, Integer> awakeNights = new HashMap<>(); // Tracks how many nights players have stayed awake.

    // Event handler for when players sleep in a bed
    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent event) {
        if (!event.isCancelled() && event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Player player = event.getPlayer();
            awakeNights.put(player.getUniqueId(), 0); // Reset awake nights counter when the player sleeps
            Bukkit.getLogger().info(player.getName() + " has slept. Nights awake reset.");
        }
    }

    // Event handler for when players quit the server
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        awakeNights.remove(event.getPlayer().getUniqueId());
    }

    // Method to apply nightly debuffs to all players
    public static void applyNightDebuffs() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isSleeping()) {
                UUID playerId = player.getUniqueId();
                int nightsAwake = awakeNights.getOrDefault(playerId, 0) + 1;
                awakeNights.put(playerId, nightsAwake);

                // Apply effects based on the number of nights awake
                switch (nightsAwake) {
                    case 1:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 60 * 20, 0)); // 20 minutes
                        break;
                    case 2:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 20 * 60 * 20, 1));
                        break;
                    default:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 20 * 60 * 20, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 120, 1)); // 2 minutes
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (player.isOnline()) {
                                    player.teleport(player.getLocation());
                                }
                            }
                        }.runTaskLater(Bukkit.getPluginManager().getPlugin("HazelsEssentials"), 20 * 120); // After blindness ends
                        break;
                }
            }
        }
    }
}
