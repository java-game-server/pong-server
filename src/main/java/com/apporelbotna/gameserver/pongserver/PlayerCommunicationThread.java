package com.apporelbotna.gameserver.pongserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.apporelbotna.gameserver.pongserver.PlayerMessage.Message;
import com.apporelbotna.gameserver.pongserver.stubs.Ball;
import com.apporelbotna.gameserver.pongserver.stubs.Player;

public class PlayerCommunicationThread implements Runnable
{
	public static class MessageReceivedEvent extends Observable
	{
		public void notifyNewMessage(Player player, Message message)
		{
			notifyObservers(new PlayerMessage(player, message));
		}
	}

	private MessageReceivedEvent messageReceivedEvent;
	private PlayerConnection playerConnection;

	private boolean readyToClose;

	public PlayerCommunicationThread(PlayerConnection playerConnection)
	{
		this.playerConnection = playerConnection;
		messageReceivedEvent = new MessageReceivedEvent();
	}

	public PlayerCommunicationThread(PlayerConnection playerConnection, Observer newMsgObserver)
	{
		this.playerConnection = playerConnection;
		messageReceivedEvent = new MessageReceivedEvent();
		messageReceivedEvent.addObserver(newMsgObserver);
	}

	public Player getPlayer()
	{
		return playerConnection.getPlayer();
	}

	public void sendGameInfo(Ball ballPosition, Player enemyPosition)
	{
		playerConnection.sendGameInfo(ballPosition, enemyPosition);
	}

	@Override
	public void run()
	{
		try(BufferedReader reader = playerConnection.getReader())
		{
			while ( ! readyToClose)
			{
				String playerMessage = reader.readLine();
				messageReceivedEvent.notifyNewMessage(
						playerConnection.getPlayer(),
						Message.fromStringCode(playerMessage)
				);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void close()
	{
		readyToClose = true;
		playerConnection.close();
	}
}
