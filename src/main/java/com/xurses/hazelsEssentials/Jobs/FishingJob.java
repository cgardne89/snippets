package com.xurses.hazelsEssentials.Jobs;

import com.xurses.hazelsEssentials.HazelsEssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class FishingJob {

    double fishXP;
    boolean isFisherMan;
    double nextLevel;
    double currentLevel;
    double levelMultiplier;
    double XPNeeded;



    public void fisherMan(Player player, double reward){

        if (isFisherMan) {
            XPNeeded = currentLevel + nextLevel * levelMultiplier / 0.02;
            fishXP = 0.01;
            Economy eco = HazelsEssentials.getEconomy();
            eco.depositPlayer(player, reward);
        }
    }
}
