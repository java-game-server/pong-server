package com.apporelbotna.gameserver.pongserver.stubs.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PongGame
{
	private static final Logger logger = LoggerFactory.getLogger(PongGame.class);
	private static final long MS_BETWEEN_PHYSICS_UPDATE = 5;

	public static final int WINDOW_WIDTH = 1080; // TODO give sensible value
	public static final int WINDOW_HEIGHT = 800; // TODO give sensible value

	public static final int GOALS_TO_WIN = 5;

	private Player player1;
	private Player player2;
	private Ball ball;

	private long lastTick;

	private Player winner = null;
	private boolean terminateGame;

	public PongGame(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		ball = new Ball();
		lastTick = System.currentTimeMillis();
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public Ball getBall()
	{
		return ball;
	}

	public void setBall(Ball ball)
	{
		this.ball = ball;
	}

	public Player getWinner()
	{
		return winner;
	}

	public void updatePhysics()
	{
		long deltaTime = System.currentTimeMillis() - lastTick;
		lastTick = System.currentTimeMillis();
		if(deltaTime < MS_BETWEEN_PHYSICS_UPDATE) return;

		try
		{
			Thread.sleep(MS_BETWEEN_PHYSICS_UPDATE);
		}
		catch (InterruptedException e)
		{
			terminateGame = true;
			logger.error(e.getMessage());
			Thread.currentThread().interrupt();
		}

		player1.move();
		player2.move();

		if(ball.isAboutToEnterGoalArea())
		{
			Player collidingPlayer = ball.collidesWithAny(player1, player2);
			if(collidingPlayer != null)
				ball.getVelocity().X = -ball.getVelocity().X;
			else
			{
				if(ball.isAboutToEnterPlayer1Area())
					player2.addGoal();
				else
					player1.addGoal();
				if(player1.hasWon())
				{
					winner = player1;
				}
				winner = player2.hasWon() ? player2 : null;
				ball.spawnAtCenter();
			}
		}
		ball.move();
	}

	public boolean hasGameEnded()
	{
		return winner != null || wasGameTerminated();
	}

	public boolean wasGameTerminated()
	{
		return terminateGame;
	}

	public void setPlayerPawn1GoingUp(boolean isGoingUp)
	{
		player1.getPawn().setGoingUp(isGoingUp);
	}

	public void setPlayerPawn2GoingUp(boolean isGoingUp)
	{
		player2.getPawn().setGoingUp(isGoingUp);
	}
}
