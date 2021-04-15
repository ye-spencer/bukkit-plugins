package me.TheSteak.multiplayerdeathswap;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MultiplayerDeathSwap implements CommandExecutor
{
	private Main plugin;
	private Server server;
	
	public MultiplayerDeathSwap(Main main)
	{
		this.plugin = main;
		
		server = plugin.getServer();
		plugin.getCommand("joindeathgame").setExecutor(this);
		plugin.getCommand("leavedeathgame").setExecutor(this);
		plugin.getCommand("startdeathgame").setExecutor(this);
				
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] others) 
	{
		return false;
	}
}
