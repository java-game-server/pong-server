package com.apporelbotna.gameserver.pongserver.stubs.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum PlayerMovementMessage implements Message
{
	GO_UP("1"), GO_DOWN("0");

	private static final Logger logger = LoggerFactory.getLogger(PlayerMovementMessage.class);

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
		logger.error(exception.getMessage());
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
