package com.apporelbotna.gameserver.pongserver.stubs;

import java.awt.Color;
import java.awt.Graphics;

import com.google.gson.annotations.Expose;

public class PlayerPawn implements Drawable
{
	public static final int INITIAL_POSITION = 300;

	public static final int BAR_WIDTH = 10;
	public static final int BAR_HEIGHT = 100;
	public static final int MARGIN_LEFT_RIGHT = 20;

	@Expose private int position; /*
												 * Since the player can only move vertically, just his height is taken into
												 * account (the y component)
												 */
	private boolean goingUp;
	private boolean controlledPawn; // TODO rename to reflect client player possession
	private Color color;

	public PlayerPawn()
	{
		this.position = INITIAL_POSITION;
		color = Color.WHITE;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		if (position <= 0)
			this.position = 0;
		else
			this.position = position + BAR_HEIGHT >= PongGame.WINDOW_HEIGHT
					? PongGame.WINDOW_HEIGHT - BAR_HEIGHT - 1
					: position;
	}

	public boolean isGoingUp()
	{
		return goingUp;
	}

	public void setGoingUp(boolean goingUp)
	{
		this.goingUp = goingUp;
	}

	public boolean isControlledPawn()
	{
		return controlledPawn;
	}

	public void setControlledPawn(boolean controlledPawn)
	{
		this.controlledPawn = controlledPawn;
	}

	public void move()
	{
		if (goingUp)
			position = position - 1 < 0 ? position : position - 1;
		else
			position = position + BAR_HEIGHT >= PongGame.WINDOW_HEIGHT ? position : position + 1;
	}

	public void resetPosition()
	{
		position = INITIAL_POSITION;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(
				controlledPawn ? MARGIN_LEFT_RIGHT : PongGame.WINDOW_WIDTH - MARGIN_LEFT_RIGHT - BAR_WIDTH,
				position,
				BAR_WIDTH,
				BAR_HEIGHT
		);
	}
}
