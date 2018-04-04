package com.apporelbotna.gameserver.pongserver;

import com.apporelbotna.gameserver.pongserver.model.Matchmaker;
import com.apporelbotna.gameserver.pongserver.model.MatchmakerQueue;
import com.apporelbotna.gameserver.pongserver.model.PlayerConnection;

public class Main
{
	public static void main(String[] args)
	{
		Matchmaker matchmaker = new MatchmakerQueue();
		// PongGameService.create(new GameDAO()); TODO uncomment when client is ready

		while(matchmaker.canAcceptPlayers())
		{
			PlayerConnection playerConnection = matchmaker.acceptPlayer();
			PlayerConnection playerConnection2 = matchmaker.acceptPlayer();
			matchmaker.createMatch(playerConnection, playerConnection2);
		}
	}
}
