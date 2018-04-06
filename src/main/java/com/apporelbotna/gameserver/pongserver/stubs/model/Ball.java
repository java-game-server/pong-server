package com.apporelbotna.gameserver.pongserver.stubs.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.google.gson.annotations.Expose;

public class Ball implements Drawable
{
	public static final int BALL_RADIUS = 15;
	public static final int INITIAL_X;
	public static final int INITIAL_Y;

	private final int goalMargin;

	@Expose private Vector2 position;
	private Vector2 velocity;
	private Color color;

	static
	{
		INITIAL_X = PongGame.WINDOW_WIDTH / 2;
		INITIAL_Y = PongGame.WINDOW_HEIGHT / 2;
	}

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

		// FIXME el random no se genera bien
//		int initialVelocityX = random.nextInt(2) - 1;
//		while (initialVelocityX == 0)
//			initialVelocityX = random.nextInt(2) - 1;
//		int initialVelocityY = random.nextInt(2) - 1;
//		while (initialVelocityY == 0)
//			initialVelocityY = random.nextInt(2) - 1;
		this.velocity = new Vector2(1, 1);
	}

	public Ball mirrorPositionX()
	{
		int halfWindowWidth = PongGame.WINDOW_WIDTH / 2;
		if (position.X != halfWindowWidth)
			position.X = position.X < halfWindowWidth ? halfWindowWidth + (halfWindowWidth - position.X)
					: halfWindowWidth - (position.X - halfWindowWidth);
		return this;
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

	public Player collidesWithAny(Player player1, Player player2)
	{
		if(isAboutToEnterPlayer1Area())
			return hasYBetween(player1) ? player1 : null;
		else if(isAboutToEnterPlayer2Area())
			return hasYBetween(player2) ? player2 : null;
		return null;
	}

	public boolean hasYBetween(Player player)
	{
		return position.Y >= player.getPosition()
				&& position.Y <= player.getPosition() + PlayerPawn.BAR_HEIGHT;
	}

	public void move()
	{
		if (position.Y <= 0 || position.Y + BALL_RADIUS >= PongGame.WINDOW_HEIGHT)
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
