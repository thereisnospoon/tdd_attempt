package com.epam.tdd;

import com.epam.tdd.parsing.InstructionMessageParser;
import com.epam.tdd.parsing.InstructionMessageParsingException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstructionMessageParserTest {

	private static final String INVALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50";

	private static final String VALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z";
	private static final String INSTRUCTION_TYPE_FROM_VALID_MESSAGE = "A";

	private InstructionMessageParser testedInstance = new InstructionMessageParser();

	@Test
	public void shouldSetParsedInstructionType() {

		InstructionMessage instructionMessage = testedInstance.parse(VALID_INSTRUCTION_MESSAGE);
		assertEquals(INSTRUCTION_TYPE_FROM_VALID_MESSAGE, instructionMessage.getInstructionType());
	}

	@Test(expected = InstructionMessageParsingException.class)
	public void shouldThrownExceptionWhenInvalidNumberOfTokens() {
		testedInstance.parse(INVALID_INSTRUCTION_MESSAGE);
	}
}
