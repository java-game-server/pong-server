package com.apporelbotna.gameserver.pongserver.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;

import lombok.Getter;
import lombok.extern.java.Log;

@Log
public final class ApplicationProperties
{
	@Getter private static String version;
	@Getter private static String name;

	@Getter private static String serverIp;
	@Getter private static int serverSocketPort;
	@Getter private static int serverMaxPlayers;

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
			log.log(Level.FINER, Arrays.toString(e.getStackTrace()), e);
		}
	}
}
