package me.TheSteak.multiplycreeperspawn;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class CreeperCommand implements CommandExecutor
{

	private Main plugin;
	
	public CreeperCommand(Main in)
	{
		this.plugin = in;
		plugin.getCommand("creepspawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] others) 
	{
		Player player = (Player) sender;
		Location location = player.getLocation();
		World world = player.getWorld();
		for (int i = (int) (Math.random() * 64) + 1; i >=0; --i)
		{
			world.spawnEntity(location, EntityType.CREEPER);
		}
		return false;
	}
	
}
