package me.TheSteak.multiplycreeperspawn;

import org.bukkit.plugin.java.JavaPlugin;

/*
[20:22:56] [Server thread/WARN]: Initializing Legacy Material Support. Unless you have legacy plugins and/or data this is a bug!
[20:23:03] [Server thread/WARN]: Legacy plugin CreeperSpawn v1.0.0 does not specify an api-version.
[20:23:03] [Server thread/ERROR]: Could not load 'plugins\CreeperSpawnRandom.jar' in folder 'plugins'
org.bukkit.plugin.InvalidPluginException: Cannot find main class `me.TheSteak.multiplyscreeperspawn.Main'
        at org.bukkit.plugin.java.PluginClassLoader.<init>(PluginClassLoader.java:66) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.plugin.java.JavaPluginLoader.loadPlugin(JavaPluginLoader.java:133) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.plugin.SimplePluginManager.loadPlugin(SimplePluginManager.java:393) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.plugin.SimplePluginManager.loadPlugins(SimplePluginManager.java:301) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.craftbukkit.v1_16_R3.CraftServer.loadPlugins(CraftServer.java:381) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at net.minecraft.server.v1_16_R3.DedicatedServer.init(DedicatedServer.java:179) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at net.minecraft.server.v1_16_R3.MinecraftServer.w(MinecraftServer.java:786) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at net.minecraft.server.v1_16_R3.MinecraftServer.lambda$0(MinecraftServer.java:155) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at java.lang.Thread.run(Thread.java:832) [?:?]
Caused by: java.lang.ClassNotFoundException: me.TheSteak.multiplyscreeperspawn.Main
        at java.net.URLClassLoader.findClass(URLClassLoader.java:435) ~[?:?]
        at org.bukkit.plugin.java.PluginClassLoader.findClass(PluginClassLoader.java:167) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at org.bukkit.plugin.java.PluginClassLoader.findClass(PluginClassLoader.java:96) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        at java.lang.ClassLoader.loadClass(ClassLoader.java:589) ~[?:?]
        at java.lang.ClassLoader.loadClass(ClassLoader.java:522) ~[?:?]
        at java.lang.Class.forName0(Native Method) ~[?:?]
        at java.lang.Class.forName(Class.java:468) ~[?:?]
        at org.bukkit.plugin.java.PluginClassLoader.<init>(PluginClassLoader.java:64) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
        ... 8 more
*/

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new CreeperCommand(this);
	}
}
