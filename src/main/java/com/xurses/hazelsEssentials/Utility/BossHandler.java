package com.xurses.hazelsEssentials.Utility;

import com.xurses.hazelsEssentials.HazelsEssentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;
import java.util.Random;

import static org.bukkit.Bukkit.broadcastMessage;

public class BossHandler implements Listener {

    static LivingEntity livingEntity; // Changed to LivingEntity for more general use

    static Random rand = new Random();
    public static String monsterCustomName;

    public static void spawnCustomMob(Location location, Player player, EntityType entity, double speed, double damage, double health, double defence) {
        World world = location.getWorld();
        if (world == null) {
            throw new NullPointerException("World is null for location: " + location);
        }

        Entity spawnedEntity = world.spawnEntity(location, entity);
        if (!(spawnedEntity instanceof LivingEntity)) {
            throw new IllegalArgumentException("Spawned entity is not a LivingEntity: " + entity);
        }

        livingEntity = (LivingEntity) spawnedEntity;
        if (player == null) {
            throw new NullPointerException("Player is null when spawning custom mob.");
        }

        // Apply custom attributes (only for relevant types)
        if (livingEntity instanceof Monster) {
            Monster monster = (Monster) livingEntity;
            monster.attack(player);
        }

        String name = ArrayLists.names.get(rand.nextInt(0, ArrayLists.names.size()));
        livingEntity.setCustomName(name.replace("'s", ""));

        Objects.requireNonNull(livingEntity.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(health);
        livingEntity.setHealth(health);
        Objects.requireNonNull(livingEntity.getAttribute(Attribute.ATTACK_DAMAGE)).setBaseValue(damage);
        Objects.requireNonNull(livingEntity.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(speed);
        Objects.requireNonNull(livingEntity.getAttribute(Attribute.ARMOR_TOUGHNESS)).setBaseValue(defence);
        livingEntity.setPersistent(true);
        // Optional attributes (handle nulls)
        Attribute flyingSpeed = Attribute.FLYING_SPEED;
        if (livingEntity.getAttribute(flyingSpeed) != null) {
            Objects.requireNonNull(livingEntity.getAttribute(flyingSpeed)).setBaseValue(speed);
        }

        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 3));
        livingEntity.setGlowing(true); // This is decorative, can be omitted.
        freezeMobForDuration(HazelsEssentials.getProvidingPlugin(HazelsEssentials.class), livingEntity, 5);
    }

    public static void freezeMobForDuration(JavaPlugin plugin, LivingEntity mob, int durationInSeconds) {
        mob.setAI(false);
        Bukkit.getScheduler().runTaskLater(plugin, () -> mob.setAI(true), durationInSeconds * 20L); // Unfreeze after duration
    }

    @EventHandler
    public void onCustomBossDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return; // Exit if the entity is not a LivingEntity
        }
        LivingEntity entity = (LivingEntity) e.getEntity();
        monsterCustomName = entity.getCustomName(); // Fetch the actual custom name
        //System.out.println("Entity killed: " + monsterCustomName);

        // Ensure the custom name exists and matches
        if (monsterCustomName != null && ArrayLists.names.stream()
                .map(name -> name.replace("'s", "")) // Normalize names from the list
                .anyMatch(name -> monsterCustomName.contains(name))) { // Compare with the entity's name
            Player killer = entity.getKiller(); // Get the killer
            if (killer != null) {
                try {

                    // Generate custom loot
                    CustomLootTables customLootTables = new CustomLootTables();
                    customLootTables.generateCustomLoot(entity, killer);
                    // Broadcast the message
                    broadcastMessage(killer.getName().toLowerCase() + " has slain " + monsterCustomName);
                } catch (Exception ex) {
                    //System.out.println("Error in loot generation or broadcasting: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                //System.out.println("No killer associated with the entity.");
            }
        } else {
            //System.out.println("Custom name is null or not matching any target names.");
        }
    }
}
