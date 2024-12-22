package com.xurses.hazelsEssentials.Utility.Commands;

import com.xurses.hazelsEssentials.Jobs.JobManager;
import com.xurses.hazelsEssentials.Utility.CurrencyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.Objects;

import static org.bukkit.util.NumberConversions.round;

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
                player.sendMessage(ChatColor.RED + "Usage: /job set [Job Name].");
                return true;
            }

            String action = args[0].toLowerCase();

            switch (action) {

                case "quit":
                    String quit = args[0];
                    player.sendMessage(ChatColor.RED + "Job Manager: " + "You have quit your job.");
                    JobManager.Job = quit.toLowerCase();
                    break;

                case "set":
                    if (args.length < 2) {
                        player.sendMessage("Usage: /job set [Job Name].");
                        return true;
                    }
                    String value = args[1];


                   if (Objects.equals(value, "fisher")) {
                        player.sendMessage(ChatColor.RED + "Job Manager: " + ChatColor.GREEN + "You are now a fisher.");
                        JobManager.Job = value.toLowerCase();
                        break;
                   }

                   else {
                       player.sendMessage(ChatColor.RED + "Currently fisher job or quit are the only ones coded. /job set fisher. /job quit");
                       break;
                   }


                case "bank":
                    if (args.length < 1) {
                        player.sendMessage("Balance: " + ChatColor.GREEN + CurrencyManager.eco.getBalance(player));
                        break;
                    }

                default:
                    player.sendMessage(ChatColor.RED + "Unknown action: " + action + ". Use 'add' or 'remove'.");
            }

            return true;
        }
    }

