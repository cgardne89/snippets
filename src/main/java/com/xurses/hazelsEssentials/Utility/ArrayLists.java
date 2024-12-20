package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ArrayLists implements Listener {

    public ArrayLists(){
        initializeArrays();
        saveAllArraysToConfig();
    }


    public static ArrayList<EntityType>
            aquaBosses = new ArrayList<>(),
            landBosses = new ArrayList<>(),
            airBosses = new ArrayList<>();

    public static ArrayList<Material>
            normalDrops = new ArrayList<>(),
            woodenDrops = new ArrayList<>(),
            stoneDrops = new ArrayList<>(),
            chainmailDrops = new ArrayList<>(),
            ironDrops = new ArrayList<>(),
            goldDrops = new ArrayList<>(),
            diamondDrops = new ArrayList<>(),
            netheriteDrops = new ArrayList<>(),
            ores = new ArrayList<>(),
            crops = new ArrayList<>(),
            logTypes = new ArrayList<>(),
            seeds = new ArrayList<>();

    public static ArrayList<String>
            names = new ArrayList<>(),
            qualityName = new ArrayList<>(),
            randLoreWeapon = new ArrayList<>(),
            randLoreTool = new ArrayList<>(),
            randLoreArmour = new ArrayList<>(),
            rareFishingDrops = new ArrayList<>();

    public static ArrayList<ChatColor>
            itemQuality = new ArrayList<>();

    public static ArrayList<Enchantment>
            enchantedWeapon = new ArrayList<>(),
            enchantedTool = new ArrayList<>(),
            enchantedArmour = new ArrayList<>(),
            enchantedBoots = new ArrayList<>(),
            enchantedNegative = new ArrayList<>(),
            enchantedPositives = new ArrayList<>(),
            enchantedExtras = new ArrayList<>();



    public void initializeArrays()
    {
        //Air Bosses
        airBosses.add(EntityType.BLAZE);
        airBosses.add(EntityType.PHANTOM);

        //Aqua Bosses
        aquaBosses.add(EntityType.DROWNED);

        //Land Bosses
        landBosses.add(EntityType.ZOMBIE);

        //ores
        ores.add(Material.COAL_ORE);
        ores.add(Material.COPPER_ORE);
        ores.add(Material.IRON_ORE);
        ores.add(Material.GOLD_ORE);
        ores.add(Material.REDSTONE_ORE);
        ores.add(Material.LAPIS_ORE);
        ores.add(Material.EMERALD_ORE);
        ores.add(Material.DIAMOND_ORE);
        ores.add(Material.NETHER_GOLD_ORE);
        ores.add(Material.NETHER_QUARTZ_ORE);
        ores.add(Material.DEEPSLATE_COAL_ORE);
        ores.add(Material.DEEPSLATE_COPPER_ORE);
        ores.add(Material.DEEPSLATE_IRON_ORE);
        ores.add(Material.DEEPSLATE_GOLD_ORE);
        ores.add(Material.DEEPSLATE_REDSTONE_ORE);
        ores.add(Material.DEEPSLATE_LAPIS_ORE);
        ores.add(Material.DEEPSLATE_EMERALD_ORE);
        ores.add(Material.DEEPSLATE_DIAMOND_ORE);
        //add Deep slate ores

        //Seeds
        seeds.add(Material.BEETROOT_SEEDS);
        seeds.add(Material.MELON_SEEDS);
        seeds.add(Material.PUMPKIN_SEEDS);
        seeds.add(Material.WHEAT_SEEDS);
        seeds.add(Material.TORCHFLOWER_SEEDS);
        seeds.add(Material.CARROT);
        seeds.add(Material.POTATO);
        seeds.add(Material.NETHER_WART); // Plantable in the Nether
        seeds.add(Material.COCOA_BEANS); // Plantable on jungle wood
        seeds.add(Material.BAMBOO); // Grows into bamboo plants
        seeds.add(Material.SUGAR_CANE); // Grows into sugar cane plants
        seeds.add(Material.KELP); // Grows underwater
        seeds.add(Material.SWEET_BERRIES); // Grows into sweet berry bushes
        seeds.add(Material.GLOW_BERRIES); // Can be planted to grow on cave vines
        seeds.add(Material.CHORUS_FLOWER); // Grows into chorus plants
        seeds.add(Material.PITCHER_POD); // Grows into Pitcher Crop


        //Farming Materials
        crops.add(Material.WHEAT);
        crops.add(Material.BEETROOTS);
        crops.add(Material.CARROT);
        crops.add(Material.POTATO);
        crops.add(Material.COCOA_BEANS);
        crops.add(Material.NETHER_WART);
        crops.add(Material.PUMPKIN);
        crops.add(Material.SUGAR_CANE);
        crops.add(Material.CACTUS);
        crops.add(Material.BROWN_MUSHROOM);
        crops.add(Material.RED_MUSHROOM);
        crops.add(Material.MELON);
        crops.add(Material.CHORUS_FRUIT);
        crops.add(Material.PITCHER_CROP);
        crops.add(Material.BAMBOO);
        crops.add(Material.KELP);
        crops.add(Material.SWEET_BERRIES);
        crops.add(Material.GLOW_BERRIES);
        crops.add(Material.TORCHFLOWER_CROP);
        crops.add(Material.SEA_PICKLE);

        //logTypes

        // Add Overworld log types
        logTypes.add(Material.OAK_LOG);
        logTypes.add(Material.SPRUCE_LOG);
        logTypes.add(Material.BIRCH_LOG);
        logTypes.add(Material.JUNGLE_LOG);
        logTypes.add(Material.ACACIA_LOG);
        logTypes.add(Material.DARK_OAK_LOG);
        logTypes.add(Material.MANGROVE_LOG);
        logTypes.add(Material.CHERRY_LOG);

        // Add Nether log-like types
        logTypes.add(Material.CRIMSON_STEM);
        logTypes.add(Material.WARPED_STEM);

        // Add the new Pale Oak log type
        logTypes.add(Material.PALE_OAK_LOG);

        // Names
        names.add("Vargas's");
        names.add("Azaan's");
        names.add("Hamish's");
        names.add("Tommy's");
        names.add("Nora's");
        names.add("Ida's");
        names.add("Mohamed's");
        names.add("Cassie's");
        names.add("Khadijah's");
        names.add("Kristian's");
        names.add("Elena's");
        names.add("Samir's");
        names.add("Amara's");
        names.add("Dario's");
        names.add("Priya's");
        names.add("Leon's");
        names.add("Isla's");
        names.add("Mateo's");
        names.add("Lucy's");
        names.add("Ravi's");
        names.add("Aria's");
        names.add("Finn's");
        names.add("Zara's");
        names.add("Caleb's");
        names.add("Lila's");
        names.add("Eli's");
        names.add("Talia's");
        names.add("Noah's");
        names.add("Amina's");
        names.add("Luca's");
        names.add("Sophia's");
        names.add("Liam's");
        names.add("Maya's");
        names.add("Ethan's");
        names.add("Layla's");
        names.add("Logan's");
        names.add("Hana's");
        names.add("Oliver's");
        names.add("Meera's");
        names.add("Aiden's");
        names.add("Farah's");
        names.add("Carter's");
        names.add("Alina's");
        names.add("Mason's");
        names.add("Sana's");
        names.add("James's");
        names.add("Ivy's");
        names.add("Jacob's");
        names.add("Ella's");
        names.add("William's");
        names.add("Olivia's");
        names.add("Lucas's");
        names.add("Emma's");
        names.add("Alexander's");
        names.add("Grace's");
        names.add("Benjamin's");
        names.add("Ava's");
        names.add("Henry's");
        names.add("Chloe's");
        names.add("Daniel's");
        names.add("Sophie's");
        names.add("Jackson's");
        names.add("Harper's");
        names.add("Samuel's");
        names.add("Zoey's");
        names.add("Joshua's");
        names.add("Aubrey's");
        names.add("Nathan's");
        names.add("Camila's");
        names.add("Elias's");
        names.add("Victoria's");
        names.add("Sebastian's");
        names.add("Isabella's");
        names.add("Gabriel's");
        names.add("Ella's");
        names.add("Aaron's");
        names.add("Penelope's");
        names.add("Julian's");
        names.add("Aurora's");
        names.add("Owen's");
        names.add("Violet's");
        names.add("Luke's");
        names.add("Hazel's");
        names.add("Calvin's");
        names.add("Madeline's");
        names.add("Miles's");
        names.add("Juliette's");
        names.add("Asher's");
        names.add("Scarlett's");
        names.add("Grayson's");
        names.add("Lydia's");
        names.add("Connor's");
        names.add("Charlotte's");
        names.add("Jayden's");
        names.add("Hannah's");
        names.add("Isaiah's");
        names.add("Eleanor's");


        // rareDrops
        rareFishingDrops.add("Saddle");
        rareFishingDrops.add("Ink Sac");
        rareFishingDrops.add("Fishing Rod");
        rareFishingDrops.add("Stick");
        rareFishingDrops.add("String");
        rareFishingDrops.add("Enchanted Book");
        rareFishingDrops.add("Nautilus Shell");

        //woodenDrops
        woodenDrops.add(Material.OAK_PLANKS);
        woodenDrops.add(Material.WOODEN_AXE);
        woodenDrops.add(Material.WOODEN_HOE);
        woodenDrops.add(Material.WOODEN_PICKAXE);
        woodenDrops.add(Material.WOODEN_SHOVEL);
        woodenDrops.add(Material.WOODEN_SWORD);


        //stoneDrops
        stoneDrops.add(Material.COBBLESTONE);
        stoneDrops.add(Material.STONE_AXE);
        stoneDrops.add(Material.STONE_HOE);
        stoneDrops.add(Material.STONE_PICKAXE);
        stoneDrops.add(Material.STONE_SHOVEL);
        stoneDrops.add(Material.STONE_SWORD);

        //chainmailDrops
        chainmailDrops.add(Material.CHAINMAIL_BOOTS);
        chainmailDrops.add(Material.CHAINMAIL_HELMET);
        chainmailDrops.add(Material.CHAINMAIL_CHESTPLATE);
        chainmailDrops.add(Material.CHAINMAIL_LEGGINGS);


        //ironDrops
        ironDrops.add(Material.IRON_BLOCK);
        ironDrops.add(Material.IRON_AXE);
        ironDrops.add(Material.IRON_HOE);
        ironDrops.add(Material.IRON_PICKAXE);
        ironDrops.add(Material.IRON_SHOVEL);
        ironDrops.add(Material.IRON_SWORD);
        ironDrops.add(Material.IRON_BOOTS);
        ironDrops.add(Material.IRON_HELMET);
        ironDrops.add(Material.IRON_CHESTPLATE);
        ironDrops.add(Material.IRON_LEGGINGS);

        // goldDrops
        goldDrops.add(Material.GOLD_BLOCK);
        goldDrops.add(Material.GOLDEN_AXE);
        goldDrops.add(Material.GOLDEN_HOE);
        goldDrops.add(Material.GOLDEN_PICKAXE);
        goldDrops.add(Material.GOLDEN_SHOVEL);
        goldDrops.add(Material.GOLDEN_SWORD);
        goldDrops.add(Material.GOLDEN_BOOTS);
        goldDrops.add(Material.GOLDEN_HELMET);
        goldDrops.add(Material.GOLDEN_CHESTPLATE);
        goldDrops.add(Material.GOLDEN_LEGGINGS);


        // diamondDrops
        diamondDrops.add(Material.DIAMOND_BLOCK);
        diamondDrops.add(Material.DIAMOND_AXE);
        diamondDrops.add(Material.DIAMOND_HOE);
        diamondDrops.add(Material.DIAMOND_PICKAXE);
        diamondDrops.add(Material.DIAMOND_SHOVEL);
        diamondDrops.add(Material.DIAMOND_SWORD);
        diamondDrops.add(Material.DIAMOND_BOOTS);
        diamondDrops.add(Material.DIAMOND_HELMET);
        diamondDrops.add(Material.DIAMOND_CHESTPLATE);
        diamondDrops.add(Material.DIAMOND_LEGGINGS);

        //netheriteDrops
        netheriteDrops.add(Material.NETHERITE_BLOCK);
        netheriteDrops.add(Material.NETHERITE_AXE);
        netheriteDrops.add(Material.NETHERITE_HOE);
        netheriteDrops.add(Material.NETHERITE_PICKAXE);
        netheriteDrops.add(Material.NETHERITE_SHOVEL);
        netheriteDrops.add(Material.NETHERITE_SWORD);
        netheriteDrops.add(Material.NETHERITE_BOOTS);
        netheriteDrops.add(Material.NETHERITE_HELMET);
        netheriteDrops.add(Material.NETHERITE_CHESTPLATE);
        netheriteDrops.add(Material.NETHERITE_LEGGINGS);


        //QualityNames
        qualityName.add(" Junk ");
        qualityName.add(" Common ");
        qualityName.add(" Uncommon ");
        qualityName.add(" Rare ");
        qualityName.add(" Epic ");
        qualityName.add(" Legendary ");

        //colors
        itemQuality.add(ChatColor.WHITE);
        itemQuality.add(ChatColor.GRAY);
        itemQuality.add(ChatColor.GREEN);
        itemQuality.add(ChatColor.BLUE);
        itemQuality.add(ChatColor.DARK_PURPLE);
        itemQuality.add(ChatColor.YELLOW);

        //Weapons
        randLoreWeapon.add("Drips with the venom of a thousand snakes");
        randLoreWeapon.add("Its blade sings with every swing");
        randLoreWeapon.add("Forged to slay the unholy");
        randLoreWeapon.add("A weapon that turns the tide of battle");
        randLoreWeapon.add("Carved from the fang of an ancient beast");
        randLoreWeapon.add("Holds the fury of a raging storm");
        randLoreWeapon.add("A blade that drinks the light");
        randLoreWeapon.add("Crafted for the chosen warrior");
        randLoreWeapon.add("Its strikes echo across the battlefield");
        randLoreWeapon.add("Legends say it cannot be stopped");

        //tools
        randLoreTool.add("Cuts through materials like butter");
        randLoreTool.add("The lost artifact of an ancient craftsman");
        randLoreTool.add("Bound with unbreakable magic");
        randLoreTool.add("A tool that reveals hidden treasures");
        randLoreTool.add("Sharper than the keenest mind");
        randLoreTool.add("Once used to carve the first mountain");
        randLoreTool.add("A tool blessed by the earth itself");
        randLoreTool.add("Never tires, even in the deepest depths");
        randLoreTool.add("The key to endless resources");
        randLoreTool.add("Each strike resounds with precision");

        //Armour
        randLoreArmour.add("Woven with threads of unyielding materials");
        randLoreArmour.add("Protects the wearer from unseen forces");
        randLoreArmour.add("Gleams with celestial light");
        randLoreArmour.add("Defence against the elements");
        randLoreArmour.add("Imbued with the strength of ten men");
        randLoreArmour.add("Legends say it deflects any blow");
        randLoreArmour.add("Forged in the core of a dying star");
        randLoreArmour.add("Defence that is said can block all");
        randLoreArmour.add("The armour piece of an eternal guardian");
        randLoreArmour.add("It leaves no trace on the wearer");

        //Weapons
        enchantedWeapon.add(Enchantment.SHARPNESS);
        enchantedWeapon.add(Enchantment.FIRE_ASPECT);
        enchantedWeapon.add(Enchantment.SMITE);
        enchantedWeapon.add(Enchantment.BANE_OF_ARTHROPODS);
        enchantedWeapon.add(Enchantment.KNOCKBACK);
        enchantedWeapon.add(Enchantment.LOOTING);
        enchantedWeapon.add(Enchantment.SWEEPING_EDGE);

        //Tools
        enchantedTool.add(Enchantment.EFFICIENCY);
        enchantedTool.add(Enchantment.SILK_TOUCH);
        enchantedTool.add(Enchantment.FORTUNE);

        //Armour
        enchantedArmour.add(Enchantment.PROTECTION);
        enchantedArmour.add(Enchantment.FIRE_PROTECTION);
        enchantedArmour.add(Enchantment.BLAST_PROTECTION);
        enchantedArmour.add(Enchantment.PROJECTILE_PROTECTION);
        enchantedArmour.add(Enchantment.THORNS);

        //Boots
        enchantedBoots.add(Enchantment.DEPTH_STRIDER);
        enchantedBoots.add(Enchantment.FROST_WALKER);
        enchantedBoots.add(Enchantment.RESPIRATION);
        enchantedBoots.add(Enchantment.AQUA_AFFINITY);
        enchantedBoots.add(Enchantment.FEATHER_FALLING);
        enchantedBoots.add(Enchantment.SOUL_SPEED);

        //Negatives
        enchantedNegative.add(Enchantment.BINDING_CURSE);
        enchantedNegative.add(Enchantment.VANISHING_CURSE);

        //Positives
        enchantedPositives.add(Enchantment.UNBREAKING);
        enchantedPositives.add(Enchantment.MENDING);

        //Extras
        enchantedExtras.add(Enchantment.UNBREAKING);
        enchantedExtras.add(Enchantment.MENDING);
        enchantedExtras.add(Enchantment.LURE); // For fishing rods
        enchantedExtras.add(Enchantment.LUCK_OF_THE_SEA); // For fishing rods
        enchantedExtras.add(Enchantment.RIPTIDE); // For tridents
        enchantedExtras.add(Enchantment.IMPALING); // For tridents
        enchantedExtras.add(Enchantment.CHANNELING); // For tridents
        enchantedExtras.add(Enchantment.MULTISHOT); // For crossbows
        enchantedExtras.add(Enchantment.PIERCING); // For crossbows
        enchantedExtras.add(Enchantment.QUICK_CHARGE); // For crossbows
    }

    // This method will save all ArrayLists to the config file
    private void saveAllArraysToConfig() {
        // Convert non-string arrays to string arrays before saving
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "aquaBosses", convertEntityTypeListToString(aquaBosses));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "landBosses", convertEntityTypeListToString(landBosses));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "airBosses", convertEntityTypeListToString(airBosses));

        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "seeds", convertMaterialListToString(seeds));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "logTypes", convertMaterialListToString(logTypes));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "normalDrops", convertMaterialListToString(normalDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "itemQuality", convertChatColorListToString(itemQuality));

        // String lists (no conversion needed)
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "names", names);
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "qualityName", qualityName);
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "randLoreWeapon", randLoreWeapon);
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "randLoreTool", randLoreTool);
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "randLoreArmour", randLoreArmour);

        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "rareFishingDrops", rareFishingDrops);
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "woodenDrops", convertMaterialListToString(woodenDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "stoneDrops", convertMaterialListToString(stoneDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "chainmailDrops", convertMaterialListToString(chainmailDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "ironDrops", convertMaterialListToString(ironDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "goldDrops", convertMaterialListToString(goldDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "diamondDrops", convertMaterialListToString(diamondDrops));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "netheriteDrops", convertMaterialListToString(netheriteDrops));

        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "ores", convertMaterialListToString(ores));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "crops", convertMaterialListToString(crops));

        // Enchanted Lists (convert Enchantments to Strings)
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedWeapon", convertEnchantmentListToString(enchantedWeapon));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedTool", convertEnchantmentListToString(enchantedTool));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedArmour", convertEnchantmentListToString(enchantedArmour));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedBoots", convertEnchantmentListToString(enchantedBoots));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedNegative", convertEnchantmentListToString(enchantedNegative));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedPositives", convertEnchantmentListToString(enchantedPositives));
        ConfigHandler.saveListToConfig(JavaPlugin.getProvidingPlugin(HazelsEssentials.class), "enchantedExtras", convertEnchantmentListToString(enchantedExtras));
    }

    public static ArrayList<String> convertEntityTypeListToString(ArrayList<EntityType> list) {
        ArrayList<String> stringList = new ArrayList<>();
        for (EntityType entityType : list) {
            stringList.add(entityType.name());
        }
        return stringList;
    }

    public static ArrayList<String> convertMaterialListToString(ArrayList<Material> list) {
        ArrayList<String> stringList = new ArrayList<>();
        for (Material material : list) {
            stringList.add(material.name());
        }
        return stringList;
    }

    public static ArrayList<String> convertChatColorListToString(ArrayList<ChatColor> list) {
        ArrayList<String> stringList = new ArrayList<>();
        for (ChatColor color : list) {
            stringList.add(color.name());
        }
        return stringList;
    }

    public static ArrayList<String> convertEnchantmentListToString(ArrayList<Enchantment> list) {
        ArrayList<String> stringList = new ArrayList<>();
        for (Enchantment enchantment : list) {
            stringList.add(enchantment.getKey().toString());
        }
        return stringList;
    }


    public static ArrayList<EntityType> convertStringToEntityType(ArrayList<String> list) {
        ArrayList<EntityType> entityTypeList = new ArrayList<>();
        for (String string : list) {
            try {
                entityTypeList.add(EntityType.valueOf(string));
            } catch (IllegalArgumentException e) {
                // Handle invalid enum value gracefully (e.g., log the error)
                System.err.println("Invalid EntityType: " + string);
            }
        }
        return entityTypeList;
    }


    public static ArrayList<Material> convertStringToMaterialList(ArrayList<String> list) {
        ArrayList<Material> materialList = new ArrayList<>();
        for (String string : list) {
            try {
                materialList.add(Material.valueOf(string));
            } catch (IllegalArgumentException e) {
                // Handle invalid enum value gracefully (e.g., log the error)
                System.err.println("Invalid Material: " + string);
            }
        }
        return materialList;
    }

    public static ArrayList<ChatColor> convertStringToChatColorList(ArrayList<String> list) {
        ArrayList<ChatColor> chatColorList = new ArrayList<>();
        for (String string : list) {
            try {
                chatColorList.add(ChatColor.valueOf(string));
            } catch (IllegalArgumentException e) {
                // Handle invalid enum value gracefully (e.g., log the error)
                System.err.println("Invalid ChatColor: " + string);
            }
        }
        return chatColorList;
    }

    public static ArrayList<Enchantment> convertStringToEnchantmentList(ArrayList<String> list) {
        ArrayList<Enchantment> enchantmentList = new ArrayList<>();
        for (String string : list) {
            try {
                enchantmentList.add(Enchantment.getByKey(NamespacedKey.fromString(string)));
            } catch (IllegalArgumentException | NullPointerException e) {
                // Handle invalid or null enchantment keys gracefully (e.g., log the error)
                System.err.println("Invalid Enchantment: " + string);
            }
        }
        return enchantmentList;
    }

}
