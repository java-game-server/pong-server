package com.apporelbotna.gameserver.pongserver;

public class GameThread implements Runnable
{
	private static final Vector2 PLAYER1_INITIAL_POSITION = new Vector2(0, 0); // TODO give sensible values
	private static final Vector2 PLAYER2_INITIAL_POSITION = new Vector2(0, 0); // TODO give sensible values

	private class GameStatus
	{
		private Player player1;
		private Player player2;

		public GameStatus(String usernamePlayer1, String usernamePlayer2)
		{
			player1 = new Player(usernamePlayer1);
			player1.setPosition(PLAYER1_INITIAL_POSITION);

			player2 = new Player(usernamePlayer2);
			player2.setPosition(PLAYER2_INITIAL_POSITION);
		}
	}

	private GameStatus gameStatus;

	public GameThread(String usernamePlayer1, String usernamePlayer2)
	{
		gameStatus = new GameStatus(usernamePlayer1, usernamePlayer2);
	}

	@Override
	public void run()
	{
		System.out.println("Player 1: " + gameStatus.player1.toString() + "\n"
											+ "Player 2: " + gameStatus.player2.toString());
	}
}
