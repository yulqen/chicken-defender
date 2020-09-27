package com.yulqen.test;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Test MC Plugin Loaded");
        World world = getServer().getWorld("mad_march_hare");
//        for (int i = 0; i < 20; i++) {
//            System.out.println("Spawning " + EntityType.CHICKEN);
//            world.spawnEntity(new Location(world, 64.0, 64.0 + (double)(i), 208.0), EntityType.CHICKEN);
//        }
        getServer().getWorld("mad_march_hare").setFullTime(1000);
        getServer().getPluginManager().registerEvents(new EventListening(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
