package com.xurses.hazelsEssentials.Utility;

import java.util.ArrayList;
import java.util.List;

public class DataConstants {

    public static final String NAME_KEY = "name";
    public static final String UUID_KEY = "UUID";
    public static List<String> MAXBANK_KEY = new ArrayList<>();
    public static List<String> MAXSTATLEVEL_KEY = new ArrayList<>();
    public DataConstants() {

        MAXBANK_KEY.add("Max_Bank");
        MAXBANK_KEY.add("0.01");

        MAXSTATLEVEL_KEY.add("Stats.Max_Level");
        MAXSTATLEVEL_KEY.add("15");
    }

}
