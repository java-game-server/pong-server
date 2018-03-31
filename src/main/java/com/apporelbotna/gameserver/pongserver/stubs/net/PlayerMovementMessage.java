package com.apporelbotna.gameserver.pongserver.stubs.net;

public enum PlayerMovementMessage
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
		for(PlayerMovementMessage message : PlayerMovementMessage.values())
			if(message.getText().equals(code))
				return message;
		throw new RuntimeException("The PlayerMessage#Message code " + code + " is not defined");
	}
}
