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
 * This is the PongServer's main thread, which accepts connections from players and starts
 * a new GameControllerThread each time two players are accepted.
 *
 * @author Jendoliver
 *
 */
public class Matchmaker
{
	private static final Logger logger = LoggerFactory.getLogger(GameControllerThread.class);

	private static Queue<Player> playerQueue; // CHECK can this be filled by another thread?
	private static final int DDOS_HALT = 1000;

	public static void main(String[] args)
	{
		playerQueue = new LinkedList<>();
		logger.info("Server is running");

		try (ServerSocket serverSocket = new ServerSocket(ApplicationProperties.getServerSocketPort()))
		{
			while (playerQueue.size() < DDOS_HALT)
			{
				SocketConnection firstPlayerListener = new SocketConnection(serverSocket.accept());
				//String usernamePlayer1 = firstPlayerListener.readLine();
				String usernamePlayer1 = "kabronidas";
				PlayerConnection firstPlayerConnection = new PlayerConnection(
						new Player(usernamePlayer1), firstPlayerListener.getSocket());
				firstPlayerConnection.write("*** Waiting for another player to join... ***");

				SocketConnection secondPlayerListener = new SocketConnection(serverSocket.accept());
				//String usernamePlayer2 = secondPlayerListener.readLine();
				String usernamePlayer2 = "kakuno";
				PlayerConnection secondPlayerConnection = new PlayerConnection(
						new Player(usernamePlayer2), secondPlayerListener.getSocket());
				firstPlayerConnection.write("*** GAME FOUND! ***");
				secondPlayerConnection.write("*** GAME FOUND! ***");

				new Thread(new GameControllerThread(firstPlayerConnection, secondPlayerConnection)).start();
			}
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
		}
	}
}
