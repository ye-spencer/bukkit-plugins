package me.TheSteak.multiplayerdeathswap;

import java.util.ArrayList;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MultiplayerDeathSwap implements CommandExecutor
{
	private Main plugin;
	private Server server;
	
	private ArrayList<Player> players;
	
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
		if ("joindeathgame".equals(cmd.getName()))
		{
			
		}
		else if ("leavedeathgame".equals(cmd.getName()))
		{
			
		}
		else if ("startdeathgame".equals(cmd.getName()))
		{
			
		}
		return false;
	}
}
