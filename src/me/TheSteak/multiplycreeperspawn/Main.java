package me.TheSteak.multiplycreeperspawn;

import org.bukkit.plugin.java.JavaPlugin;

/*
[20:43:50 ERROR]: Could not load 'plugins\CreeperSpawnRandom.jar' in folder 'plugins'
org.bukkit.plugin.InvalidDescriptionException: Invalid plugin.yml
	at org.bukkit.plugin.java.JavaPluginLoader.getPluginDescription(JavaPluginLoader.java:168) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.bukkit.plugin.SimplePluginManager.loadPlugins(SimplePluginManager.java:144) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.bukkit.craftbukkit.v1_16_R3.CraftServer.loadPlugins(CraftServer.java:381) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at net.minecraft.server.v1_16_R3.DedicatedServer.init(DedicatedServer.java:179) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at net.minecraft.server.v1_16_R3.MinecraftServer.w(MinecraftServer.java:786) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at net.minecraft.server.v1_16_R3.MinecraftServer.lambda$0(MinecraftServer.java:155) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at java.lang.Thread.run(Thread.java:832) [?:?]
Caused by: org.yaml.snakeyaml.parser.ParserException: while parsing a block mapping
 in 'reader', line 9, column 9:
            aliases: [csr]
            ^
expected <block end>, but found '<block mapping start>'
 in 'reader', line 10, column 13:
                description: /csr to spawn betwe ... 
                ^

	at org.yaml.snakeyaml.parser.ParserImpl$ParseBlockMappingKey.produce(ParserImpl.java:572) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.parser.ParserImpl.peekEvent(ParserImpl.java:158) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.parser.ParserImpl.checkEvent(ParserImpl.java:148) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeMappingNode(Composer.java:235) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeNode(Composer.java:162) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeValueNode(Composer.java:257) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeMappingChildren(Composer.java:248) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeMappingNode(Composer.java:236) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeNode(Composer.java:162) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeValueNode(Composer.java:257) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeMappingChildren(Composer.java:248) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeMappingNode(Composer.java:236) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.composeNode(Composer.java:162) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.getNode(Composer.java:95) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.composer.Composer.getSingleNode(Composer.java:119) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.constructor.BaseConstructor.getSingleData(BaseConstructor.java:150) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.Yaml.loadFromReader(Yaml.java:472) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.yaml.snakeyaml.Yaml.load(Yaml.java:411) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.bukkit.plugin.PluginDescriptionFile.<init>(PluginDescriptionFile.java:252) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	at org.bukkit.plugin.java.JavaPluginLoader.getPluginDescription(JavaPluginLoader.java:163) ~[craftbukkit-1.16.4.jar:git-Bukkit-3eb7236]
	... 6 more
 */

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new CreeperCommand(this);
	}
}
