package com.xurses.hazelsEssentials;


import com.xurses.hazelsEssentials.Jobs.JobManager;
import com.xurses.hazelsEssentials.Listeners.*;
import com.xurses.hazelsEssentials.Utility.*;
import com.xurses.hazelsEssentials.Utility.Commands.ConfigCommandHandler;
import com.xurses.hazelsEssentials.Utility.Commands.CoordsCommand;
import com.xurses.hazelsEssentials.Utility.Commands.JobsCommand;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public final class HazelsEssentials extends JavaPlugin implements Listener {
    private static Economy econ = null;

    @Override
    public void onDisable() {
        for (Map.Entry<Location, Material> entry : RespawnOreHandler.blockLogging.entrySet()) {
            Location location = entry.getKey();
            Material material = entry.getValue();

            if (location.getWorld() != null) { // Ensure world is loaded
                Block block = location.getBlock();
                block.setType(material);
            }
        }
        RespawnOreHandler.blockLogging.clear();

        getLogger().info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onEnable() {
        setupEconomy();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ArrayLists(), this);


        getCommand("config").setExecutor(new ConfigCommandHandler(this));
        getCommand("mycoords").setExecutor(new CoordsCommand());
        getCommand("job").setExecutor(new JobsCommand(this));

        File file = new File(JavaPlugin.getProvidingPlugin(HazelsEssentials.class).getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        new DataConstants();
        new LevelCalculator();
        ConfigHandler.handleServerData(config, file);
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setupEcon();
        ConfigHandler configHandler = ConfigHandler.getInstance(this);

        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
                configHandler.handlePlayerJoin(event.getPlayer());
            }
        }, this);
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                configHandler.handlePlayerQuit(event.getPlayer());
            }
        }, this);


        pm.registerEvents(new JobManager(), this);
        pm.registerEvents(new BossHandler(), this);
        pm.registerEvents(new RightClickListener(), this);
        pm.registerEvents(new Debuffs(), this);
        pm.registerEvents(new CookingListener(), this);
        pm.registerEvents(new WoodcuttingListener(), this);
        pm.registerEvents(new CombatListener(), this);
        pm.registerEvents(new SmeltingListener(), this);
        pm.registerEvents(new GatheringListener(), this);
        pm.registerEvents(new FishingListener(), this);
        pm.registerEvents(new BlockLoggingHandler(), this);
        pm.registerEvents(new RespawnOreHandler(), this);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getWorlds().get(0).getTime() == 13000) { // Trigger at the start of night
                    Debuffs.applyNightDebuffs();
                }
            }
        }.runTaskTimer(this, 0, 20 * 60); // Runs every minute
    }


    private void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            getLogger().info("Vault not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (rsp == null) {
            getLogger().info("Could not register economy service");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Economy economy = rsp.getProvider();
        if (economy == null){
            getLogger().info("Economy provider is not available");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private boolean setupEcon(){
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {

        return econ;
    }

    }


