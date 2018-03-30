package com.apporelbotna.gameserver.pongserver.stubs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.google.gson.annotations.Expose;

public class Ball implements Drawable
{
	public static final int BALL_RADIUS = 15;
	public static final int INITIAL_X = 400; // TODO sensiblot
	public static final int INITIAL_Y = 400; // TODO sensiblot

	private final int goalMargin;

	@Expose private Vector2 position;
	private Vector2 velocity;
	private Color color;

	public Ball()
	{
		goalMargin = PlayerPawn.MARGIN_LEFT_RIGHT + PlayerPawn.BAR_WIDTH;
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

	public boolean isAboutToEnterPlayer1Area()
	{
		return position.X - 1 <= goalMargin;
	}

	public boolean isAboutToEnterPlayer2Area()
	{
		return position.X + 1 >= PongGame.WINDOW_WIDTH - goalMargin;
	}

	public boolean isAboutToEnterGoalArea()
	{
		return isAboutToEnterPlayer1Area() || isAboutToEnterPlayer2Area();
	}

	public boolean collidesWith(PlayerPawn playerPawn)
	{
		return position.Y >= playerPawn.getPosition()
				&& position.Y <= playerPawn.getPosition() + PlayerPawn.BAR_HEIGHT;
	}

	public void move()
	{
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

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
