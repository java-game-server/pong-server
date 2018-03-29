package com.apporelbotna.gameserver.pongserver.stubs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketConnection
{
	protected Socket socket;
	protected BufferedReader reader;
	protected BufferedWriter writer;

	public SocketConnection(Socket socket) throws IOException
	{
		super();
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public Socket getSocket()
	{
		return socket;
	}

	public BufferedReader getReader()
	{
		return reader;
	}

	public BufferedWriter getWriter()
	{
		return writer;
	}

	public void close()
	{
		try
		{
			socket.close();
			reader.close();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
