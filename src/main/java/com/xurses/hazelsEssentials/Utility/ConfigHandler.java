package com.xurses.hazelsEssentials.Utility;

import com.google.gson.JsonObject;
import com.xurses.hazelsEssentials.Jobs.FishingJob;
import com.xurses.hazelsEssentials.Jobs.JobManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.*;

import static org.bukkit.Bukkit.getLogger;

public class ConfigHandler{

    private static File playerDataFolder = null;
    private static ConfigHandler instance;
    // TBD // JsonObject jsonObject = new JsonObject();


    private ConfigHandler(JavaPlugin plugin) {
        this.playerDataFolder = new File(plugin.getDataFolder(), "playerdata");
        if (!playerDataFolder.exists()) {
            if (playerDataFolder.mkdirs()) {
                getLogger().info("Player data folder created: " + playerDataFolder.getAbsolutePath());
            } else {
                getLogger().severe("Failed to create player data folder: " + playerDataFolder.getAbsolutePath());
            }
        }
    }

    public static void replacePlayerData(Player player, String key, Object newValue) {
        // Get the player's file
        File playerFile = getPlayerFile(player);
        if (!playerFile.exists()) {
            getLogger().severe("Player file not found for: " + player.getName());
            return;
        }

        // Load the file into a FileConfiguration object
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Update the key with the new value
        playerData.set(key, newValue);

        // Save the updated configuration back to the file
        savePlayerFile(playerData, playerFile);

        getLogger().info("Updated key '" + key + "' for player: " + player.getName());
    }

    public static synchronized ConfigHandler getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ConfigHandler(plugin);
        }
        return instance;
    }

    public static File getPlayerFile(Player player) {
        return new File(playerDataFolder, player.getUniqueId() + ".yml");
    }

    public static void savePlayerFile(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            getLogger().severe("Failed to save player file: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public static void handlePlayerData(FileConfiguration playerData, Player player){
        playerData.set(DataConstants.NAME_KEY, player.getName());
        playerData.set(DataConstants.UUID_KEY, player.getUniqueId().toString());
        playerData.set("Stats.Fishing.CurrentXP", FishingJob.baseXP);
        playerData.set("Stats.Fishing.Level", FishingJob.level);
        playerData.set("Job", JobManager.Job);
    }

    public static void handleServerData(FileConfiguration serverData, File file) {
        if (!file.exists()) {
            serverData.set(DataConstants.MAXBANK_KEY.get(0), DataConstants.MAXBANK_KEY.get(1));
            serverData.set(DataConstants.MAXSTATLEVEL_KEY.get(0), DataConstants.MAXSTATLEVEL_KEY.get(1));
            try {
                serverData.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            DataConstants.MAXBANK_KEY.add(1, serverData.get("Max_Bank").toString());
        }
    }

    public void handlePlayerQuit(Player player){
        File playerFile = getPlayerFile(player);
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        handlePlayerData(playerData, player);
        savePlayerFile(playerData, playerFile);
        getLogger().info(player.getName() + "'s file saved");
    }

    public void handlePlayerJoin(Player player) {
        File playerFile = getPlayerFile(player);
        if (!playerFile.exists()) {
            try {
                if (playerFile.createNewFile()) {
                    FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
                    handlePlayerData(playerData, player);
                    savePlayerFile(playerData, playerFile);
                    getLogger().info("Player file created successfully for: " + player.getName());
                } else {
                    getLogger().severe("Failed to create file for player: " + player.getName());
                }
            } catch (IOException e) {
                getLogger().severe("Error creating player file for: " + player.getName());
                e.printStackTrace();
            }
        } else {
            FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
            FishingJob.baseXP = playerData.getInt("Stats.Fishing.CurrentXP");
            FishingJob.level = playerData.getInt("Stats.Fishing.Level");
            JobManager.Job = Objects.requireNonNull(playerData.get("Job")).toString();
            CurrencyManager.eco.depositPlayer(player, playerData.getDouble("Current_Balance"));
            getLogger().info("File already exists for player: " + player.getName());
        }
    }


    public static void saveListToConfig(JavaPlugin plugin, String key, List<String> list) {
        File configFile = new File(plugin.getDataFolder(), "Check_Lists.yml");
        FileConfiguration file = YamlConfiguration.loadConfiguration(configFile);


        // Ensure the config file exists
        if (!configFile.exists()) {
            try {
                if (configFile.createNewFile()) {
                    file.save(configFile);
                    plugin.getLogger().info("Created config.yml file.");
                }
            } catch (IOException e) {
                plugin.getLogger().severe("Failed to create config.yml file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                file.set(key, list);
                file.save(configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static List<String> loadListFromConfig(JavaPlugin plugin, File file, String key) {

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        // Check if the key exists
        if (config.contains(key)) {
            return config.getStringList(key); // Load the list as a List of Strings
        } else {
            plugin.getLogger().warning("Key '" + key + "' not found in config.yml.");
            return new ArrayList<>(); // Return an empty list if the key doesn't exist
        }
    }


}
