package me.TheSteak.multiplycreeperspawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreeperCommand implements CommandExecutor
{

	private Main plugin;
	
	public CreeperCommand(Main in)
	{
		this.plugin = in;
		plugin.getCommand("creepspawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		return false;
	}
	
}
