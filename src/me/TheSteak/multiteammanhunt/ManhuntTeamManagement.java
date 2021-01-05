package me.TheSteak.multiteammanhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ManhuntTeamManagement implements CommandExecutor 
{
	
	private Main plugin;
	
	public ManhuntTeamManagement (Main in)
	{
		plugin = in;
		plugin.getCommand("teamhunter").setExecutor(this);
		plugin.getCommand("teamrunner").setExecutor(this);
		plugin.getCommand("switchtrack").setExecutor(this);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		if ("teamhunter".equals(cmd.getName()))
		{
			
		}
		else if ("teamrunner".equals(cmd.getName()))
		{
			
		}
		else if ("switchtrack".equals(cmd.getName()))
		{
			
		}
		return true;
	}
}
