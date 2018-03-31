package com.apporelbotna.gameserver.pongserver.stubs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;

import lombok.extern.java.Log;

@Log
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

	public String readLine()
	{
		try
		{
			return reader.readLine();
		}
		catch (IOException e)
		{
			log.log(Level.FINER, Arrays.toString(e.getStackTrace()), e);
		}
		return null;
	}

	public boolean write(String msg)
	{
		try
		{
			writer.write(msg + "\n");
			writer.flush();
			return true;
		}
		catch (IOException e)
		{
			log.log(Level.FINER, Arrays.toString(e.getStackTrace()), e);
		}
		return false;
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
			log.log(Level.FINER, Arrays.toString(e.getStackTrace()), e);
		}
	}
}
