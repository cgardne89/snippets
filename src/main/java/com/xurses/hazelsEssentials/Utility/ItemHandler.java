package com.xurses.hazelsEssentials.Utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ItemHandler {



    ArrayList<Enchantment> enchantedExtras = ArrayLists.enchantedExtras;

    ArrayList<Enchantment> enchantedPositives = ArrayLists.enchantedPositives;

    ArrayList<String> randLoreWeapon = ArrayLists.randLoreWeapon;
    ArrayList<Enchantment> enchantedWeapon = ArrayLists.enchantedWeapon;

    ArrayList<String> randLoreTool = ArrayLists.randLoreTool;
    ArrayList<Enchantment> enchantedTool = ArrayLists.enchantedTool;

    ArrayList<String> randLoreArmour = ArrayLists.randLoreArmour;
    ArrayList<Enchantment> enchantedArmour = ArrayLists.enchantedArmour;

    public boolean isWeapon(ItemStack item) {
        Material material = item.getType();
        return material.toString().endsWith("_SWORD") ||
                material.toString().endsWith("TRIDENT") ||
                material.toString().endsWith("MACE");
    }

    public boolean isTool(ItemStack item) {
        Material material = item.getType();
        return material.toString().endsWith("AXE") ||
                material.toString().endsWith("HOE") ||
                material.toString().endsWith("PICKAXE") ||
                material.toString().endsWith("SHOVEL");
    }

    public boolean isArmour(ItemStack item) {
        Material material = item.getType();
        return material.toString().endsWith("_HELMET") ||
                material.toString().endsWith("_CHESTPLATE") ||
                material.toString().endsWith("_LEGGINGS") ||
                material.toString().endsWith("_BOOTS");
    }



    public ItemStack createCustomItem(ChatColor itemColor, String oldUser, String lootRarityType, Material mat) {
        ItemStack customItem = new ItemStack(mat);
        ItemMeta meta = customItem.getItemMeta();

        if (meta != null) {
            Random rand = new Random();
            meta.setDisplayName(itemColor + oldUser + lootRarityType + mat.toString().toLowerCase().replace("_", " "));

            if (isArmour(customItem)) {

                meta.setLore(List.of(itemColor + randLoreArmour.get(rand.nextInt(randLoreArmour.size()))));


                for (int i = 0; i <= rand.nextInt(1,2); i++) {
                    Random ranEncha = new Random();
                    Enchantment enchantment = enchantedArmour.get(ranEncha.nextInt(enchantedArmour.size()));
                    int minLevel = 1; // Minimum level is always 1 for most enchantments
                    int maxLevel = enchantment.getMaxLevel();
                    int enchantLevel = Math.min(rand.nextInt(maxLevel - minLevel + 1) + minLevel, maxLevel);

                    meta.addEnchant(enchantment, enchantLevel, true);

                }
            } else if (isWeapon(customItem)){
                // Set lore for armor
                meta.setLore(List.of(itemColor + randLoreWeapon.get(rand.nextInt(randLoreWeapon.size()))));


                for (int i = 0; i <= rand.nextInt(1,2); i++) { //
                    Random ranEncha = new Random();
                    Enchantment enchantment = enchantedWeapon.get(ranEncha.nextInt(enchantedWeapon.size()));
                    int minLevel = 1; // Minimum level is always 1 for most enchantments
                    int maxLevel = enchantment.getMaxLevel();
                    int enchantLevel = Math.min(rand.nextInt(maxLevel - minLevel + 1) + minLevel, maxLevel);

                    meta.addEnchant(enchantment, enchantLevel, true);

                }
            } else if (isTool(customItem)){

                meta.setLore(List.of(itemColor + randLoreTool.get(rand.nextInt(randLoreTool.size()))));


                for (int i = 0; i <= rand.nextInt(1,2); i++) {
                    Random ranEncha = new Random();
                    Enchantment enchantment = enchantedTool.get(ranEncha.nextInt(enchantedTool.size()));
                    int minLevel = 1; // Minimum level is always 1 for most enchantments
                    int maxLevel = enchantment.getMaxLevel();
                    int enchantLevel = Math.min(rand.nextInt(maxLevel - minLevel + 1) + minLevel, maxLevel);

                    meta.addEnchant(enchantment, enchantLevel, true);

                }
            }

            System.out.println(CustomLootTables.lootRarityType);
            if (CustomLootTables.lootRarityType.trim().equals("Uncommon")) {
                int unbreakingLevel = Math.min(rand.nextInt(enchantedPositives.get(0).getMaxLevel()) + 1,
                        enchantedPositives.get(0).getMaxLevel());
                meta.addEnchant(enchantedPositives.get(0), unbreakingLevel, true);
            }

            if (CustomLootTables.lootRarityType.trim().equals("Rare") || CustomLootTables.lootRarityType.trim().equals("Epic")) {
                int mendingLevel = Math.min(rand.nextInt(enchantedPositives.get(1).getMaxLevel()) + 1,
                        enchantedPositives.get(1).getMaxLevel());
                meta.addEnchant(enchantedPositives.get(1), mendingLevel, true);
                int unbreakingLevel = Math.min(rand.nextInt(enchantedPositives.get(0).getMaxLevel()) + 1,
                        enchantedPositives.get(0).getMaxLevel());
                meta.addEnchant(enchantedPositives.get(0), unbreakingLevel, true);
            }

            if (CustomLootTables.lootRarityType.trim().equals("Legendary")) {
                // Add higher-level enchantments or additional ones for legendary items
                int unbreakingLevel = Math.min(rand.nextInt(enchantedPositives.get(0).getMaxLevel()) + 1,
                        enchantedPositives.get(0).getMaxLevel());
                meta.addEnchant(enchantedPositives.get(0), unbreakingLevel, true);

                int mendingLevel = Math.min(rand.nextInt(enchantedPositives.get(1).getMaxLevel()) + 1,
                        enchantedPositives.get(1).getMaxLevel());
                meta.addEnchant(enchantedPositives.get(1), mendingLevel, true);

                // Add a custom enchantment for Legendary
                Enchantment extraEnchantment = enchantedExtras.get(rand.nextInt(enchantedExtras.size())); // Assuming a third enchantment exists
                int extraLevel = Math.min(rand.nextInt(extraEnchantment.getMaxLevel()) + 1,
                        extraEnchantment.getMaxLevel());
                meta.addEnchant(extraEnchantment, extraLevel, true);
            }

            customItem.setItemMeta(meta);
        }
        return customItem;
    }

}
