package me.TheSteak.carpetbomb;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CarpombCommand implements CommandExecutor 
{
	private Main plugin;
	
	public CarpombCommand (Main in)
	{
		plugin = in;
		plugin.getCommand("carpetbomb").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] others) 
	{
		Player p = (Player) sender;
		Location loc = p.getLocation();
		loc.add(-4, 1, 0);
		World w = p.getWorld();
		for (int i = 0; i <= 4; ++i)
		{
			loc.add(1, 0, 0);
			w.strikeLightning(loc);
			w.spawnEntity(loc, EntityType.MINECART_TNT);
		}
		return false;
	} 

}
