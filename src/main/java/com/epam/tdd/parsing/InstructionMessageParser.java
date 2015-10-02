package com.epam.tdd.parsing;

import com.epam.tdd.InstructionMessage;

public class InstructionMessageParser {

	private static final String MESSAGE_DELIMITER = "\\s";
	private static final String FIRST_TOKEN_OF_MESSAGE = "InstructionMessage";

	private static final int NUMBER_OF_TOKENS_IN_MESSAGE = 6;
	private static final int POSITION_OF_FIRST_TOKEN = 0;
	private static final int POSITION_OF_INSTRUCTION_TYPE = 1;
	private static final int POSITION_OF_PRODUCT_CODE = 2;

	public InstructionMessage parse(String instructionMessageString) {

		String[] messageTokens = instructionMessageString.split(MESSAGE_DELIMITER);
		validateMessageFormat(messageTokens);

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(messageTokens[POSITION_OF_INSTRUCTION_TYPE]);
		instructionMessage.setProductCode(messageTokens[POSITION_OF_PRODUCT_CODE]);

		return instructionMessage;
	}

	private void validateMessageFormat(String[] messageTokens) {

		validateNumberOfTokens(messageTokens);
		validateFirstTokenOfMessage(messageTokens);
	}

	private void validateNumberOfTokens(String[] messageTokens) {
		if (NUMBER_OF_TOKENS_IN_MESSAGE != messageTokens.length) {
			throw new InstructionMessageParsingException("Invalid number of tokens in message");
		}
	}

	private void validateFirstTokenOfMessage(String[] messageTokens) {
		if (!FIRST_TOKEN_OF_MESSAGE.equals(messageTokens[POSITION_OF_FIRST_TOKEN])) {
			throw new InstructionMessageParsingException("First token of message is invalid: "
					+ messageTokens[POSITION_OF_FIRST_TOKEN]);
		}
	}
}
