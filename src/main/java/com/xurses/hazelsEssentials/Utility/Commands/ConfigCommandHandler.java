package com.xurses.hazelsEssentials.Utility.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigCommandHandler implements CommandExecutor {

    private final JavaPlugin plugin;

    public ConfigCommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        // Check permissions
        if (!player.hasPermission("hazelsessentials.config")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /config <add/remove> <key> [value]");
            return true;
        }

        String action = args[0].toLowerCase();
        String key = args[1];
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        FileConfiguration config = plugin.getConfig();

        switch (action) {
            case "add":
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "Usage: /config add <key> <value>");
                    return true;
                }
                String value = args[2];

                // Check if the key refers to a list
                if (config.contains(key)) {
                    // If the value is a list, append to it
                    if (config.get(key) instanceof java.util.List) {
                        @SuppressWarnings("unchecked")
                        java.util.List<String> list = (java.util.List<String>) config.get(key);
                        list.add(value);  // Append the new value
                        config.set(key, list);  // Update the config with the modified list
                    } else {
                        // If it's not a list, just set the value normally
                        config.set(key, value);
                    }
                } else {
                    // If the key doesn't exist, create a new list with the value
                    config.set(key, java.util.Arrays.asList(value));
                }

                saveConfig(config, configFile);
                player.sendMessage(ChatColor.GREEN + "Config key '" + key + "' added to '" + value + "'.");
                break;

            case "remove":
                if (!config.contains(key)) {
                    player.sendMessage(ChatColor.RED + "The key '" + key + "' does not exist in the config.");
                    return true;
                }
                config.set(key, null);
                saveConfig(config, configFile);
                player.sendMessage(ChatColor.GREEN + "Config key '" + key + "' has been removed.");
                break;

            default:
                player.sendMessage(ChatColor.RED + "Unknown action: " + action + ". Use 'add' or 'remove'.");
        }

        return true;
    }

    private void saveConfig(FileConfiguration config, File configFile) {
        try {
            config.save(configFile);
            plugin.reloadConfig();
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to save config.yml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
