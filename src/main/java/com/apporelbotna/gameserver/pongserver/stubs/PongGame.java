package com.apporelbotna.gameserver.pongserver.stubs;

public class PongGame
{
	public static final int WINDOW_WIDTH = 1080; // TODO give sensible value
	public static final int WINDOW_HEIGHT = 800; // TODO give sensible value

	public static final int GOALS_TO_WIN = 5;

	private Player player1;
	private Player player2;
	private Ball ball;

	private Player winner = null;

	public PongGame(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		ball = new Ball();
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
				ball.spawnAtCenter();
			}
		}
		ball.move();
	}

	public boolean hasGameEnded()
	{
		return winner != null;
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
