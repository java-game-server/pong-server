package com.apporelbotna.gameserver.pongserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Matchmaker
{
	private static Queue<Player> playerQueue; // CHECK can this be filled by another thread?
	private static final int DDOS_HALT = 1000;

	public static void main(String[] args)
	{
		playerQueue = new LinkedList<>();
		System.out.println(" Server is Running  ");

		try (ServerSocket serverSocket = new ServerSocket(5555))
		{
			// REFACTOR meeeee
			while (playerQueue.size() < DDOS_HALT)
			{
				// Listen for the first player
				Socket firstConnectionSocket = serverSocket.accept();
				BufferedReader firstReader = new BufferedReader(
						new InputStreamReader(firstConnectionSocket.getInputStream()));
				BufferedWriter firstWriter = new BufferedWriter(
						new OutputStreamWriter(firstConnectionSocket.getOutputStream()));

				// On first player accept, notify him
				String usernamePlayer1 = firstReader.readLine();
				firstWriter.write("*** Waiting for another player to join... ***\n");

				// Listen for the second player
				Socket secondConnectionSocket = serverSocket.accept();
				BufferedReader secondReader = new BufferedReader(
						new InputStreamReader(secondConnectionSocket.getInputStream()));
				BufferedWriter secondWriter = new BufferedWriter(
						new OutputStreamWriter(secondConnectionSocket.getOutputStream()));

				// On first second player accept, notify both
				String usernamePlayer2 = secondReader.readLine();
				firstWriter.write("*** GAME FOUND! ***\n");
				secondWriter.write("*** GAME FOUND! ***\n");

				new Thread(new GameThread(usernamePlayer1, usernamePlayer2));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
