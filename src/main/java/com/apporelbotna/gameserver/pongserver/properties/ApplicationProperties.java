package com.apporelbotna.gameserver.pongserver.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apporelbotna.gameserver.pongserver.model.GameControllerThread;

public final class ApplicationProperties
{
	private static final Logger logger = LoggerFactory.getLogger(GameControllerThread.class);

	private static String version;
	private static String name;

	private static String serverIp;
	private static int serverSocketPort;
	private static int serverMaxPlayers;

	private ApplicationProperties()
	{
		throw new UnsupportedOperationException("ApplicationProperties must not be instantiated!");
	}

	static
	{
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String applicationPropertiesPath = rootPath + "com\\apporelbotna\\gameserver\\pongserver\\properties\\application.properties";

		Properties applicationProperties = new Properties();
		try
		{
			applicationProperties.load(new FileInputStream(applicationPropertiesPath));
			version = applicationProperties.getProperty("version");
			name = applicationProperties.getProperty("name");

			serverIp = applicationProperties.getProperty("server.ip");
			serverSocketPort = Integer.valueOf(applicationProperties.getProperty("server.socketport"));
			serverMaxPlayers = Integer.valueOf(applicationProperties.getProperty("server.maxplayers"));
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
		}
	}

	public static String getVersion()
	{
		return version;
	}

	public static String getName()
	{
		return name;
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
