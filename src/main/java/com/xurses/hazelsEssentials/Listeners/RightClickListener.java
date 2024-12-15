package com.xurses.hazelsEssentials.Listeners;


import com.xurses.hazelsEssentials.HazelsEssentials;
import com.xurses.hazelsEssentials.Utility.CurrencyManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerInteractEvent;


public class RightClickListener implements Listener {

    @EventHandler
    public void checkRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        Material iteminHand = e.getPlayer().getEquipment().getItemInMainHand().getType();


            //player.sendMessage(" Action: " + e.getAction() +  block.getBlockData() + "Used Block: " + iteminHand);
        }
    }

