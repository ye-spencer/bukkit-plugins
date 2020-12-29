package me.TheSteak.carpetbomb;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

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
		World w = p.getWorld();
		for (int i = 0; i <= 4; ++i)
		{
			w.strikeLightning(p.getTargetBlock(null, 50).getLocation().add(0, 1, 0));
			Entity tnt = p.getWorld().spawn(p.getTargetBlock(null, 50).getLocation().add(0, 1, 0), TNTPrimed.class);
            ((TNTPrimed)tnt).setFuseTicks(200);
		}
		return false;
	} 

}
