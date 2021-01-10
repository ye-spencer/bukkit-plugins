package me.TheSteak.multiteammanhunt;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
/*
 * TODO:
 * add conformations
 * speed up compass updating
 * 
 */
public class ManhuntTeamManagement implements CommandExecutor 
{
	
	private Main plugin;
	
	private boolean track;
	
	private ArrayList<Player> hunters, runners;
	private ArrayList<Integer> hunterpoint;
	
	private Timer updating;
	
	public ManhuntTeamManagement (Main in)
	{
		plugin = in;
		plugin.getCommand("teamhunter").setExecutor(this);
		plugin.getCommand("teamrunner").setExecutor(this);
		plugin.getCommand("switchtrack").setExecutor(this);
		plugin.getCommand("startcompass").setExecutor(this);
		plugin.getCommand("stopcompass").setExecutor(this);
		hunters = new ArrayList<Player>();
		runners = new ArrayList<Player>();
		hunterpoint = new ArrayList<Integer>();
		track = false;
		
		updating = new Timer();
		updating.schedule(new updateLoc(), 0, 1000);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		if ("teamhunter".equals(cmd.getName()))
		{
			runners.remove((Player)sender);
			hunters.add((Player)sender);
			if (runners.isEmpty())
			{
				hunterpoint.add(-1);
				hunters.get(hunters.size() - 1).sendMessage("There is no one to track yet :(");
			}
			else
			{
				hunterpoint.add(0);
				hunters.get(hunters.size() - 1).sendMessage("Your compass is now pointing to " + ChatColor.GREEN + " " + runners.get(0).getName().toUpperCase());
			}
			Bukkit.broadcastMessage(sender.getName() + " has been added to the " + ChatColor.RED + "hunter team");
		}
		else if ("teamrunner".equals(cmd.getName()))
		{
			hunters.remove((Player)sender);
			runners.add((Player)sender);
			if (runners.size() == 1)
			{
				for (int i = 0; i < hunters.size(); ++i)
				{
					hunterpoint.set(i, 0);
					hunters.get(i).sendMessage("Your compass is now pointing to " + ChatColor.GREEN + " " + runners.get(0).getName().toUpperCase());
				}
			}
			Bukkit.broadcastMessage(sender.getName() + " has been added to the " + ChatColor.BLUE + "runner team");
		}
		else if ("switchtrack".equals(cmd.getName()))
		{
			int point = hunters.indexOf((Player)sender);
			hunterpoint.set(point, (hunterpoint.get(point) + 1) % runners.size());
			sender.sendMessage("Your compass is now pointing to " + ChatColor.GREEN + " " + runners.get(hunterpoint.get(point)).getName().toUpperCase());
		}
		else if ("startcompass".equals(cmd.getName()))
		{
			track = true;
			Bukkit.broadcastMessage("compasses have started tracking");
		}
		else if ("stopcompass".equals(cmd.getName()))
		{
			track = false;
		}
		return false;
	}
	
	private void updatePositions()
	{
		for (int i = 0; i < hunters.size(); ++i)
		{
			hunters.get(i).setCompassTarget(runners.get(hunterpoint.get(i)).getLocation());
		}
			
	}
	
	
	private class updateLoc extends TimerTask 
	{
	    public void run() 
	    {
	       if (track)
	       {
	    	   updatePositions();
	       }
	    }
	}
}
