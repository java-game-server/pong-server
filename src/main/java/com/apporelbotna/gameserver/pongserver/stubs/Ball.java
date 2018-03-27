package com.apporelbotna.gameserver.pongserver.stubs;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Ball extends Observable implements Drawable
{
	public static final int BALL_RADIUS = 15;
	public static final int INITIAL_X = 400; // TODO sensiblot
	public static final int INITIAL_Y = 400; // TODO sensiblot

	private Vector2 position;
	private Vector2 velocity;
	private GoalEvent goalEvent;

	public Ball()
	{
		spawnAtCenter();
	}

	public void spawnAtCenter()
	{
		Random random = new Random();
		this.position = new Vector2(INITIAL_X, INITIAL_Y);
		this.velocity = new Vector2(random.nextInt(2) - 1, random.nextInt(2) - 1);
	}

	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getVelocity()
	{
		return velocity;
	}

	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}

	public void move()
	{
		// if pos goes out of X range
			// if ball collides with player pawn
				// velocity.X = -velocity.X
			// else
				// GOAL EVENT
		if(position.Y <= 0 || position.Y >= PongGame.WINDOW_HEIGHT)
			velocity.Y = -velocity.Y;
		position.X += velocity.X;
		position.Y += velocity.Y;
	}

	public void addGoalObserver(Observer observer)
	{
		goalEvent.addObserver(observer);
	}

	public void deleteGoalObserver(Observer observer)
	{
		goalEvent.deleteObserver(observer);
	}

	public void notifyGoal(Player scoringPlayer)
	{
		goalEvent.goal(scoringPlayer);
	}

	@Override
	public void draw(Graphics g)
	{
		g.fillOval(position.X, position.Y, BALL_RADIUS, BALL_RADIUS);
	}
}
