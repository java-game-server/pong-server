package com.apporelbotna.gameserver.pongserver.stubs.model;

import com.google.gson.annotations.Expose;

public class Vector2
{
	@Expose public int X;
	@Expose public int Y;

	public Vector2() { }
	public Vector2(int X, int Y) { this.X = X; this.Y = Y; }

	public static Vector2 of(int X, int Y)
	{
		return new Vector2(X, Y);
	}

	public Vector2 add(Vector2 other)
	{
		return new Vector2(this.X + other.X, this.Y + other.Y);
	}

	public Vector2 sub(Vector2 other)
	{
		return new Vector2(this.X - other.X, this.Y - other.Y);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
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
		Vector2 other = (Vector2) obj;
		if (X != other.X)
			return false;
		return (Y != other.Y);
	}
}
