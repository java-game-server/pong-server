package com.apporelbotna.gameserver.pongserver.stubs.net;

import com.apporelbotna.gameserver.pongserver.stubs.model.Ball;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.model.PongGame;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

public class GameStatusMessage implements Message
{
	@Expose private boolean gameFinished;
	@Expose private Ball ball;
	@Expose private Player player;
	@Expose private Player enemy;

	public GameStatusMessage(boolean gameFinished, Ball ball, Player player, Player enemy)
	{
		this.gameFinished = gameFinished;
		this.ball = ball;
		this.player = player;
		this.enemy = enemy;
	}

	public GameStatusMessage(PongGame pongGame, Player receiver)
	{
		this.gameFinished = pongGame.hasGameEnded();
		this.ball = pongGame.getBall();
		this.player = receiver;
		this.enemy = pongGame.getPlayer1().equals(receiver) ?
				pongGame.getPlayer2()
				: pongGame.getPlayer1();
	}

	public boolean isGameFinished()
	{
		return gameFinished;
	}

	public Ball getBall()
	{
		return ball;
	}

	public Player getPlayer()
	{
		return player;
	}

	public Player getEnemy()
	{
		return enemy;
	}

	public static GameStatusMessage fromJson(String json)
	{
		return new Gson().fromJson(json, GameStatusMessage.class);
	}

	public static boolean canCreateFromJson(String json)
	{
		try
		{
			new Gson().fromJson(json, GameStatusMessage.class);
			return true;
		}
		catch (JsonSyntaxException e)
		{
			return false;
		}
	}

	@Override
	public String serialize()
	{
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}

	@Override
	public GameStatusMessage deserialize(String serializedMessage)
	{
		return fromJson(serializedMessage);
	}

	@Override
	public boolean canDeserialize(String serializedMessage)
	{
		return canCreateFromJson(serializedMessage);
	}
}
