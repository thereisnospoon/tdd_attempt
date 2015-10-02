package com.epam.tdd.parsing;

public class InstructionMessageParser {

	private static final int NUMBER_OF_TOKENS_IN_MESSAGE = 6;
	private static final String MESSAGE_DELIMITER = "\\s";

	public void parse(String instructionMessageString) {

		String[] messageTokens = instructionMessageString.split(MESSAGE_DELIMITER);
		if (NUMBER_OF_TOKENS_IN_MESSAGE != messageTokens.length) {
			throw new InstructionMessageParsingException("Invalid number of tokens in message");
		}
	}
}
