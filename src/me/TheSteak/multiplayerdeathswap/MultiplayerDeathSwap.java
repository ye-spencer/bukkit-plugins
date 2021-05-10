package me.TheSteak.multiplayerdeathswap;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private final int minutesVary = 2, minutesMin = 4, secondsPerCountdown = 10;
	
	public MultiplayerDeathSwap(Main main)
	{
		this.plugin = main;
		
		server = plugin.getServer();
		plugin.getCommand("joindeathgame").setExecutor(this);
		plugin.getCommand("leavedeathgame").setExecutor(this);
		plugin.getCommand("startdeathgame").setExecutor(this);
		
		gameStarted = true;
		
		
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
			}
			else if (players.size() == 0)
			{
				server.broadcastMessage("lol, no one won, you all died, BAD");
				gameStarted = false;
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
		try 
		{
			TimeUnit.SECONDS.sleep((long) (Math.random() * (minutesVary * 60) + (minutesMin * 60)));
			run();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	private boolean properlyShifted(ArrayList<Location> old, ArrayList<Location> shuffled)
	{
		for (int i = 0; i < old.size(); i++) if (shuffled.get(i).equals(old.get(i))) return false;
		return true;
	}
	
	private void run()
	{
		for (int i = secondsPerCountdown; i > 0; i--)
		{	
			server.broadcastMessage(ChatColor.RED + "Swapping in " + i +  " . . .");
			try 
			{
				TimeUnit.MILLISECONDS.sleep(990);
			} 
			catch (InterruptedException e) 
			{
				server.broadcastMessage("ERROR STARTING SWAP TIMER");
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
			Collections.shuffle(oldLocations);
		}
		for (int i = 0; i < players.size(); i++) players.get(i).teleport(oldLocations.get(i));
		
		server.broadcastMessage(ChatColor.DARK_GREEN + "Swap Complete");
	}
	
	
}
