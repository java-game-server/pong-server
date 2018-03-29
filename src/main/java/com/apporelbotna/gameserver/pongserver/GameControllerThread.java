package com.apporelbotna.gameserver.pongserver;

import java.util.Observable;
import java.util.Observer;

import com.apporelbotna.gameserver.pongserver.stubs.PongGame;

public class GameControllerThread implements Runnable, Observer
{
	private static final long MS_BETWEEN_PHYSICS_UPDATE = 50;

	private PlayerCommunicationThread playerCommunicationThread1;
	private PlayerCommunicationThread playerCommunicationThread2;
	private PongGame pongGame;
	// private GameDAO gameDAO;

	public GameControllerThread(PlayerConnection playerConn1, PlayerConnection playerConn2)
	{
		pongGame = new PongGame(playerConn1.getPlayer(), playerConn2.getPlayer());
		playerCommunicationThread1 = new PlayerCommunicationThread(playerConn1, this);
		playerCommunicationThread2 = new PlayerCommunicationThread(playerConn2, this);
	}

	@Override
	public void run()
	{
		new Thread(playerCommunicationThread1).start();
		new Thread(playerCommunicationThread2).start();

		while( ! pongGame.hasGameEnded())
		{
			// FIXME esto pegara un pedo brutal porque el thread esta bloqueado escuchando pero wenooo SYYY??? ajajjajj
			try
			{
				Thread.sleep(MS_BETWEEN_PHYSICS_UPDATE);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			pongGame.updatePhysics();
			playerCommunicationThread1.sendGameInfo(pongGame.getBall(), pongGame.getPlayer2());
			playerCommunicationThread2.sendGameInfo(pongGame.getBall(), pongGame.getPlayer1());
		}

		// Actualizar bbdd con ganador (GameDAO) y demas mierdas

	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o.getClass().equals(PlayerCommunicationThread.MessageReceivedEvent.class)) // On player intent
		{
			PlayerMessage playerMessage = (PlayerMessage) arg;
			if(playerMessage.getMessage().equals(PlayerMessage.Message.GO_UP))
				playerMessage.getPlayer().setGoingUp(true);
			else if(playerMessage.getMessage().equals(PlayerMessage.Message.GO_DOWN))
				playerMessage.getPlayer().setGoingUp(false);
		}
	}
}
