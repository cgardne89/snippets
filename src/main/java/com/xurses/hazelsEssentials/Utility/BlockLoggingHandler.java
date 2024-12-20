package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.*;

public class BlockLoggingHandler implements Listener {
    public record MultiValues(String block, Location loc){}
    List<String> blockLogging = new ArrayList<>();


    

    @EventHandler
    public void onBlocksBroken(BlockBreakEvent e){
        String block = e.getBlock().getType().name();
        Location loc = e.getBlock().getLocation();
        Player player = e.getPlayer();
        String test = String.valueOf(new MultiValues(block, loc));

        blockLogging.add(test);




        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), player.getName(), blockLogging);
    }
}
