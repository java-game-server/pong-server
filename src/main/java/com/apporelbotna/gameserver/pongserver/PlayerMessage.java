package com.apporelbotna.gameserver.pongserver;

import com.apporelbotna.gameserver.pongserver.stubs.Player;

public class PlayerMessage
{
	public enum Message
	{
		GO_UP("1"), GO_DOWN("0");

		private String text;

		private Message(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return text;
		}

		public static Message fromStringCode(String code)
		{
			for(Message message : Message.values())
				if(message.getText().equals(code))
					return message;
			throw new RuntimeException("The PlayerMessage#Message code " + code + " is not defined");
		}
	}

	private Player player;
	private Message message;

	public PlayerMessage(Player player, Message message)
	{
		this.player = player;
		this.message = message;
	}

	public Player getPlayer()
	{
		return player;
	}

	public Message getMessage()
	{
		return message;
	}
}
