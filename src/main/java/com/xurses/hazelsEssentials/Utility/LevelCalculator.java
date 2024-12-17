package com.xurses.hazelsEssentials.Utility;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LevelCalculator {
    public static int XPneeded = 0;
    public static int level1XP = 20;

    public LevelCalculator(){
    }

    public static void calculateLevel(int level, int baseXP) {
        XPneeded = level * level1XP;

        for (int i = 0; i >= XPneeded; i++){
            if (baseXP >= XPneeded) {
                level += 1;
                baseXP = 0;
            }
        }
    }
}

