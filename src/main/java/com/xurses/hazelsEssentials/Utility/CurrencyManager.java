package com.xurses.hazelsEssentials.Utility;



import com.xurses.hazelsEssentials.*;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;


public class CurrencyManager {

    public static Economy eco = HazelsEssentials.getEconomy();
    static int increase;
    static int baseMoney = 1;

    public CurrencyManager() {
        initialize();
    }



    public void initialize() {

    }

    public static boolean balanceCap(Player player) {
        double maxBank = Double.parseDouble(DataConstants.MAXBANK_KEY.get(1));
        double bankBalance = eco.getBalance(player);
        return bankBalance >= maxBank;

    }

    public static int rewardMultiplier(int level) {
        int[] thresholds = {5, 15};
        ArrayList<Integer> cashIncrease = new ArrayList<>();
        cashIncrease.add(1);
        cashIncrease.add(2);
        cashIncrease.add(3);

        for (int i = 0; i < thresholds.length; i++){
            if (level < thresholds[i]){
                increase = cashIncrease.get(i);
                break;
            }
        }
        return increase * baseMoney;
    }

    public static double payPlayer(int level, Player player) {
        double getReward = rewardMultiplier(level);
        CurrencyManager.eco.depositPlayer(player, getReward/ 100);
        return getReward;
    }

}