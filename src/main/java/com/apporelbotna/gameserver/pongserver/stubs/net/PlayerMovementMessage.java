package com.apporelbotna.gameserver.pongserver.stubs.net;

import java.util.Arrays;
import java.util.logging.Level;

import lombok.extern.java.Log;

@Log
public enum PlayerMovementMessage implements Message
{
	GO_UP("1"), GO_DOWN("0");

	private String text;

	private PlayerMovementMessage(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public static PlayerMovementMessage fromStringCode(String code)
	{
		for (PlayerMovementMessage message : PlayerMovementMessage.values())
			if (message.getText().equals(code))
				return message;
		RuntimeException exception = new RuntimeException(
				"The PlayerMessage#Message code " + code + " is not defined");
		log.log(Level.FINER, Arrays.toString(exception.getStackTrace()), exception);
		throw exception;
	}

	@Override
	public String serialize()
	{
		return getText();
	}

	@Override
	public PlayerMovementMessage deserialize(String serializedMessage)
	{
		return fromStringCode(serializedMessage);
	}

	@Override
	public boolean canDeserialize(String serializedMessage)
	{
		for (PlayerMovementMessage message : PlayerMovementMessage.values())
			if (message.getText().equals(serializedMessage))
				return true;
		return false;
	}
}
