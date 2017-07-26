package me.beefstew2007.punishsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private static Plugin plugin;
	
	String punishConsole = "[Punish] ";
	String punish = ChatColor.DARK_RED + "[Punish] " + ChatColor.RED + "";
	
	public void onEnable(){
		plugin = this;
		Bukkit.broadcastMessage(punish + "Punish plugin by beefstew2007 (Shxke) loaded! Version: 1.1");
		registerEvents(this, new PunishCommand());
		getCommand("punish").setExecutor(new PunishCommand());
	}
	
	public void onDisable(){
		plugin = null;
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	 
	public static Plugin getPlugin() {
		return plugin;
	}

}
