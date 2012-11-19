package com.github.schuurdeur;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

final public class main extends JavaPlugin {
      ArrayList<String> a = new ArrayList<String>();

    @Override
    public void onEnable() {
        //config
        this.saveDefaultConfig();

        //new listener
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void playerJoin(PlayerJoinEvent event) {
               if(getConfig().getBoolean("enabled")) {
                   //add current users from configuration files.
                   if(!getConfig().getStringList("players").isEmpty()) {
                       for (String players : getConfig().getStringList("players")) {
                           a.add(players);
                       }
                   }

                   //check users
                   Player player = event.getPlayer();

                   if(a.contains(player.getDisplayName())) {
                       //if player is in the list
                       player.sendMessage(ChatColor.RED + "[Warm Welcome] Welcome back, " + player.getDisplayName() + "!");
                   } else {
                      player.sendMessage(ChatColor.RED + "[Warm Welcome] Welcome to our server " + player.getDisplayName() +
                                         " ! I hope you will enjoy my present.");
                       //update inventory
                       Inventory playerInventory = player.getInventory();

                       //basic items for crafting
                       playerInventory.addItem(new ItemStack(Material.WOOD_AXE, 1));
                       playerInventory.addItem(new ItemStack(Material.WOOD_PICKAXE, 1));
                       playerInventory.addItem(new ItemStack(Material.WOOD_SWORD, 1));
                       playerInventory.addItem(new ItemStack(Material.WOOD_HOE,1));

                       //basic armor
                       playerInventory.addItem(new ItemStack(Material.LEATHER_BOOTS, 1));
                       playerInventory.addItem(new ItemStack(Material.LEATHER_HELMET, 1));
                       playerInventory.addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                       playerInventory.addItem(new ItemStack(Material.LEATHER_LEGGINGS, 1));

                      //update the list.
                      a.add(player.getDisplayName());
                      getConfig().set("players", a.toArray());
                      saveConfig();
                      reloadConfig();
                      a.clear();
                   }
               }
            }
        }, this);
    }

    @Override
    public void onDisable() {

    }

    //basic functions


}
