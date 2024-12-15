package com.xurses.hazelsEssentials.Listeners;

import com.xurses.hazelsEssentials.Utility.BossHandler;
import com.xurses.hazelsEssentials.HazelsEssentials;
import com.xurses.hazelsEssentials.Utility.ArrayLists;
import com.xurses.hazelsEssentials.Utility.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static com.xurses.hazelsEssentials.Utility.ArrayLists.seeds;

public class GatheringListener implements Listener {

    Random rand = new Random();
    // Map to store harvested items and counts per player
    private final Map<UUID, Map<Material, Integer>> harvestMap = new HashMap<>();
    // Map to store player timers
    private final Map<UUID, BukkitRunnable> playerTimers = new HashMap<>();

    // A map to store timers for each player (with the time of the last crop planted)
    private Map<UUID, BukkitRunnable> cropTimers = new HashMap<>();
    // A map to track how many crops a player has planted within a short period (e.g., 5 seconds)
    private Map<UUID, Integer> cropsPlantedMap = new HashMap<>();
    UUID playerId;

    @EventHandler
    public void onBreakingBlocks(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Material blockType = e.getBlock().getType();
        Block block = e.getBlock();

        List<String> TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "ores");
        ArrayList<Material> ores = new ArrayList<>(ArrayLists.convertStringToMaterialList((ArrayList<String>) TempArray));

        TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "logTypes");
        ArrayList<Material> logTypes = new ArrayList<>(ArrayLists.convertStringToMaterialList((ArrayList<String>) TempArray));

        if (ores.contains(blockType) || logTypes.contains(blockType)) {
            // Add block type to the harvest map
            UUID playerId = player.getUniqueId();
            harvestMap.putIfAbsent(playerId, new HashMap<>());
            Map<Material, Integer> playerHarvest = harvestMap.get(playerId);
            playerHarvest.put(blockType, playerHarvest.getOrDefault(blockType, 0) + 1);


            if (playerTimers.containsKey(playerId)) {
                playerTimers.get(playerId).cancel();
            }

            BukkitRunnable timer = new BukkitRunnable() {
                @Override
                public void run() {
                    Map<Material, Integer> harvestedItems = harvestMap.remove(playerId);
                    if (harvestedItems != null && !harvestedItems.isEmpty()) {
                        harvestedItems.forEach((material, count) ->
                                player.sendMessage("You gathered " + count + " " +
                                        material.toString().toLowerCase().replace("_", " ") + "."));
                    }
                    playerTimers.remove(playerId);
                }
            };
            playerTimers.put(playerId, timer);
            timer.runTaskLater(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), 200L); // 200 ticks = 10 seconds


            if (rand.nextFloat() <= 0.01) {
                TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "landBosses");
                ArrayList<EntityType> landBosses = new ArrayList<>(ArrayLists.convertStringToEntityType((ArrayList<String>) TempArray));
                BossHandler.spawnCustomMob(block.getLocation(), player, landBosses.get(rand.nextInt(landBosses.size())), 0.01, 30, 100, 100);
            }
        }
    }




    // Store the last position where the player planted
    private final Map<UUID, Location> lastPlayerLocationMap = new HashMap<>();


    // Store the last position where the player planted

    // Handle player interactions with farmland
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the player right-clicked on a block
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block block = event.getClickedBlock();

        // Ensure it's farmland and that the block is not null
        if (block != null && block.getType() == Material.FARMLAND) {
            // Store the player who interacted with the farmland block, with their UUID and location
            lastPlayerLocationMap.put(event.getPlayer().getUniqueId(), block.getLocation());

            // Initialize the crop count for the player (if not already done)
            UUID playerId = event.getPlayer().getUniqueId();
            cropsPlantedMap.putIfAbsent(playerId, 0);
            int plantedCrops = cropsPlantedMap.getOrDefault(playerId, 0);
            if (rand.nextFloat() <= 0.01 && plantedCrops != 0) {
                List<String> TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "landBosses");
                ArrayList<EntityType> landBosses = new ArrayList<>(ArrayLists.convertStringToEntityType((ArrayList<String>) TempArray));
                BossHandler.spawnCustomMob(block.getLocation(), event.getPlayer(), landBosses.get(rand.nextInt(landBosses.size())), 0.01, 30, 100, 100);
        }
    }
        }

    // Handle block physics changes (e.g., crop growth)
    @EventHandler
    public void onBlockChange(BlockPhysicsEvent event){
            Block block = event.getBlock();

            // Check if the block is farmland
            if (block.getType() == Material.FARMLAND) {
                // Check if the block above is a crop (e.g., wheat, carrot)
                Block above = block.getRelative(BlockFace.UP);
                List<String> TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "crops");
                ArrayList<Material> crops = new ArrayList<>(ArrayLists.convertStringToMaterialList((ArrayList<String>) TempArray));

                if (crops.contains(above.getType()) || seeds.contains(above.getType())) {
                    playerId = null;

                    // Retrieve the player's UUID based on the block's location
                    for (Map.Entry<UUID, Location> entry : lastPlayerLocationMap.entrySet()) {
                        if (entry.getValue().equals(block.getLocation())) {
                            playerId = entry.getKey();
                            break;
                        }
                    }

                    if (playerId != null) {
                        // Get the player object
                        Player player = Bukkit.getPlayer(playerId);
                        if (player != null) {
                            // Play sound when a crop is planted or grown
                            block.getWorld().playSound(block.getLocation(), Sound.BLOCK_GRASS_STEP, 1.0F, 1.0F);

                            // Increment the number of crops planted by the player
                            cropsPlantedMap.put(playerId, cropsPlantedMap.getOrDefault(playerId, 0) + 1);

                            // Cancel any existing timer for the player
                            if (cropTimers.containsKey(playerId)) {
                                cropTimers.get(playerId).cancel();
                            }

                            // Create a new timer to notify when they stop planting
                            UUID finalPlayerId = playerId;
                            BukkitRunnable timer = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    // If player is still near their last planted location and hasn't moved too far
                                    Location lastLocation = lastPlayerLocationMap.get(finalPlayerId);
                                    if (lastLocation != null && player.getLocation().distance(lastLocation) <= 5) {
                                        // Send a message that planting is ongoing

                                    }
                                }
                            };

                            // Store the timer and run it after a delay of 1 second (20 ticks)
                            cropTimers.put(playerId, timer);
                            timer.runTaskLater(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), 20L); // 20 ticks = 1 second
                        }
                    }
                }
            }
        }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();  // Ensure to get the correct playerId from the player object

        // Check if the player has moved far from their planted location
        if (lastPlayerLocationMap.containsKey(playerId)) {
            Location lastLocation = lastPlayerLocationMap.get(playerId);

            // If the player is more than 5 blocks away from the last planted location, reset
            if (lastLocation != null && player.getLocation().distance(lastLocation) > 5) {
                // Stop tracking and notify player
                int plantedCrops = cropsPlantedMap.getOrDefault(playerId, 0);
                if (plantedCrops != 0){
                    player.sendMessage("You moved too far away, and planting has stopped. You planted a total of " + plantedCrops + " crops. ");
                }


                // Reset the planting location and crop count
                lastPlayerLocationMap.remove(playerId);
                cropsPlantedMap.put(playerId, 0);  // Reset the crop count for the player

                // Cancel any active crop planting timer
                if (cropTimers.containsKey(playerId)) {
                    cropTimers.get(playerId).cancel();
                    cropTimers.remove(playerId); // Ensure the timer is removed
                }
            }
        }
    }

}





