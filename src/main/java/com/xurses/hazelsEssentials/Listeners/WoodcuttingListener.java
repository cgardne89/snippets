package com.xurses.hazelsEssentials.Listeners;

import com.xurses.hazelsEssentials.HazelsEssentials;
import com.xurses.hazelsEssentials.Jobs.WoodcuttingJob;
import com.xurses.hazelsEssentials.Utility.ArrayLists;
import com.xurses.hazelsEssentials.Utility.ConfigHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class WoodcuttingListener implements Listener {
    File file = new File(JavaPlugin.getProvidingPlugin(HazelsEssentials.class).getDataFolder(), "Check_Lists.yml");

    ArrayList<String> TempArray =  new ArrayList<>(ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials .class), file, "logTypes"));
    ArrayList<Material> logTypes = new ArrayList<>(ArrayLists.convertStringToMaterialList((ArrayList<String>) TempArray));


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        Material mat = e.getBlock().getType();

        if (logTypes.contains(mat)){
            if (WoodcuttingJob.isWoodcutting(player)) {
                WoodcuttingJob.payForWork(player);
            }
        }


    }
}
