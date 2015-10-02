package com.epam.tdd;

import org.junit.Test;

public class InstructionMessageParserTest {

	private static final String INVALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50";

	private InstructionMessageParser testedInstance = new InstructionMessageParser();

	@Test(expected = InstructionMessageParsingException.class)
	public void shouldThrownExceptionWhenInvalidNumberOfTokens() {
		testedInstance.parse(INVALID_INSTRUCTION_MESSAGE);
	}
}
