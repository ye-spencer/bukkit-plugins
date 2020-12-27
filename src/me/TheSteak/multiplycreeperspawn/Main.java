package me.TheSteak.multiplycreeperspawn;

import org.bukkit.plugin.java.JavaPlugin;

/*
[21:42:37] [Server thread/WARN]: Legacy plugin CreeperSpawn v1.0.0 does not specify an api-version.
[21:42:37] [Server thread/INFO]: [CreeperSpawn] Loading CreeperSpawn v1.0.0
*/

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new CreeperCommand(this);
	}
}
