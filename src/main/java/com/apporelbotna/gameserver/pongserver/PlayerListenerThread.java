package com.apporelbotna.gameserver.pongserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.apporelbotna.gameserver.pongserver.stubs.Player;

public class PlayerListenerThread implements Runnable
{
	public static class MessageReceivedEvent extends Observable
	{
		public void notifyNewMessage(Player player, String message)
		{
			notifyObservers(new PlayerMessage(player, message));
		}
	}

	private MessageReceivedEvent messageReceivedEvent;
	private PlayerConnection playerConnection;

	private boolean readyToClose;

	public PlayerListenerThread(PlayerConnection playerConnection)
	{
		this.playerConnection = playerConnection;
		messageReceivedEvent = new MessageReceivedEvent();
	}

	public PlayerListenerThread(PlayerConnection playerConnection, Observer newMsgObserver)
	{
		this.playerConnection = playerConnection;
		messageReceivedEvent = new MessageReceivedEvent();
		messageReceivedEvent.addObserver(newMsgObserver);
	}

	public Player getPlayer()
	{
		return playerConnection.getPlayer();
	}

	@Override
	public void run()
	{
		try(BufferedReader reader = playerConnection.getReader())
		{
			while ( ! readyToClose)
			{
				String playerMessage = reader.readLine();
				messageReceivedEvent.notifyNewMessage(playerConnection.getPlayer(), playerMessage);
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
