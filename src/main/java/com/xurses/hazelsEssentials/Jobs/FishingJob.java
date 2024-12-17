package com.xurses.hazelsEssentials.Jobs;

import com.xurses.hazelsEssentials.Utility.CurrencyManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FishingJob {
    public static int baseXP = 0;
    public static int level = 1;
    static double multiplier = CurrencyManager.rewardMultiplier(level);


    public static boolean isFishing(Player player) {

        if (!CurrencyManager.balanceCap(player) && Objects.equals(JobManager.Job, "fisher")) {
            return true;
        }
        return false;
    }

    public static void payForWork(Player player) {
        CurrencyManager.eco.depositPlayer(player, 0.01 * multiplier);
        player.sendMessage(ChatColor.RED + "Fishing Job: " + ChatColor.GOLD + "Added " + 0.01 * multiplier + "$.");
    }
}
