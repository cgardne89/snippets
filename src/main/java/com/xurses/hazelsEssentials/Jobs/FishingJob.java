package com.xurses.hazelsEssentials.Jobs;

import com.xurses.hazelsEssentials.Utility.CurrencyManager;
import com.xurses.hazelsEssentials.Utility.LevelCalculator;
import org.bukkit.entity.Player;


public class FishingJob {
    public static int baseXP = 0;
    public static int level = 1;


    public static boolean isFishing(Player player) {
        if (!CurrencyManager.balanceCap(player) && JobManager.Job.equals("fisher")) {

            return true;
        }
        return false;
    }

    public static void payForWork(Player player) {
        baseXP += 1;
        level = LevelCalculator.calculateLevel(level, player);
        CurrencyManager.payPlayer(level, player);
    }

}
