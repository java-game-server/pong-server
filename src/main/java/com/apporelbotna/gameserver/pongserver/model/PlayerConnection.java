package com.apporelbotna.gameserver.pongserver.model;

import java.io.IOException;
import java.net.Socket;

import com.apporelbotna.gameserver.pongserver.stubs.model.Ball;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.net.GameStatusMessage;
import com.apporelbotna.gameserver.pongserver.stubs.net.SocketConnection;

import lombok.Getter;

/**
 * This class provides a socket communication interface with a player.
 *
 * @author Jendoliver
 *
 */
public class PlayerConnection extends SocketConnection
{
	@Getter private Player player;

	public PlayerConnection(Player player, Socket socket) throws IOException
	{
		super(socket);
		this.player = player;
	}

	public boolean sendGameInfo(boolean hasGameEnded, Ball ballPosition, Player enemy)
	{
		return send(new GameStatusMessage(
				hasGameEnded,
				ballPosition,
				player,
				enemy));
	}
}
