package me.TheSteak.multiteammanhunt;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * okay im not too sure on how these work, but i think once the plugin in loaded, it runs the main class, which creates all the other classes, which stay and wait for commands to be run,
 * or anything else to happen
 * 
 * Location l=target.getLocation();
player.setCompassTarget(l);
 */

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new MainhuntCompassCommand(this);
	}
}
