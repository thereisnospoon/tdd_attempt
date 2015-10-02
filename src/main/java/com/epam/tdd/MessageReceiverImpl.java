package com.epam.tdd;

import com.epam.tdd.parsing.InstructionMessageParser;

public class MessageReceiverImpl implements MessageReceiver {

	private InstructionMessageParser instructionMessageParser;

	public MessageReceiverImpl() {
		instructionMessageParser = new InstructionMessageParser();
	}

	@Override
	public void receive(String message) {

		InstructionMessage instructionMessage = instructionMessageParser.parse(message);

	}
}
