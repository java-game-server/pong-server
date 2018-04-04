package com.apporelbotna.gameserver.pongserver.model;

import java.util.Observable;
import java.util.Observer;

import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.model.PongGame;
import com.apporelbotna.gameserver.pongserver.stubs.net.PlayerMovementMessage;

/**
 * This class serves as a constant player message listener. It will broadcast a
 * MessageReceivedEvent when a player message is received to all of their observers.
 *
 * @author Jendoliver
 *
 */
public class PlayerCommunicationChannel implements Runnable
{
	public static class MessageReceivedEvent extends Observable
	{
		public void notifyNewMessage(Player player, PlayerMovementMessage message)
		{
			setChanged();
			notifyObservers(new PlayerMovementMessageWrapper(player, message));
		}
	}

	private MessageReceivedEvent messageReceivedEvent;
	private PlayerConnection playerConnection;

	private boolean readyToClose;

	public PlayerCommunicationChannel(PlayerConnection playerConnection)
	{
		this.playerConnection = playerConnection;
		messageReceivedEvent = new MessageReceivedEvent();
	}

	public PlayerCommunicationChannel(PlayerConnection playerConnection, Observer newMsgObserver)
	{
		this.playerConnection = playerConnection;
		messageReceivedEvent = new MessageReceivedEvent();
		messageReceivedEvent.addObserver(newMsgObserver);
	}

	public Player getPlayer()
	{
		return playerConnection.getPlayer();
	}

	public boolean sendGameInfo(PongGame pongGame)
	{
		return playerConnection.sendGameInfo(pongGame);
	}

	@Override
	public void run()
	{
		String playerMessage = playerConnection.readLine();
		while ( ! readyToClose && playerMessage != null)
		{
			messageReceivedEvent.notifyNewMessage(
					playerConnection.getPlayer(),
					PlayerMovementMessage.fromStringCode(playerMessage));
			playerMessage = playerConnection.readLine();
		}
	}

	public void close()
	{
		readyToClose = true;
		playerConnection.close();
	}
}
