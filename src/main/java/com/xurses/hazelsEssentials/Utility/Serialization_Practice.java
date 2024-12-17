package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import net.md_5.bungee.api.chat.ItemTag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Byte.valueOf;

public class Serialization_Practice {




   /* // get all information on a block
    public static String serializeBlock(Block block, Player player) throws IOException {
        StringBuilder blockData = new StringBuilder();
        Material material = block.getType();
        BlockData blockState = block.getBlockData();
        World blockWorld = block.getLocation().getWorld();
        int blockX = block.getLocation().getBlockX();
        int blockY = block.getLocation().getBlockY();
        int blockZ = block.getLocation().getBlockZ();

        // Gathering the block's individual data values

        // Block's material
        blockData.append("material=" + material.name() + ";");

        // Block's state
        blockData.append("state=" + blockState.getAsString() + ";");

        // Block's location
        blockData.append("world=" + blockWorld.getName() + ";");
        blockData.append("x=" + blockX + ";");
        blockData.append("y=" + blockY + ";");
        blockData.append("z=" + blockZ + ";");

        File configFile = new File(JavaPlugin.getProvidingPlugin(HazelsEssentials.class).getDataFolder(), "config.yml");
        FileConfiguration file = YamlConfiguration.loadConfiguration(configFile);
        file.set("Saved_Block", blockData.toString());
        file.save(configFile);

        // convert it to a string
        return blockData.toString();
    }

    // convert it to a block again
    public static Block deserializeBlock(String serializeBlock){
        String[] reCreateBlockFromStrings = serializeBlock.split(";");
        Block block = null;
        Material material = null;
        BlockData blockState = null;
        Location blockLocation = null;
        World blockWorld = null;
        int blockX = 0;
        int blockY = 0;
        int blockZ = 0;

        for (String reCreateBlockFromString : reCreateBlockFromStrings) {
            // Get the material from the string
            if (reCreateBlockFromString.startsWith("material=")) {
                material = Material.valueOf(reCreateBlockFromString.substring("material=".length()));
            }
            // Get the block state from the string
            if (reCreateBlockFromString.startsWith("state=")) {
                blockState = Bukkit.createBlockData(reCreateBlockFromString.substring("state=".length()));
            }


            // Get the block location from the string
            if (reCreateBlockFromString.startsWith("world")) {
                blockWorld = Bukkit.getWorld(reCreateBlockFromString.substring("world=".length()));
            }
            if (reCreateBlockFromString.startsWith("x=")) {
                blockX = Integer.parseInt(reCreateBlockFromString.substring("x=".length()));
            }
            if (reCreateBlockFromString.startsWith("y=")) {
                blockY = Integer.parseInt(reCreateBlockFromString.substring("y=".length()));
            }
            if (reCreateBlockFromString.startsWith("z=")) {
                blockZ = Integer.parseInt(reCreateBlockFromString.substring("z=".length()));
            }
            blockLocation = new Location(blockWorld, blockX, blockY, blockZ);
        }



        block = blockLocation.getWorld().getBlockAt(blockLocation);
        block.setType(material);
        block.setBlockData(blockState);

        // save to main config file


        return block;


    }*/



}
