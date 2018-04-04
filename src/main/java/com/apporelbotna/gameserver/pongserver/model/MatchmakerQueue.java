package com.apporelbotna.gameserver.pongserver.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apporelbotna.gameserver.pongserver.properties.ApplicationProperties;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.net.SocketConnection;

/**
 * This is the PongServer's main thread, which accepts connections from players and starts a new
 * GameControllerThread each time two players are accepted.
 *
 * @author Jendoliver
 *
 */
public class MatchmakerQueue implements Matchmaker
{
	private static final Logger logger = LoggerFactory.getLogger(MatchmakerQueue.class);

	private static Queue<Player> playerQueue; // CHECK can this be filled by another thread?
	private static final int DDOS_HALT = 1000;

	private static ServerSocket serverSocket;

	public MatchmakerQueue()
	{
		try
		{
			serverSocket = new ServerSocket(ApplicationProperties.getServerSocketPort());
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
		}
		playerQueue = new LinkedList<>();
		logger.info("Server is running");
	}

	@Override
	public boolean canAcceptPlayers()
	{
		return playerQueue.size() < DDOS_HALT;
	}

	@Override
	public PlayerConnection acceptPlayer()
	{
		SocketConnection playerListener;
		try
		{
			playerListener = new SocketConnection(serverSocket.accept());
			// String playerEmail = playerListener.readLine(); TODO replace
			// String playerToken = playerListener.readLine();
			String playerUsername = "kabronidas"; String playerToken = "11111";
			return new PlayerConnection(new Player(playerUsername, playerToken), playerListener);
		}
		catch (IOException e)
		{
			logger.error("Failed to properly open a connection");
		}
		return null;
	}

	@Override
	public Thread createMatch(PlayerConnection playerConnection, PlayerConnection playerConnection2)
	{
		Thread match = new Thread(new GameControllerThread(playerConnection, playerConnection2));
		match.start();
		return match;
	}
}
