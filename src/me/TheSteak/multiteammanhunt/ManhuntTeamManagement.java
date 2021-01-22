package me.TheSteak.multiteammanhunt;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
/*
 * TODO:
 *  clean up code
 *  catch errors
 *  
 *  ERROR:
 *  compasses do not track players, only spawn locations
 */
public class ManhuntTeamManagement implements CommandExecutor 
{
	
	private Main plugin;
	
	private ArrayList<Player> hunters, runners;
	private ArrayList<Integer> hunterpoint, runnerpoint;
	
	
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
		runnerpoint = new ArrayList<Integer>();
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new updateClass(), 1, 10);
		
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		Player p = (Player)sender;
		if ("teamhunter".equals(cmd.getName()))
		{
			runners.remove(p);
			hunters.add(p);
			if (runners.isEmpty())
			{
				hunterpoint.add(-1);
				p.sendMessage("There is no one to track yet");
			}
			else
			{
				hunterpoint.add(0);
				p.sendMessage("Your compass is now pointing to " + ChatColor.GREEN  + runners.get(0).getName().toUpperCase());
			}
			Bukkit.broadcastMessage(p.getName() + " has been added to the " + ChatColor.RED + "hunter team");
			Bukkit.broadcastMessage(ChatColor.BLUE + "Runner Team " + runners.toString());
			Bukkit.broadcastMessage(ChatColor.RED + "Hunter Team " + hunters.toString());
			return true;
		}
		else if ("teamrunner".equals(cmd.getName()))
		{
			hunters.remove(p);
			runners.add(p);	
			if (runners.size() == 1)
			{
				for (int i = 0; i < hunters.size(); ++i)
				{
					hunterpoint.set(i, 0);
					hunters.get(i).sendMessage("Your compass is now pointing to " + ChatColor.GREEN + p.getName().toUpperCase());
				}
				runnerpoint.add(-1);
				p.sendMessage("There is no teammates to track (yet)");
			}
			else if (runners.size() == 2)
			{
				runnerpoint.set(0, 1);
				runnerpoint.set(1, 0);
				runners.get(0).sendMessage("Your compass is now pointing to " + ChatColor.GREEN + 
						" " + p.getName().toUpperCase() + 
						" Distance " + runners.get(0).getLocation().distance(p.getLocation()));
				p.sendMessage("Your compass is now pointing to " + ChatColor.GREEN + 
						" " + runners.get(0).getName().toUpperCase() + 
						" Distance " + runners.get(0).getLocation().distance(runners.get(0).getLocation()));
			}
			Bukkit.broadcastMessage(p.getName() + " has been added to the " + ChatColor.BLUE + "runner team");
			Bukkit.broadcastMessage(ChatColor.BLUE + "Runner Team " + runners.toString());
			Bukkit.broadcastMessage(ChatColor.RED + "Hunter Team " + hunters.toString());
			return true;
			
		}
		else if ("switchtrack".equals(cmd.getName()))
		{
			int point = hunters.indexOf(p);
			if (point != -1)
			{
				hunterpoint.set(point, (hunterpoint.get(point) + 1) % runners.size());
				p.sendMessage("Your compass is now pointing to " + ChatColor.GREEN + " " + runners.get(hunterpoint.get(point)).getName().toUpperCase());
			}
			point = runners.indexOf(p);
			if (point != -1)
			{
				int i = runnerpoint.get(point);
				if (i + 1 == point)
				{
					runnerpoint.set(point, (i + 2) % runners.size());
				}
				else
				{
					runnerpoint.set(point, (i + 1) % runners.size());
				}
			}
		}
		return false;
	}
	
	private void updatePositions()
	{
		for (int i = 0; i < hunters.size(); ++i)
		{
			int k = hunterpoint.get(i);
			if (k > 0) hunters.get(i).setCompassTarget(runners.get(k).getLocation());
		}
		for (int i = 0; i < runners.size(); ++i)
		{
			int k = runnerpoint.get(i);
			if (k > 0) hunters.get(i).setCompassTarget(runners.get(k).getLocation());
		}
	}
	
	private class updateClass implements Runnable
	{

		@Override
		public void run() 
		{
			Bukkit.broadcastMessage("thread updated");
			updatePositions();
		}
		
	}
	
	
}
