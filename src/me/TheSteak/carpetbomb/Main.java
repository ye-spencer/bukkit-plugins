package me.TheSteak.carpetbomb;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new CarpombCommand(this);
	}
}