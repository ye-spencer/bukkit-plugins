package me.TheSteak.multiplayerdeathswap;

import java.util.ArrayList;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class MultiplayerDeathSwap implements CommandExecutor, Listener
{
	private Main plugin;
	private Server server;
	
	private ArrayList<Player> players;
	
	private boolean gameStarted;
	
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
		if (players.remove(p))
		{
			if (players.size() == 1)
			{
				//TODO WINNER
			}
			else if (players.size() == 0)
			{
				//TODO NO WINNER
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
		while(players.size() > 1)
		{
			newRound();
		}
	}
	
	private void newRound()
	{
		//timer
		//check for game end every once in a while
	}
}
