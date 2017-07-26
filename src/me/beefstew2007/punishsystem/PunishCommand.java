package me.beefstew2007.punishsystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PunishCommand implements CommandExecutor, Listener{
	
	public static ArrayList<String> muted = new ArrayList<String>();
	public static ArrayList<String> banned = new ArrayList<String>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		
		String punish = ChatColor.BLUE + "[Punish] " + ChatColor.AQUA + "";
		Player player = event.getPlayer();
		
		if (banned.contains(player.getName())){
			player.kickPlayer(punish + "Connection interrupted. You are banned from this server!");
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		
		String punish = ChatColor.BLUE + "[Punish] " + ChatColor.AQUA + "";
		Player player = event.getPlayer();
		
		if (muted.contains(player.getName())){
			event.setCancelled(true);
			player.sendMessage(punish + "You are muted, cannot chat!");
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

		String punish = ChatColor.BLUE + "[Punish] " + ChatColor.AQUA + "";
		
		Player target = (Player) Bukkit.getPlayerExact(args[1]);
		Player player = (Player) sender;
		
		if (alias.equalsIgnoreCase("punish")){
			if (player.hasPermission("perm.punish")){
				if (args.length == 2){
					if (args[0].equalsIgnoreCase("warn")){
						Bukkit.broadcastMessage(punish + target.getName() + " has been warned by " + player.getName());
					}
					else if (args[0].equalsIgnoreCase("mute")){
						if (muted.contains(target.getName())){
							muted.remove(target.getName());
							player.sendMessage(punish + target.getName() + " has been unmuted");
							target.sendMessage(punish+"You have been unmuted!");
						}else{
							muted.add(target.getName());
							player.sendMessage(punish+target.getName()+" has been muted.");
							target.sendMessage(punish+"You have been muted!");
						}
					}
					else if (args[0].equalsIgnoreCase("ban")){
						if (banned.contains(target.getName())){
							banned.remove(target.getName());
							Bukkit.broadcastMessage(punish + target.getName() + " has been unbanned from the server!");
						}else{
							banned.add(target.getName());
							Bukkit.broadcastMessage(punish + target.getName() + " has been banned by " + player.getName());
							target.kickPlayer(punish + "Connection Interrupted...");
						}
					}
					else if (args[0].equalsIgnoreCase("kick")){
					}
						target.kickPlayer(punish + "You have been kicked by " + player.getName());
						Bukkit.broadcastMessage(punish + target.getName() + " has been kicked by " + player.getName());
						}

					
				}else{
					player.sendMessage(punish + "Invalid arguments bro!");
					}
			}else{
				player.sendMessage(punish + "You don't have permission!");
				}
		return false;
	}

}
