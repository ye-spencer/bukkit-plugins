package me.TheSteak.multiplycreeperspawn;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * [21:14:47] [Server thread/ERROR]: Could not load 'plugins\CreeperSpawnRandom.jar' in folder 'plugins'
org.bukkit.plugin.InvalidDescriptionException: Invalid plugin.yml
        at org.bukkit.plugin.java.JavaPluginLoader.getPluginDescription(JavaPluginLoader.java:168) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.plugin.SimplePluginManager.loadPlugins(SimplePluginManager.java:144) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.craftbukkit.v1_16_R3.CraftServer.loadPlugins(CraftServer.java:381) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at net.minecraft.server.v1_16_R3.DedicatedServer.init(DedicatedServer.java:179) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at net.minecraft.server.v1_16_R3.MinecraftServer.w(MinecraftServer.java:786) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at net.minecraft.server.v1_16_R3.MinecraftServer.lambda$0(MinecraftServer.java:155) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at java.lang.Thread.run(Thread.java:832) [?:?]
Caused by: org.yaml.snakeyaml.scanner.ScannerException: while scanning for the next token
found character '\t(TAB)' that cannot start any token. (Do not use \t(TAB) for indentation)
 in 'reader', line 11, column 1:

    ^
 */

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new CreeperCommand(this);
	}
}
