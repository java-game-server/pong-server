package com.apporelbotna.gameserver.pongserver.stubs.model;

import java.awt.Graphics;

import com.google.gson.annotations.Expose;

public class Player
{
	private String username;
	@Expose private PlayerPawn pawn;
	private int goals;

	public Player(String username)
	{
		this.username = username;
		pawn = new PlayerPawn();
	}

	public String getUsername()
	{
		return username;
	}

	public PlayerPawn getPawn()
	{
		return pawn;
	}

	public void setPawn(PlayerPawn pawn)
	{
		this.pawn = pawn;
	}

	public int getGoals()
	{
		return goals;
	}

	public void setGoals(int goals)
	{
		this.goals = goals;
	}

	public void addGoal()
	{
		goals++;
	}

	public boolean hasWon()
	{
		return goals >= PongGame.GOALS_TO_WIN;
	}

	public boolean isControlledPlayer()
	{
		return pawn.isControlledPawn();
	}

	public void setControlledPlayer(boolean isControlled)
	{
		pawn.setControlledPawn(isControlled);
	}

	public int getPosition()
	{
		return pawn.getPosition();
	}

	public void setPosition(int position)
	{
		pawn.setPosition(position);
	}

	public boolean isGoingUp()
	{
		return pawn.isGoingUp();
	}

	public void setGoingUp(boolean goingUp)
	{
		pawn.setGoingUp(goingUp);
	}

	public void move()
	{
		pawn.move();
	}

	public void resetPosition()
	{
		pawn.resetPosition();
	}

	public void draw(Graphics g)
	{
		pawn.draw(g);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player)obj;
		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else if (!username.equals(other.username))
			return false;
		return true;
	}
}
