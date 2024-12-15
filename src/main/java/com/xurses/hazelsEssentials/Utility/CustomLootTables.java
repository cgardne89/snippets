package com.xurses.hazelsEssentials.Utility;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.*;

public class CustomLootTables {
    ItemHandler itemHandler = new ItemHandler();
    ArrayList<Material> rarity = new ArrayList<>();
    Random rand = new Random();
    public static String lootRarityType;
    ChatColor qualityColor;


    public void generateCustomLoot(LivingEntity mob, Player player){
        Location location = mob.getLocation();
        LootContext context = new LootContext.Builder(location)
                .killer(player)
                .build();
        Random rand = new Random();
        LootTable customLootTable = createCustomLootTable();
        Collection<ItemStack> loot = customLootTable.populateLoot(rand, context);

        loot.forEach(item -> Objects.requireNonNull(location.getWorld()).dropItem(location, item));
    }

    public ArrayList<Material> determineRarity() {
        float temp = rand.nextFloat();
        System.out.println("Random value for rarity: " + temp);

        // Define rarity thresholds and corresponding data
        double[] thresholds = {0.5, 0.6, 0.7, 0.8, 0.85, 0.9};
        List<List<Material>> drops = Arrays.asList(
                ArrayLists.woodenDrops, ArrayLists.stoneDrops, ArrayLists.chainmailDrops,
                ArrayLists.goldDrops, ArrayLists.diamondDrops, ArrayLists.netheriteDrops
        );
        List<String> qualityNames = ArrayLists.qualityName;
        List<ChatColor> qualityColors = ArrayLists.itemQuality;

        // Iterate through thresholds to find the correct rarity
        for (int i = 0; i < thresholds.length; i++) {
            if (temp < thresholds[i]) {
                rarity = new ArrayList<>(drops.get(i));
                lootRarityType = qualityNames.get(i);
                qualityColor = qualityColors.get(i);
                System.out.println("Selected rarity: " + lootRarityType + " with items: " + rarity);
                return rarity;
            }
        }

        // Default to wooden drops if no match
        rarity = new ArrayList<>(ArrayLists.woodenDrops);
        lootRarityType = qualityNames.get(0);
        qualityColor = qualityColors.get(0);
        System.out.println("Defaulted to wooden drops.");
        return rarity;
    }

    public LootTable createCustomLootTable() {
        return new LootTable() {
            @Override
            public NamespacedKey getKey() {
                return null;
            }

            @Override
            public Collection<ItemStack> populateLoot (java.util.Random random, LootContext Context) {
                List<ItemStack> loot = new ArrayList<>();
                rarity = new ArrayList<>(determineRarity());
                int i = random.nextInt(0, rarity.size());
                ItemStack createItem = itemHandler.createCustomItem(qualityColor, BossHandler.monsterCustomName + "'s", lootRarityType, rarity.get(i));
                loot.add(new ItemStack(createItem));
                return loot;
            }

            @Override
            public void fillInventory(Inventory inventory, Random random, LootContext lootContext) {

            }
        };
    }
}
