package com.apporelbotna.gameserver.pongserver;

public class Player
{
	private String username;
	private Vector2 position;
	private int goals;

	public Player(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public int getGoals()
	{
		return goals;
	}

	public void setGoals(int goals)
	{
		this.goals = goals;
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

	@Override
	public String toString()
	{
		return "Player [username=" + username + ", position=" + position + ", goals=" + goals + "]";
	}
}
