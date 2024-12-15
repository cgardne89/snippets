package com.xurses.hazelsEssentials.Listeners;

import com.xurses.hazelsEssentials.HazelsEssentials;
import org.bukkit.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;


import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class CookingListener implements Listener {
    /*NamespacedKey key = new NamespacedKey(HazelsEssentials.getProvidingPlugin(HazelsEssentials.class), "Custom_Chicken");
    NamespacedKey key2 = new NamespacedKey(HazelsEssentials.getProvidingPlugin(HazelsEssentials.class), "asdasd");
    ItemStack horseStack = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
    ShapelessRecipe horseArmour = new ShapelessRecipe(key2, horseStack);



    FurnaceRecipe diamondBlock = new FurnaceRecipe(key, new ItemStack(Material.DIAMOND_BLOCK, 1), Material.COAL_BLOCK, 1, 30000);*/


    /*public CookingListener(){
        horseArmour.addIngredient(Material.COAL);
        horseArmour.addIngredient(Material.STRING);
        getServer().addRecipe(horseArmour);
        getServer().addRecipe(diamondBlock);
    }


    @EventHandler
    public void onStartCooking(FurnaceStartSmeltEvent e){

        Material mat = e.getSource().getType();
        String recipeCat = e.getRecipe().getResult().toString();
        int cookTime = e.getTotalCookTime();
        ItemStack copper = new ItemStack(Material.COPPER_INGOT, 1);

        getLogger().info("Mat: " + mat + ". Recipe: " + recipeCat + ". Time: " + cookTime + " My Copper: " + copper);

    }
    @EventHandler
    public void onCooking(FurnaceBurnEvent e){
    }*/


}
