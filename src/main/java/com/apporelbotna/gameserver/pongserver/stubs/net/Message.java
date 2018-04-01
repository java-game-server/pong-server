package com.apporelbotna.gameserver.pongserver.stubs.net;

public interface Message
{
	public String serialize();
	public Message deserialize(String serializedMessage);
	public boolean canDeserialize(String serializedMessage);
}
