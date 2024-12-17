package com.xurses.hazelsEssentials.Listeners;


import com.xurses.hazelsEssentials.Utility.ConfigHandler;
import com.xurses.hazelsEssentials.Utility.Serialization_Practice;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;

public class RightClickListener implements Listener {


        @EventHandler
        public void onPlayerRightClick(PlayerInteractEvent event) {
// chicken butt

                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {


                }
        }
}





            //player.sendMessage(" Action: " + e.getAction() +  block.getBlockData() + "Used Block: " + iteminHand);



