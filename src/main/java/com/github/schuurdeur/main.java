package com.github.schuurdeur;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

final public class main extends JavaPlugin {
      

      
    public void LoadDefaultConfig(){
    	/** Method to load the default configuration and save it to the plugins directory 
    	 * as config.yml when run for the first time */
    	this.saveDefaultConfig();
    }
    
    
      
    @Override
    public void onEnable() {
        //config
        LoadDefaultConfig();
    	

        //new listener
        getServer().getPluginManager().registerEvents(new Listener() {
            
        	
        	@EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
            	Player player = event.getPlayer();
            	String playername = player.getName();
            	World world = player.getWorld();
            	
            	File playerdat = new File(world.getWorldFolder() + "\\players\\" + playername + ".dat");
            	
            	if(playerdat.exists()) {
            		//Player has been here before, send them the welcome back message.
            		player.sendMessage(ChatColor.RED + getConfig().getString("OldPlayer Welcome Prefix") + " " + player.getDisplayName() + " " + getConfig().getString("OldPlayer Welcome Suffix"));
            	}
            	
            	else {
            		//Player has never been here before, give them WarmWelcome and give some stuff.
            		player.sendMessage(ChatColor.RED + getConfig().getString("NewPlayer Welcome Prefix") + " " + player.getDisplayName() +
                            " " + getConfig().getString("NewPlayer Welcome Suffix"));
            		
            		//Log the New Player to the console & Log for admins & debug
            		getLogger().info("Detected New Player - " + playername + " Invoking WarmWelcome Package");
            		
            	
            		//update inventory
            		Inventory playerInventory = player.getInventory();

            		//basic items for crafting
            		playerInventory.addItem(new ItemStack(Material.WOOD_AXE, 1));
            		playerInventory.addItem(new ItemStack(Material.WOOD_PICKAXE, 1));
            		playerInventory.addItem(new ItemStack(Material.WOOD_SWORD, 1));
            		playerInventory.addItem(new ItemStack(Material.WOOD_HOE,1));
            		playerInventory.addItem(new ItemStack(Material.WOOD_SPADE,1));

            		//basic armor
            		playerInventory.addItem(new ItemStack(Material.LEATHER_BOOTS, 1));
            		playerInventory.addItem(new ItemStack(Material.LEATHER_HELMET, 1));
            		playerInventory.addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
            		playerInventory.addItem(new ItemStack(Material.LEATHER_LEGGINGS, 1));
            	}
            }
            
            
        }, this);
    }

    @Override
    public void onDisable() {

    }

    //basic functions


}
