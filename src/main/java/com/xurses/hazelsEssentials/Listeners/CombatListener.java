package com.xurses.hazelsEssentials.Listeners;

import com.xurses.hazelsEssentials.Jobs.CombatJob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class CombatListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        Player player = e.getEntity().getKiller();

        if (CombatJob.isInCombat(player)){
            CombatJob.payForWork(player);
        }

    }
}
