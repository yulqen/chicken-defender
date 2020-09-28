package com.yulqen.test;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveMeDiamond implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ItemStack diamond = new ItemStack(Material.DIAMOND);
            diamond.setAmount(64);
            player.getInventory().addItem(diamond);
        }
        return true;
    }
}
