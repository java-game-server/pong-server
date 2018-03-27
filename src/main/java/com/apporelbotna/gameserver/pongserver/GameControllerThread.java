package com.apporelbotna.gameserver.pongserver;

import java.util.Observable;
import java.util.Observer;

import com.apporelbotna.gameserver.pongserver.stubs.PongGame;

public class GameControllerThread implements Runnable, Observer
{
	private PlayerListenerThread playerListener1;
	private PlayerListenerThread playerListener2;
	private PongGame pongGame;
	// private GameDAO gameDAO;

	public GameControllerThread(PlayerConnection playerConn1, PlayerConnection playerConn2)
	{
		pongGame = new PongGame(playerConn1.getPlayer(), playerConn2.getPlayer());
		playerListener1 = new PlayerListenerThread(playerConn1, this);
		playerListener2 = new PlayerListenerThread(playerConn2, this);
	}

	@Override
	public void run()
	{
		new Thread(playerListener1).start();
		new Thread(playerListener2).start();

		while( ! pongGame.hasGameEnded())
		{

		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o.getClass().equals(PlayerListenerThread.MessageReceivedEvent.class)) // On player intent
		{
			PlayerMessage playerMessage = (PlayerMessage) arg;
			if(playerMessage.getMessage().equals(PlayerMessage.GO_UP))
				playerMessage.getPlayer().setGoingUp(true);
			else if(playerMessage.getMessage().equals(PlayerMessage.GO_DOWN))
				playerMessage.getPlayer().setGoingUp(false);
		}
	}
}
