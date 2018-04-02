package com.apporelbotna.gameserver.pongserver.model;

import java.io.IOException;
import java.net.Socket;

import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.model.PongGame;
import com.apporelbotna.gameserver.pongserver.stubs.net.GameStatusMessage;
import com.apporelbotna.gameserver.pongserver.stubs.net.SocketConnection;

/**
 * This class provides a socket communication interface with a player.
 *
 * @author Jendoliver
 *
 */
public class PlayerConnection extends SocketConnection
{
	private Player player;

	public PlayerConnection(Player player, Socket socket) throws IOException
	{
		super(socket);
		this.player = player;
	}

	public boolean sendGameInfo(PongGame pongGame)
	{
		return send(new GameStatusMessage(pongGame, player));
	}

	public Player getPlayer()
	{
		return player;
	}
}
