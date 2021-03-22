package me.TheSteak.multiteammanhunt;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
/*
 * TODO:
 * 
 * IDEA:
 * add a get teams method
 * 
 * ERROR:
 * can't switch teams without error
 * you can join a team twice
 * 
 */
public class ManhuntTeamManagement implements CommandExecutor, Listener
{
	
	private Main plugin;
	
	private ArrayList<Player> hunters, runners;
	private ArrayList<Integer> hunterpoint, runnerpoint;
	
	private Server server;
	
	
	public ManhuntTeamManagement (Main in)
	{
		plugin = in;
		
		plugin.getCommand("teamhunter").setExecutor(this);
		plugin.getCommand("teamrunner").setExecutor(this);
		plugin.getCommand("switchtrack").setExecutor(this);
		plugin.getCommand("gethmteams").setExecutor(this);
		plugin.getCommand("leaveteam").setExecutor(this);
		
		hunters = new ArrayList<Player>();
		runners = new ArrayList<Player>();
		hunterpoint = new ArrayList<Integer>();
		runnerpoint = new ArrayList<Integer>();
		
		server = Bukkit.getServer();
		
		server.getScheduler().runTaskTimer(plugin, new updateClass(), 1, 8);
		
		server.broadcastMessage("timer started");
			
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		server.broadcastMessage("command called");
		Player p = (Player)sender;
		if ("teamhunter".equals(cmd.getName()))
		{
			if (playerArrToString(hunters).contains(p.getName()))
			{
				p.sendMessage("You are already on the " + ChatColor.RED + "hunter team");
				return true;
			}
			if (runners.contains(p))
			{
				int i = runners.indexOf(p);
				runnerpoint.remove(i);
				runners.remove(i);
				for (int j = 0; j < hunterpoint.size(); j++)
				{
					if (hunterpoint.get(j) == i)
					{
						int k = runners.size();
						hunterpoint.set(j, k - 1);
						if (k <= 0) hunters.get(j).sendMessage("There is no one to track");
						else hunters.get(j).sendMessage("Your compass is now pointing to " + ChatColor.GREEN  + runners.get(j).getPlayerListName().toUpperCase());
					}
				}
			}
			hunters.add(p);
			if (runners.isEmpty())
			{
				hunterpoint.add(-1);
				p.sendMessage("There is no one to track yet");
			}
			else
			{
				hunterpoint.add(0);
				p.sendMessage("Your compass is now pointing to " + ChatColor.GREEN  + runners.get(0).getPlayerListName().toUpperCase());
			}
			server.broadcastMessage(p.getPlayerListName() + " has been added to the " + ChatColor.RED + "hunter team");
			server.broadcastMessage(ChatColor.BLUE + "Runner Team " + playerArrToString(runners) + runnerpoint.toString());
			server.broadcastMessage(ChatColor.RED + "Hunter Team " + playerArrToString(hunters) + hunterpoint.toString());
			return true;
		}
		else if ("teamrunner".equals(cmd.getName()))
		{
			if (playerArrToString(runners).contains(p.getName()))
			{
				p.sendMessage("You are already on the " + ChatColor.BLUE + "runner team");
				return true;
			}
			if (hunters.contains(p))
			{
				hunterpoint.remove(hunters.indexOf(p));
				hunters.remove(p);
			}
			runners.add(p);	
			if (runners.size() == 1)
			{
				for (int i = 0; i < hunters.size(); ++i)
				{
					hunterpoint.set(i, 0);
					hunters.get(i).sendMessage("Your compass is now pointing to " + ChatColor.GREEN + p.getPlayerListName().toUpperCase());
				}
				runnerpoint.add(-1);
				p.sendMessage("There is no teammates to track (yet)");
			}
			else if (runners.size() == 2)
			{
				runnerpoint.set(0, 1);
				runnerpoint.set(1, 0);
				runners.get(0).sendMessage("Your compass is now pointing to " + ChatColor.GREEN + 
						" " + p.getPlayerListName().toUpperCase() + 
						" [Distance " + runners.get(0).getLocation().distance(p.getLocation()) + "]");
				p.sendMessage("Your compass is now pointing to " + ChatColor.GREEN + 
						" " + runners.get(0).getPlayerListName().toUpperCase() + 
						" [Distance " + runners.get(0).getLocation().distance(runners.get(0).getLocation()) + "]");
			}
			server.broadcastMessage(p.getPlayerListName() + " has been added to the " + ChatColor.BLUE + "runner team");
			server.broadcastMessage(ChatColor.BLUE + "Runner Team " + playerArrToString(runners) + runnerpoint.toString());
			server.broadcastMessage(ChatColor.RED + "Hunter Team " + playerArrToString(hunters) + hunterpoint.toString());
			return true;
			
		}
		else if ("switchtrack".equals(cmd.getName()))
		{
			int point = hunters.indexOf(p);
			if (point != -1)
			{
				hunterpoint.set(point, (hunterpoint.get(point) + 1) % runners.size());
				p.sendMessage("Your compass is now pointing to " + ChatColor.GREEN + " " + runners.get(hunterpoint.get(point)).getPlayerListName().toUpperCase());
			}
			point = runners.indexOf(p);
			if (point != -1)
			{
				int i = runnerpoint.get(point);
				if (i + 1 == point) runnerpoint.set(point, (i + 2) % runners.size());
				else runnerpoint.set(point, (i + 1) % runners.size());
			}
			return true;
		}
		else if ("getmhteams".equals(cmd.getName()))
		{
			server.broadcastMessage(ChatColor.BLUE + "Runner Team " + playerArrToString(runners) + runnerpoint.toString());
			server.broadcastMessage(ChatColor.RED + "Hunter Team " + playerArrToString(hunters) + hunterpoint.toString());
			return true;
		}
		else if ("leaveteam".equals(cmd.getName()))
		{
			int num = runners.indexOf(p);
			if (num > 0)
			{
				runners.remove(num);
				runnerpoint.remove(num);
				return true;
			}
			num = hunters.indexOf(p);
			if (num > 0)
			{
				hunters.remove(num);
				hunterpoint.remove(num);
				return true;
			}
			p.sendMessage("you are not on a team");
			return true;
		}
		return false;
	}
	
	private void updatePositions()
	{
		for (int i = 0; i < hunters.size(); i++)
		{
			int k = hunterpoint.get(i);
			if (k >= 0 && hunters.get(i).getWorld().getEnvironment() == runners.get(k).getWorld().getEnvironment()) hunters.get(i).setCompassTarget(runners.get(k).getLocation());
		}
		for (int i = 0; i < runners.size(); i++)
		{
			int k = runnerpoint.get(i);
			if (k >= 0 && hunters.get(k).getWorld().getEnvironment() == runners.get(i).getWorld().getEnvironment()) runners.get(i).setCompassTarget(hunters.get(k).getLocation());
		}
	}
	
	private String playerArrToString(ArrayList<Player> arr)
	{
		StringBuilder s = new StringBuilder();
		for (Player p : arr)
		{
			s.append(p.getPlayerListName());
			s.append(" ");
		}
		return s.toString();
	}
	
	private class updateClass implements Runnable
	{
		public void run() 
		{
			updatePositions();
		}
	}
	
	
}
