package com.apporelbotna.gameserver.pongserver.stubs;

import com.apporelbotna.gameserver.pongserver.PlayerConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ServerMessage
{
	private PlayerConnection playerConnection;

	@Expose
	private boolean gameFinished;

	@Expose
	private Ball ball;

	@Expose
	private Player player;

	@Expose
	private Player enemy;

	public ServerMessage(PlayerConnection playerConnection, Ball ball, Player enemy)
	{
		this.playerConnection = playerConnection;
		this.ball = ball;
		this.player = this.playerConnection.getPlayer();
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

	public boolean send()
	{
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return playerConnection.write(gson.toJson(this));
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
