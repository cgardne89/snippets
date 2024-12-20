package com.xurses.hazelsEssentials.Utility;



import com.xurses.hazelsEssentials.*;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.round;

public class CurrencyManager {

    public static Economy eco = HazelsEssentials.getEconomy();
    static Random rand = new Random();
    static double increase;
    static double Current_Balance;

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

    public static double rewardMultiplier(int level, Player player) {
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

    public static void payPlayer(int level, Player player, String Job_Name) {

        CurrencyManager.eco.depositPlayer(player, 0.01 * rewardMultiplier(level, player));
        CurrencyManager.getCurrentBalance(player);
        player.sendMessage(ChatColor.RED + Job_Name + ": " + ChatColor.GOLD + "Added " + 0.01 * rewardMultiplier(level, player) + "$.");
    }

    public static void getCurrentBalance(Player player) {
        Current_Balance = round(eco.getBalance(player) * (100) / 100);
    }
}