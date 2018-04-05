package com.apporelbotna.gameserver.pongserver.service;

import com.apporelbotna.gameserver.persistencewsclient.GameDAO;
import com.apporelbotna.gameserver.pongserver.properties.ApplicationProperties;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.model.PongGame;
import com.apporelbotna.gameserver.stubs.Match;

public class PongGameService
{
	private GameDAO gameDAO;
	private static PongGameService instance;

	private PongGameService(GameDAO gameDAO)
	{
		this.gameDAO = gameDAO;
	}

	public static synchronized PongGameService getInstance()
	{
		if (instance == null)
		{
			instance = new PongGameService(new GameDAO());
		}
		return instance;
	}

	public synchronized void uploadMatchToDatabase(PongGame pongGame)
	{
		long gameTime = pongGame.getGameTimeInMillis();
		Player player1 = pongGame.getPlayer1();
		Player player2 = pongGame.getPlayer2();

		gameDAO.finishMatch(
				new Match(
					player1.getEmail(),
					ApplicationProperties.getGameId(),
					gameTime,
					player1.getGoals()),

				new Match(
						player2.getEmail(),
						ApplicationProperties.getGameId(),
						gameTime,
						player2.getGoals())
		);
	}
}
