package com.xurses.hazelsEssentials.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;

public class CookingListener implements Listener {
    /*NamespacedKey key = new NamespacedKey(HazelsEssentials.getProvidingPlugin(HazelsEssentials.class), "IronOnFire");
    ItemStack cookME = new ItemStack(Material.IRON_BARS, 1);
    CampfireRecipe cookIron = new CampfireRecipe(key, cookME, Material.RAW_IRON, 0.01F, 1);*/



    public CookingListener(){
        //getServer().addRecipe(cookIron);
    }

    @EventHandler
    public void onCooking(FurnaceBurnEvent e){

    }

}
