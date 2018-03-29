package com.apporelbotna.gameserver.pongserver.stubs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observer;
import java.util.Random;

import com.google.gson.annotations.Expose;

public class Ball implements Drawable
{
	public static final int BALL_RADIUS = 15;
	public static final int INITIAL_X = 400; // TODO sensiblot
	public static final int INITIAL_Y = 400; // TODO sensiblot

	@Expose private Vector2 position;
	private Vector2 velocity;
	private GoalEvent goalEvent = new GoalEvent();
	private Color color;

	public Ball()
	{
		spawnAtCenter();
	}

	public Ball(Observer goalObserver)
	{
		addGoalObserver(goalObserver);
		spawnAtCenter();
	}

	public void spawnAtCenter()
	{
		color = Color.YELLOW;
		Random random = new Random();
		this.position = new Vector2(INITIAL_X, INITIAL_Y);

		int initialVelocityX = random.nextInt(2) - 1;
		while(initialVelocityX == 0)
			initialVelocityX = random.nextInt(2) - 1;
		int initialVelocityY = random.nextInt(2) - 1;
		while(initialVelocityY == 0)
			initialVelocityY = random.nextInt(2) - 1;
		this.velocity = new Vector2(initialVelocityX, initialVelocityY);
	}

	public void move()
	{
		// if pos goes out of X range
			// if ball collides with player pawn
				// velocity.X = -velocity.X
			// else
				// GOAL EVENT
		if(position.Y <= 0 || position.Y + BALL_RADIUS >= PongGame.WINDOW_HEIGHT)
			velocity.Y = -velocity.Y;
		position.X += velocity.X;
		position.Y += velocity.Y;
	}


	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(position.X, position.Y, BALL_RADIUS, BALL_RADIUS);
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

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
