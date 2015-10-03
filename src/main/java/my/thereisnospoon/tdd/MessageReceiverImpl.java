package my.thereisnospoon.tdd;

import my.thereisnospoon.tdd.parsing.InstructionMessageParser;

public class MessageReceiverImpl implements MessageReceiver {

	private InstructionMessageParser instructionMessageParser;

	private InstructionQueue instructionQueue;

	public MessageReceiverImpl() {

		instructionMessageParser = new InstructionMessageParser();
		instructionQueue = new InstructionQueue();
	}

	@Override
	public void receive(String message) {

		InstructionMessage instructionMessage = instructionMessageParser.parse(message);
		instructionQueue.enqueue(instructionMessage);
	}
}
