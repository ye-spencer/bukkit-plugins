package me.TheSteak.multiteammanhunt;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ManhuntCompassCommand implements CommandExecutor 
{
	
	private Main plugin;
	
	public ManhuntCompassCommand (Main in)
	{
		plugin = in;
		plugin.getCommand("manhuntcompass").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] other) 
	{
		Player p = (Player) sender;
		PlayerInventory inv = p.getInventory();
		inv.setItem(inv.firstEmpty(), new ItemStack(Material.COMPASS));
		return true;
	}
}
