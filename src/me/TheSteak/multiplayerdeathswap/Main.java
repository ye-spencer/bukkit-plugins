package me.TheSteak.multiplayerdeathswap;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new MultiplayerDeathSwap(this);
	}
}