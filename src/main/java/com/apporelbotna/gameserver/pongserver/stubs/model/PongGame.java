package com.apporelbotna.gameserver.pongserver.stubs.model;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PongGame
{
	private static final Logger logger = LoggerFactory.getLogger(PongGame.class);
	private static final long MS_BETWEEN_PHYSICS_UPDATE = 3;

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	public static final int GOALS_TO_WIN = 5;

	private Player player1;
	private Player player2;
	private Ball ball;

	private long beginPlayTime;
	// private long lastTick; TODO use instead of Thread.sleep OR SEE TIMER CLASS BETTER :)

	private Player winner = null;
	private boolean terminateGame;

	public PongGame(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		ball = new Ball();
		beginPlayTime = System.currentTimeMillis();
		// lastTick = beginPlayTime;
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

	public long getGameTimeInMillis()
	{
		return System.currentTimeMillis() - beginPlayTime;
	}

	public void updatePhysics()
	{
		// TODO implement
//		long now = System.currentTimeMillis();
//		long deltaTime = now - lastTick;
//		lastTick = now;
//		if (deltaTime < MS_BETWEEN_PHYSICS_UPDATE)
//			return;

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
		checkForGoalAndUpdate();
		ball.move();
	}

	private void checkForGoalAndUpdate()
	{
		Optional<Player> player = checkGoal();
		if(player.isPresent())
		{
			player.get().addGoal();
			winner = checkWinner();
			ball.spawnAtCenter();
		}
		else
			ball.getVelocity().X = -ball.getVelocity().X;
	}

	public Optional<Player> checkGoal()
	{
		if(ball.isAboutToEnterPlayer1Area())
			return Optional.of(player2);
		else if(ball.isAboutToEnterPlayer2Area())
			return Optional.of(player1);
		return Optional.ofNullable(null);
	}

	public Player checkWinner()
	{
		if (player1.hasWon())
			return player1;
		return player2.hasWon() ? player2 : null;
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
