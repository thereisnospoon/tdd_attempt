package com.epam.tdd.parsing;

import com.epam.tdd.InstructionMessage;

public class InstructionMessageParser {

	private static final String MESSAGE_DELIMITER = "\\s";
	private static final int NUMBER_OF_TOKENS_IN_MESSAGE = 6;
	private static final int POSITION_OF_INSTRUCTION_TYPE = 1;

	public InstructionMessage parse(String instructionMessageString) {

		String[] messageTokens = instructionMessageString.split(MESSAGE_DELIMITER);
		if (NUMBER_OF_TOKENS_IN_MESSAGE != messageTokens.length) {
			throw new InstructionMessageParsingException("Invalid number of tokens in message");
		}

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(messageTokens[POSITION_OF_INSTRUCTION_TYPE]);

		return instructionMessage;
	}
}
