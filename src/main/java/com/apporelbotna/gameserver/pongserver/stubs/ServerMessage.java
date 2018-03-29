package com.apporelbotna.gameserver.pongserver.stubs;

import java.io.IOException;

import com.apporelbotna.gameserver.pongserver.PlayerConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ServerMessage
{
	private PlayerConnection playerConnection;
	@Expose private boolean gameFinished;
	@Expose private Ball ball;
	@Expose private Player enemy;

	public ServerMessage(PlayerConnection playerConnection, Ball ball, Player enemy)
	{
		this.playerConnection = playerConnection;
		this.ball = ball;
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

	public Player getEnemy()
	{
		return enemy;
	}

	public void send()
	{
		try
		{
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			playerConnection.getWriter().write(gson.toJson(this)+"\n");
			playerConnection.getWriter().flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static ServerMessage fromJson(String json)
	{
		return new Gson().fromJson(json, ServerMessage.class);
	}

	@Override
	public String toString()
	{
		return "ServerMessage [gameFinished=" + gameFinished + ", ball=" + ball + ", enemy=" + enemy
				+ "]";
	}
}
