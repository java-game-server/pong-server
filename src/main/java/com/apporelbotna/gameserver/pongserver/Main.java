package com.apporelbotna.gameserver.pongserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apporelbotna.gameserver.pongserver.model.Matchmaker;
import com.apporelbotna.gameserver.pongserver.model.MatchmakerQueue;
import com.apporelbotna.gameserver.pongserver.model.PlayerConnection;

public class Main
{
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args)
	{
		Matchmaker matchmaker = new MatchmakerQueue();

		while (matchmaker.canAcceptPlayers())
		{
			PlayerConnection playerConnection = matchmaker.acceptPlayer();
			logger.debug(String.format("PLAYER ACCEPTED: %s", playerConnection.getPlayer().getName()));
			PlayerConnection playerConnection2 = matchmaker.acceptPlayer();
			logger.debug(String.format("PLAYER ACCEPTED: %s", playerConnection2.getPlayer().getName()));
			matchmaker.createMatch(playerConnection, playerConnection2);
			logger.debug(String.format("Match created: %s VS %s",
					playerConnection.getPlayer().getName(),
					playerConnection2.getPlayer().getName()));
		}
	}
}
