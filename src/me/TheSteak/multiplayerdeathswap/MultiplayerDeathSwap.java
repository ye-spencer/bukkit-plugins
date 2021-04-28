package me.TheSteak.multiplayerdeathswap;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class MultiplayerDeathSwap implements CommandExecutor, Listener
{
	private Main plugin;
	private Server server;
	
	private ArrayList<Player> players;
	
	private boolean gameStarted;
	
	private Timer timer;
	
	private final int minutesVary = 2, minutesMin = 4;
	
	public MultiplayerDeathSwap(Main main)
	{
		this.plugin = main;
		
		server = plugin.getServer();
		plugin.getCommand("joindeathgame").setExecutor(this);
		plugin.getCommand("leavedeathgame").setExecutor(this);
		plugin.getCommand("startdeathgame").setExecutor(this);
		
		gameStarted = true;
		
		timer = new Timer();		
		
	}
	
	
	public void onDeath(PlayerDeathEvent event) 
	{
		Player p = event.getEntity();
		if (gameStarted && players.remove(p))
		{
			if (players.size() == 1)
			{
				gameStarted = false;
				server.broadcastMessage("The winner of death swap is " + ChatColor.GOLD + p.getName());
				timer.cancel();
			}
			else if (players.size() == 0)
			{
				server.broadcastMessage("lol, no one won, you all died, BAD");
				gameStarted = false;
				timer.cancel();
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] others) 
	{
		if ("joindeathgame".equals(cmd.getName()))
		{
			Player p = (Player)sender;
			if (players.contains(p)) p.sendMessage("You are already in the death swap game");
			else
			{
				players.add(p);
				p.sendMessage("You have now joined the death swap game");
			}
			return true;
		}
		else if ("leavedeathgame".equals(cmd.getName()))
		{
			Player p = (Player)sender;
			if (players.remove(p)) p.sendMessage("You have now left the death game");
			else p.sendMessage("You were not in the death game previously");
			return true;
		}
		else if ("startdeathgame".equals(cmd.getName()))
		{
			if (gameStarted) server.broadcastMessage("Game has already been started");
			else
			{
				gameStarted = true;
				server.broadcastMessage("Game has officially begun");
				startGame();
			}
		}
		return false;
	}
	
	private void startGame()
	{
		while (players.size() > 1)
		{
			newRound();
		}
	}
	
	private void newRound()
	{
		timer.schedule(new Swapper(), (long) (Math.random() * (minutesVary * 60000) + (minutesMin * 60000)));
	}
	
	private boolean properlyShifted(ArrayList<Location> old, ArrayList<Location> shuffled)
	{
		return false;
	}
	
	private void shuffle(ArrayList<Location> arr)
	{
		
	}
	
	private class Swapper extends TimerTask
	{
		@Override
		public void run()
		{
			for (int i = 10; i > 0; i--)
			{
				
				server.broadcastMessage(ChatColor.RED + "Swapping in " + i);
				try 
				{
					TimeUnit.SECONDS.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			server.broadcastMessage(ChatColor.RED + "Swapping. . . ");
			ArrayList<Location> oldLocations = new ArrayList<Location>(), temp = new ArrayList<Location>();
			for (Player p : players)
			{
				oldLocations.add(p.getLocation());
				temp.add(p.getLocation());
			}
			while (!properlyShifted(temp, oldLocations))
			{
				shuffle(oldLocations);
			}
			for (int i = 0; i < players.size(); i++)
			{
				players.get(i).teleport(oldLocations.get(i));
			}
		}
	}
}
