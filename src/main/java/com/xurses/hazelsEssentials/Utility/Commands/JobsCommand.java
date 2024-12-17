package com.xurses.hazelsEssentials.Utility.Commands;

import com.xurses.hazelsEssentials.Jobs.JobManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class JobsCommand implements CommandExecutor {

        private final JavaPlugin plugin;

        public JobsCommand(JavaPlugin plugin) {
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


            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /job set [Job Name]");
                return true;
            }

            String action = args[0].toLowerCase();

            switch (action) {
                case "set":
                    if (args.length < 2) {
                        player.sendMessage(ChatColor.RED + "Usage: /job set [Job Name]");
                        return true;
                    }

                    String value = args[1];

                    JobManager.Job = value.toLowerCase();

                    break;

                default:
                    player.sendMessage(ChatColor.RED + "Unknown action: " + action + ". Use 'add' or 'remove'.");
            }

            return true;
        }
    }

