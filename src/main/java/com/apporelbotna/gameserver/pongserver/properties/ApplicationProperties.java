package com.apporelbotna.gameserver.pongserver.properties;

import java.util.ResourceBundle;

public final class ApplicationProperties
{
	private static String version;
	private static String name;

	private static int gameId;

	private static String serverIp;
	private static int serverSocketPort;
	private static int serverMaxPlayers;

	private ApplicationProperties()
	{
		throw new UnsupportedOperationException("ApplicationProperties must not be instantiated!");
	}

	static
	{
		ResourceBundle bundle = ResourceBundle
				.getBundle("com.apporelbotna.gameserver.pongserver.properties.application");

		version = bundle.getString("version");
		name = bundle.getString("name");

		gameId = Integer.valueOf(bundle.getString("game.id"));

		serverIp = bundle.getString("server.ip");
		serverSocketPort = Integer.valueOf(bundle.getString("server.socketport"));
		serverMaxPlayers = Integer.valueOf(bundle.getString("server.maxplayers"));
	}

	public static String getVersion()
	{
		return version;
	}

	public static String getName()
	{
		return name;
	}

	public static int getGameId()
	{
		return gameId;
	}

	public static String getServerIp()
	{
		return serverIp;
	}

	public static int getServerSocketPort()
	{
		return serverSocketPort;
	}

	public static int getServerMaxPlayers()
	{
		return serverMaxPlayers;
	}
}
