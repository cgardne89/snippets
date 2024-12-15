package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class CurrencyManager {

    public static Economy eco = HazelsEssentials.getEconomy();
    static Random rand = new Random();
    static double increase;

    public CurrencyManager() {
        initialize();
    }



    public void initialize() {

    }

    public static boolean getBalanceCap(Player player){

        File playerFile = ConfigHandler.getPlayerFile(player);
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);


        double maxBank = Double.parseDouble(playerData.getString(DataConstants.MAXBANK_KEY));
        double bankBalance = eco.getBalance(player);
        return bankBalance >= maxBank;
    }

    public static void getCashAmount(Player player){
        double[] levels = {1, 5, 10, 15};
        ArrayList<Double> cashIncrease = new ArrayList<>();
        cashIncrease.add(1.0);
        cashIncrease.add(2.0);
        cashIncrease.add(3.0);
        cashIncrease.add(5.0);
        float temp = rand.nextFloat();

        for (int i = 0; i < levels.length; i++){
            if (temp < levels[i]){
                increase = cashIncrease.get(i);
                break;
            }
        }
    }
}