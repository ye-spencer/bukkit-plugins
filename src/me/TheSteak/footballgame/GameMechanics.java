package me.TheSteak.footballgame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.md_5.bungee.api.ChatColor;

public class GameMechanics implements CommandExecutor, Listener
{
	
	private Main plugin;
	private Server server;
	private boolean enabled;
	
	private ArrayList<Player> a, b;
	
	public GameMechanics (Main in)
	{
		enabled = false;
		plugin = in;
		
		server = plugin.getServer();
		
		a = new ArrayList<Player>();
		b = new ArrayList<Player>();
		
		plugin.getCommand("startfootballgame").setExecutor(this);
		plugin.getCommand("endfootballgame").setExecutor(this);
		
		plugin.getCommand("teama");
		plugin.getCommand("teamb");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] others) 
	{
		
		if (command.getName() == "startfootballgame")
		{
			server.broadcastMessage("game started");
			enabled = true;
			return true;
		}
		else if (command.getName() == "endfootballgame")
		{
			server.broadcastMessage("game ended");
			enabled = false;
			return true;
		}
		else if (command.getName() == "teama")
		{
			if (others.length > 1) return false;
			Player target;
			if (others.length == 1)
			{
				target = Bukkit.getPlayer(others[0]);
			}
			else
			{
				target = (Player)sender;
			}
			b.remove(target);
			a.add(target);
			server.broadcastMessage("Team A: " + ChatColor.BLUE + listPlayers(a) + 
					"Team B: " + ChatColor.RED + listPlayers(b));
			return true;
		}
		else if (command.getName() == "teamb")
		{
			if (others.length > 1) return false;
			Player target;
			if (others.length == 1)
			{
				target = Bukkit.getPlayer(others[0]);
			}
			else
			{
				target = (Player)sender;
			}
			a.remove(target);
			b.add(target);
			server.broadcastMessage("Team A: " + ChatColor.BLUE + listPlayers(a) + 
					"Team B: " + ChatColor.RED + listPlayers(b));
			return true;
		}
		return false;
	}
	

	
	@EventHandler
    public void onHit(EntityDamageEvent event)
	{
		if (enabled)
		{
			if (event.getEntity() instanceof Player && event.getEntity().getLastDamageCause() instanceof Player)
			{
				Player receiver = (Player)event.getEntity();
				Player attacker = (Player)event.getEntity().getLastDamageCause();
				if (a.contains(receiver) && b.contains(attacker) || b.contains(receiver) && a.contains(attacker))
				{
					//TODO figureout what to do
				}
			}
        }
    }
	
	private String listPlayers(ArrayList<Player> arr)
	{
		StringBuilder sb = new StringBuilder();
		for (Player p : arr)
		{
			sb.append(p.getName() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

}
