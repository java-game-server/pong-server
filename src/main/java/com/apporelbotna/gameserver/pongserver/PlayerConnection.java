package com.apporelbotna.gameserver.pongserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.apporelbotna.gameserver.pongserver.stubs.Player;

public class PlayerConnection
{
	private Player player;
	private Socket socket;

	public PlayerConnection(Player player, Socket socket)
	{
		super();
		this.player = player;
		this.socket = socket;
	}

	public Player getPlayer()
	{
		return player;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public BufferedReader getReader() throws IOException
	{
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public BufferedWriter getWriter() throws IOException
	{
		return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void close()
	{
		player = null;
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
