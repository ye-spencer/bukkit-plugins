package me.TheSteak.idiotdown;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class IdiotDown implements Listener
{
	public void onDeath(PlayerDeathEvent event) 
	{
		if (event.getEntity().getName().equals("C_Salt"))
			event.setDeathMessage(ChatColor.DARK_RED + "WARNING: retard down");
	}
	
}
