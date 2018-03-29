package com.apporelbotna.gameserver.pongserver;

import java.io.IOException;
import java.net.Socket;

import com.apporelbotna.gameserver.pongserver.stubs.Ball;
import com.apporelbotna.gameserver.pongserver.stubs.Player;
import com.apporelbotna.gameserver.pongserver.stubs.ServerMessage;
import com.apporelbotna.gameserver.pongserver.stubs.SocketConnection;

public class PlayerConnection extends SocketConnection
{
	private Player player;

	public PlayerConnection(Player player, Socket socket) throws IOException
	{
		super(socket);
		this.player = player;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void sendGameInfo(Ball ballPosition, Player enemyPosition)
	{
		new ServerMessage(this, ballPosition, enemyPosition).send();
	}
}
