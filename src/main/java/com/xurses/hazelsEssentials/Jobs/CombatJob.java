package com.xurses.hazelsEssentials.Jobs;

import com.xurses.hazelsEssentials.Utility.CurrencyManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CombatJob {
    public static int baseXP = 0;
    public static int level = 1;



    public static boolean isInCombat(Player player) {

        if (!CurrencyManager.balanceCap(player) && Objects.equals(JobManager.Job, "combatant")) {
            return true;
        }
        return false;
    }

    public static void payForWork(Player player) {
        CurrencyManager.payPlayer(level, player);
    }
}
