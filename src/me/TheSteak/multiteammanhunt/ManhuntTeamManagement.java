package me.TheSteak.multiteammanhunt;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/*
 * TODO:
 * account for switching teams
 */
public class ManhuntTeamManagement implements CommandExecutor 
{
	
	private Main plugin;
	
	ArrayList<Player> hunters, runners;
	ArrayList<Integer> hunterpoint;
	
	public ManhuntTeamManagement (Main in)
	{
		plugin = in;
		plugin.getCommand("teamhunter").setExecutor(this);
		plugin.getCommand("teamrunner").setExecutor(this);
		plugin.getCommand("switchtrack").setExecutor(this);
		hunters = new ArrayList<Player>();
		runners = new ArrayList<Player>();
		hunterpoint = new ArrayList<Integer>();
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		if ("teamhunter".equals(cmd.getName()))
		{
			hunters.add((Player)sender);
			if (runners.isEmpty())
			{
				hunterpoint.add(-1);
			}
			else
			{
				hunterpoint.add(0);
			}
		}
		else if ("teamrunner".equals(cmd.getName()))
		{
			runners.add((Player)sender);
			if (runners.size() == 1)
			{
				for (int i = 0; i < hunters.size(); ++i)
				{
					hunterpoint.set(i, 0);
				}
			}
		}
		else if ("switchtrack".equals(cmd.getName()))
		{
			
		}
		return true;
	}
}
