package com.yulqen.test;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

import java.util.Collection;

import static org.bukkit.Bukkit.getServer;

public class KillZombies implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        World world = getServer().getWorld("mad_march_hare");
        Collection<Entity> zombies = world.getEntitiesByClasses(Zombie.class);
        for (Entity z : zombies) {
            z.remove();
        }
        if (zombies.isEmpty()) {
            return false;
        }
        System.out.println("Killed all the zombies...");
        return true;
    }
}
