package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespawnOreHandler implements Listener {
    File file = new File(JavaPlugin.getProvidingPlugin(HazelsEssentials.class).getDataFolder(), "Check_Lists.yml");

    List<String> TempArray = ConfigHandler.loadListFromConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), file, "ores");
    ArrayList<Material> ores = new ArrayList<>(ArrayLists.convertStringToMaterialList((ArrayList<String>) TempArray));
    public static Map<Location, Material> blockLogging = new HashMap<>();
    Map<Material, Long> respawnTimers = new HashMap<>();



    @EventHandler
    public void onBlocksBroken(BlockBreakEvent e) {
        Material mBlock = e.getBlock().getType();
        Block block = e.getBlock();
        Location lBlock = e.getBlock().getLocation();
        Player player = e.getPlayer();
        long baseTime = 20 * 3600L;
        for (int i = 0; i < ArrayLists.ores.size(); i++ ){
            Long respawnTime = baseTime * (i + 1);
            respawnTimers.put(ArrayLists.ores.get(i), respawnTime);

        }


        if (ores.contains(mBlock) && player.isSneaking()){

            for (ItemStack itemStack : block.getDrops()) {
                ItemStack droppedItem = new ItemStack(itemStack);
                block.getLocation().getWorld().dropItem(block.getLocation(), droppedItem);
            }

            blockLogging.put(lBlock, mBlock);
            block.setType(Material.BEDROCK);
            e.setCancelled(true);

            long respawnTime = respawnTimers.getOrDefault(mBlock, 20 * 3600L);
            new BukkitRunnable() {
                Block block = e.getBlock();
                Player player = e.getPlayer();
                @Override
                public void run() {
                if (blockLogging.containsKey(lBlock)){
                    block.setType(blockLogging.get(lBlock));
                    blockLogging.remove(lBlock);
                }
                }

            }.runTaskTimer(HazelsEssentials.getPlugin(HazelsEssentials.class), respawnTime, 0L);
        }
    }
}