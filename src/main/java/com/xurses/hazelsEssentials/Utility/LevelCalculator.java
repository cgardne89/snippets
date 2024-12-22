package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.Jobs.FishingJob;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LevelCalculator {
    public static int XPneeded = 0;
    public static int level1XP = 20;

    public LevelCalculator(){
    }



    public static int calculateLevel(int level, Player player) {
        XPneeded = level * level1XP;
        while (FishingJob.baseXP >= XPneeded) {
                level += 1;
                FishingJob.baseXP = 0;
                player.sendMessage("Congrats you are now level " + level);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                player.playEffect(EntityEffect.FIREWORK_EXPLODE);
        }
        return level;
    }
}

