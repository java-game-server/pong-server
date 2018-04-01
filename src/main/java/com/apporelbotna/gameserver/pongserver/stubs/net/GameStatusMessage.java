package com.apporelbotna.gameserver.pongserver.stubs.net;

import com.apporelbotna.gameserver.pongserver.stubs.model.Ball;
import com.apporelbotna.gameserver.pongserver.stubs.model.Player;
import com.apporelbotna.gameserver.pongserver.stubs.net.gson.AnnotationExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class GameStatusMessage implements Message
{
	private boolean gameFinished;
	private Ball ball;
	private Player player;
	private Player enemy;

	public GameStatusMessage(
			boolean gameFinished,
			Ball ball,
			Player player,
			Player enemy)
	{
		this.gameFinished = gameFinished;
		this.ball = ball;
		this.player = player;
		this.enemy = enemy;
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
		Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy())
				.create();
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
