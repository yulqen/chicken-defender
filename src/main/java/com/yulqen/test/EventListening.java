package com.yulqen.test;

import java.util.concurrent.TimeUnit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
        event.setJoinMessage("YOU CAN NOW PICK UP YOUR DIAMOND ARMOUR. Remember - this game makes diamonds very easy to obtain ;-)");
    }

    @EventHandler
    public void sophieMinesIron(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getName().equals("SophieLemLem")) {
            if (checkBlockMaterial(e.getBlock().getType(), Material.IRON_ORE)) {
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 8));
                player.sendMessage("Sophie mines magic iron!");
            }
        }
    }

    @EventHandler
    public void joeTiresWithCoalAndIron(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getName().equals("_Joex")) {
            Material block = e.getBlock().getType();
            if (checkBlockMaterial(block, Material.IRON_ORE) || checkBlockMaterial(block, Material.COAL_ORE)) {
                player.setHealth(player.getHealth() / 1.2);
            }
        }
    }

    private boolean checkBlockMaterial(Material block, Material material) {
        return block.equals(material);
    }

    @EventHandler
    public void chickenDefender(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        EntityType type = e.getEntity().getType();
        if (damager instanceof Player && (type.equals(EntityType.CHICKEN) ||
                (type.equals(EntityType.COW)) ||
                (type.equals(EntityType.PIG)))) {
            ((Player) damager).setHealth(2.0);
            Entity animal = e.getEntity();
            Location loc = damager.getLocation();
            damager.sendMessage("You damaged " + animal.getName());
            damager.sendMessage(e.getEntity().getName() + " will be defended!");
            World world = damager.getWorld();
            if (type.equals(EntityType.CHICKEN)) {
                Zombie zombie = world.spawn(new Location(world, loc.getX()+2.0, loc.getY(), loc.getZ()), Zombie.class);
                zombie.setBaby();
                zombie.setInvulnerable(true);
                zombie.setCustomName("Chicken Defender");

                Zombie zombie2 = world.spawn(new Location(world, loc.getX()-3.0, loc.getY(), loc.getZ()), Zombie.class);
                zombie2.setBaby();
                zombie2.setInvulnerable(true);
                zombie2.setCustomName("Chicken Defender");

                ItemStack zSword = new ItemStack(Material.DIAMOND_SWORD);
                zSword.addEnchantment(Enchantment.DAMAGE_ALL, 5);

                ItemStack zHelmet = new ItemStack(Material.DIAMOND_HELMET, 3);
                zHelmet.addEnchantment(Enchantment.OXYGEN, 3);

                ItemStack[] zombieArmour = {zHelmet};
                zombie.getEquipment().setItemInMainHand(zSword);

				zombie.getEquipment().setArmorContents(zombieArmour);
				zombie2.getEquipment().setArmorContents(zombieArmour);

				// Give them what they want
                world.dropItemNaturally(loc, new ItemStack(Material.DIAMOND));
            } else if (type.equals(EntityType.COW)) {
                Slime slime = world.spawn(new Location(world, loc.getX()+6, loc.getY(), loc.getZ()), Slime.class);
                slime.setSize(4);
                slime.setCustomName("Moo blob");
                slime.attack(damager);
            } else { // it's a Pig
                Creeper creeper = world.spawn(new Location(world, loc.getX()-6, loc.getY(), loc.getZ()), Creeper.class);
                Creeper creeper2 = world.spawn(new Location(world, loc.getX()+6, loc.getY(), loc.getZ()), Creeper.class);
                creeper.setCustomName("Pig Protector");
                creeper2.setCustomName("Pig Protector");
            }
        }
    }

//    @EventHandler
//    public void boomIfPickUpDiamondGear(EntityPickupItemEvent e) throws InterruptedException {
//        LivingEntity player = e.getEntity();
//        World world = player.getWorld();
//        Location loc = player.getLocation();
//        if ((e.getItem().getItemStack().getType().equals(Material.DIAMOND_BOOTS)) || e.getItem().getItemStack().getType().equals(Material.DIAMOND_CHESTPLATE)
//                || e.getItem().getItemStack().getType().equals(Material.DIAMOND_HELMET)
//                || e.getItem().getItemStack().getType().equals(Material.DIAMOND_LEGGINGS))
//    {
//        	player.sendMessage("Sorry! You that stuff isn't for you... Boom!");
//        	TimeUnit.SECONDS.sleep(2);
//        	world.createExplosion(loc, 4f);
//        }
//    }

    @EventHandler
    public void sophieSpawnsWithDiamondArmour(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        if (player.getName().equals("SophieLemLem")) {
            ItemStack chestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);

            chestPlate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            chestPlate.addEnchantment(Enchantment.DURABILITY, 3);
            chestPlate.addEnchantment(Enchantment.BINDING_CURSE, 1);
            pants.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            pants.addEnchantment(Enchantment.DURABILITY, 3);
            pants.addEnchantment(Enchantment.BINDING_CURSE, 1);
            helmet.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
            helmet.addEnchantment(Enchantment.DURABILITY, 3);
            helmet.addEnchantment(Enchantment.BINDING_CURSE, 1);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            boots.addEnchantment(Enchantment.BINDING_CURSE, 1);
            sword.addEnchantment(Enchantment.KNOCKBACK, 2);
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
            sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
            pick.addEnchantment(Enchantment.DIG_SPEED, 5);

            player.getInventory().addItem(chestPlate);
            player.getInventory().addItem(pants);
            player.getInventory().addItem(helmet);
            player.getInventory().addItem(boots);
            player.getInventory().addItem(sword);
            player.getInventory().addItem(pick);
        }
    }

    @EventHandler
    public void skeletonIfPickUpLog(EntityPickupItemEvent e) {
        LivingEntity player = e.getEntity();
        Location loc = player.getLocation();
        World world = player.getWorld();
//        Block block = world.getBlockAt(loc);
//        block.setType(Material.STONE);
        Material material = e.getItem().getItemStack().getType();
        if (player.getType().equals(EntityType.PLAYER)) {
			if (checkBlockMaterial(material, Material.SPRUCE_LOG) || checkBlockMaterial(material, Material.OAK_LOG)
					|| checkBlockMaterial(material, Material.BIRCH_LOG)
					|| checkBlockMaterial(material, Material.DARK_OAK_LOG)
					|| checkBlockMaterial(material, Material.JUNGLE_LOG)
					|| checkBlockMaterial(material, Material.STRIPPED_ACACIA_LOG)
					|| checkBlockMaterial(material, Material.STRIPPED_SPRUCE_LOG)
					|| checkBlockMaterial(material, Material.STRIPPED_DARK_OAK_LOG)
					|| checkBlockMaterial(material, Material.STRIPPED_BIRCH_LOG)
					|| checkBlockMaterial(material, Material.STRIPPED_JUNGLE_LOG)
					|| checkBlockMaterial(material, Material.STRIPPED_OAK_LOG)
					|| checkBlockMaterial(material, Material.WARPED_HYPHAE)) {
				player.setHealth(2.0);
				player.sendMessage("Clank! Be careful collecting logs.");
				//            world.createExplosion(new Location(world, loc.getX(), loc.getY(), loc.getZ()-5.0), 4f);
				world.spawn(new Location(world, loc.getX() + 5, loc.getY(), loc.getZ()), Skeleton.class);
				world.spawn(new Location(world, loc.getX() - 5, loc.getY(), loc.getZ()), Skeleton.class);
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

}
