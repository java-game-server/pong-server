package com.apporelbotna.gameserver.pongserver;

import com.apporelbotna.gameserver.pongserver.stubs.Player;

public class PlayerMessage
{
	public static final String GO_UP = "1";
	public static final String GO_DOWN = "0";

	private Player player;
	private String message;

	public PlayerMessage(Player player, String message)
	{
		this.player = player;
		this.message = message;
	}

	public Player getPlayer()
	{
		return player;
	}

	public String getMessage()
	{
		return message;
	}
}
