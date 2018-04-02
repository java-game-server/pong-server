package com.apporelbotna.gameserver.pongserver.model;

import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.net.PlayerMovementMessage;

/**
 * A simple struct representing a message from a player. It holds the sender player and the
 * message, which can be GO_UP (1) or GO_DOWN (0)
 *
 * @author Jendoliver
 *
 */
public class PlayerMessage
{
	private Player player;
	private PlayerMovementMessage message;

	public PlayerMessage(Player player, PlayerMovementMessage message)
	{
		this.player = player;
		this.message = message;
	}

	public Player getPlayer()
	{
		return player;
	}

	public PlayerMovementMessage getMessage()
	{
		return message;
	}
}
