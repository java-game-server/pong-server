package com.apporelbotna.gameserver.pongserver.stubs.model;

import java.awt.Graphics;

import com.apporelbotna.gameserver.stubs.Token;
import com.apporelbotna.gameserver.stubs.User;
import com.apporelbotna.gameserver.stubs.UserWrapper;
import com.google.gson.annotations.Expose;

public class Player
{
	private UserWrapper userWrapper;
	@Expose private PlayerPawn pawn;
	private int goals;

	public Player(UserWrapper userWrapper)
	{
		this.userWrapper = userWrapper;
		pawn = new PlayerPawn();
	}

	public Player(String email, String tokenKey)
	{
		this(new UserWrapper(new User(email), new Token(tokenKey)));
	}

	public UserWrapper getUserWrapper()
	{
		return userWrapper;
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

	public String getName()
	{
		return userWrapper.getUser().getName();
	}

	public String getEmail()
	{
		return userWrapper.getUser().getEmail();
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
		return pawn.isControlledByClient();
	}

	public void setControlledPlayer(boolean isControlled)
	{
		pawn.setControlledByClient(isControlled);
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
		result = prime * result + ((userWrapper == null) ? 0 : userWrapper.hashCode());
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
		if (userWrapper == null)
		{
			if (other.userWrapper != null)
				return false;
		}
		else if (!userWrapper.equals(other.userWrapper))
			return false;
		return true;
	}
}
