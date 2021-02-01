package me.TheSteak.footballgame;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GameMechanics implements CommandExecutor, Listener
{
	
	private Main plugin;
	private Server server;
	private boolean enabled;
	
	public GameMechanics (Main in)
	{
		enabled = false;
		plugin = in;
		
		server = plugin.getServer();
		
		plugin.getCommand("startfootballgame").setExecutor(this);
		plugin.getCommand("endfootballgame").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] others) 
	{
		if (command.getName() == "startfootballgame")
		{
			enabled = true;
			return true;
		}
		else if (command.getName() == "endfootballgame")
		{
			enabled = false;
			return true;
		}
		return false;
	}
	
	@EventHandler
    public void onHit(EntityDamageEvent event)
	{
		if (enabled)
		{
			if (event.getEntity() instanceof Player)
			{
				//TODO deal with what happens on hit
			}
        }
    }

}