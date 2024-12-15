package com.xurses.hazelsEssentials.Utility.Commands;


import com.xurses.hazelsEssentials.HazelsEssentials;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class CoordsCommand implements CommandExecutor {

    public record PlayerData(boolean switchState, BukkitTask task) {}
    Map<Player, PlayerData> commandState = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }


        PlayerData playerData = commandState.computeIfAbsent(player, p -> new PlayerData(true, null));

        if (!commandState.containsKey(player)) {
            commandState.put(player, new PlayerData(true, playerData.task()));
        }


        if (playerData != null && !playerData.switchState()) {
            playerData.task().cancel();
            commandState.put(player, new PlayerData(true, playerData.task()));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
            return true;
        } else {

            BukkitTask task = new BukkitRunnable() {

                @Override
                public void run() {
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }


                    double x = player.getLocation().getX();
                    double y = player.getLocation().getY();
                    double z = player.getLocation().getZ();


                    String message = String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                }

            }.runTaskTimer(HazelsEssentials.getPlugin(HazelsEssentials.class), 0L, 20L);
            commandState.put(player, new PlayerData(false, task));
        }
        return true;
        }


}
