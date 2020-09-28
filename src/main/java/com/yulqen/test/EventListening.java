package com.yulqen.test;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class EventListening implements Listener {

//    @EventHandler
//    public void treeGenOnPickUp(EntityPickupItemEvent event) {
//        LivingEntity entity = event.getEntity();
//        String name = entity.getName();
//
//        World world = event.getEntity().getWorld();
//        Location loc = entity.getLocation();
//        world.generateTree(new Location(world, loc.getX() +1.0, loc.getY() +1.0, loc.getZ()), TreeType.BIG_TREE);
//    }

    @EventHandler
    public void joinMessage(PlayerJoinEvent event) {
        event.setJoinMessage("Welcome to the Server! Please behave and fun! (By the way, we have plugins and strange things might " +
                "happen");
    }

    @EventHandler
    public void sophieMinesIron(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getName().equals("SophieLemLem")) {
            if (e.getBlock().getType().equals(Material.IRON_ORE)) {
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 8));
                player.sendMessage("Sophie mines magic iron!");
            }
        }
    }

    @EventHandler
    public void joeTiresWithCoalAndIron(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getName().equals("_Joex")) {
            if (e.getBlock().getType().equals(Material.IRON_ORE) || e.getBlock().getType().equals(Material.COAL_ORE)) {
                player.setHealth(player.getHealth() / 1.2);
            }
        }
    }

    @EventHandler
    public void chickenDefender(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (damager instanceof Player && e.getEntity().getType().equals(EntityType.CHICKEN)) {
            Entity chicken = e.getEntity();
            Location loc = damager.getLocation();
            damager.sendMessage("You damaged " + e.getEntity().getName());
            damager.sendMessage(e.getEntity().getName() + " will be defended!");
            World world = damager.getWorld();
            Zombie zombie = world.spawn(new Location(world, loc.getX()+2.0, loc.getY(), loc.getZ()), Zombie.class);
            zombie.setBaby();
            zombie.setInvulnerable(true);
            zombie.setCustomName("Chicken Defender");
            world.dropItemNaturally(loc, new ItemStack(Material.DIAMOND));
        }
    }

    @EventHandler
    public void boomIfPickupOak(EntityPickupItemEvent e) {
        LivingEntity player = e.getEntity();
        Location loc = player.getLocation();
        World world = player.getWorld();
//        Block block = world.getBlockAt(loc);
//        block.setType(Material.STONE);
        if (e.getItem().getItemStack().getType().equals(Material.SPRUCE_LOG)) {
            player.sendMessage("Clank! Be careful collecting spruce logs.");
//            world.createExplosion(new Location(world, loc.getX(), loc.getY(), loc.getZ()-5.0), 4f);
            world.spawn(new Location(world, loc.getX() + 5, loc.getY(), loc.getZ()), Skeleton.class);
            world.spawn(new Location(world, loc.getX() -5, loc.getY(), loc.getZ()), Skeleton.class);
        }
//        if (e.getItem().getItemStack().getType().equals(Material.DIRT)) {
//            player.sendMessage("Collecting dirt is a bad thing...");
//            world.spawn(new Location(world, loc.getX(), loc.getY(), loc.getZ() + 2.0), Wolf.class);
//        }
//        e.getItem().getItemStack().setType(Material.ANVIL);
//        double playerHealth = player.getHealth();
//        player.setHealth(playerHealth / 1.2);
//        player.sendMessage("You have lost some health.... ");
    }

}
