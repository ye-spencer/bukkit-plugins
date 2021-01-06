package me.TheSteak.multiteammanhunt;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManhuntTeamManagement implements CommandExecutor 
{
	
	private Main plugin;
	
	ArrayList<Player> hunters, runners;
	
	public ManhuntTeamManagement (Main in)
	{
		plugin = in;
		plugin.getCommand("teamhunter").setExecutor(this);
		plugin.getCommand("teamrunner").setExecutor(this);
		plugin.getCommand("switchtrack").setExecutor(this);
		hunters = new ArrayList<Player>();
		runners = new ArrayList<Player>();
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		if ("teamhunter".equals(cmd.getName()))
		{
			hunters.add((Player)sender);
		}
		else if ("teamrunner".equals(cmd.getName()))
		{
			runners.add((Player)sender);
		}
		else if ("switchtrack".equals(cmd.getName()))
		{
			
		}
		return true;
	}
}
