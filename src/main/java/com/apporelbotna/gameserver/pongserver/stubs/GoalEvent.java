package com.apporelbotna.gameserver.pongserver.stubs;

import java.util.Observable;

public class GoalEvent extends Observable
{
	/**
	 * Notify that a goal has been scored to all observers
	 */
	public void goal(Player scoringPlayer)
	{
		notifyObservers(scoringPlayer);
	}
}
