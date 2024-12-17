package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class CurrencyManager {

    public static Economy eco = HazelsEssentials.getEconomy();
    static Random rand = new Random();
    static double increase;

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

    public static double rewardMultiplier(int level) {
        double[] thresholds = {5, 15};
        ArrayList<Double> cashIncrease = new ArrayList<>();
        cashIncrease.add(1.0);
        cashIncrease.add(2.0);
        float temp = rand.nextFloat();

        for (level = 0; level < thresholds.length; level++){
            if (temp < thresholds[level]){
                increase = cashIncrease.get(level);
                break;
            }
        }
        return increase;
    }
}