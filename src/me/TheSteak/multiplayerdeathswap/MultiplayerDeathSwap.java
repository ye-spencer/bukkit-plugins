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

/**
 * 
 * @author TheSteak
 *
 */
public class MultiplayerDeathSwap implements CommandExecutor, Listener
{
	private Server server;
	
	private ArrayList<Player> players;
	
	private boolean gameStarted;
	
	private final int minutesVary = 3, minutesMin = 2, secondsPerCountdown = 10, secondsPerMinute = 60;
	
	public MultiplayerDeathSwap(Main plugin)
	{		
		server = plugin.getServer();
		plugin.getCommand("joindeathswap").setExecutor(this);
		plugin.getCommand("leavedeathswap").setExecutor(this);
		plugin.getCommand("startdeathswap").setExecutor(this);
		
		gameStarted = true;
	}
	
	
	public void onDeath(PlayerDeathEvent event) 
	{
		Player p = event.getEntity();
		if (gameStarted && players.remove(p))
		{
			if (players.size() == 1)
			{
				server.broadcastMessage("The winner of death swap is " + ChatColor.GOLD + p.getName());
				gameStarted = false;
			}
			else if (players.size() == 0)
			{
				server.broadcastMessage("No winner, everyone died");
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
				server.broadcastMessage("Deathswap has begun!");
				startGame();
			}
		}
		return false;
	}
	
	private void startGame()
	{
		while (players.size() > 1) { newRound(); }
	}
	
	private void newRound()
	{
		try 
		{
			TimeUnit.SECONDS.sleep((long) (Math.random() * (minutesVary * secondsPerMinute) + (minutesMin * secondsPerMinute)));
			run();
		} 
		catch (InterruptedException e) { e.printStackTrace(); }
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
			server.broadcastMessage(ChatColor.RED + "Swapping in " + i +  "seconds ...");
			try  
			{
				TimeUnit.MILLISECONDS.sleep(997);
			} 
			catch (InterruptedException error) 
			{
				server.broadcastMessage("ERROR WITH TIMER");
			}
		}
		server.broadcastMessage(ChatColor.RED + "Swapping. . . ");
		ArrayList<Location> oldLocations = new ArrayList<Location>(), temp = new ArrayList<Location>();
		for (Player p : players)
		{
			oldLocations.add(p.getLocation());
			temp.add(p.getLocation());
		}
		do { Collections.shuffle(oldLocations); }
		while (!properlyShifted(temp, oldLocations));
		
		for (int i = 0; i < players.size(); i++) players.get(i).teleport(oldLocations.get(i));
		
		server.broadcastMessage(ChatColor.DARK_GREEN + "SWAP COMPLETE");
	}
	
	
}
