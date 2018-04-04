package com.apporelbotna.gameserver.pongserver.model;

public interface Matchmaker
{
	boolean canAcceptPlayers();
	PlayerConnection acceptPlayer();
	Thread createMatch(PlayerConnection playerConnection, PlayerConnection playerConnection2);
}
