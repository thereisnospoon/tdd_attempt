package com.epam.tdd;

import com.epam.tdd.parsing.InstructionMessageParser;
import com.epam.tdd.parsing.InstructionMessageParsingException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstructionMessageParserTest {

	private static final String INVALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50";
	private static final String INSTRUCTION_MESSAGE_WITH_INVALID_FIRST_TOKEN =
			"Instruction A MZ89 5678 50 2015-03-05T10:04:56.012Z";

	private static final String VALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z";
	private static final String INSTRUCTION_TYPE_FROM_VALID_MESSAGE = "A";
	private static final String PRODUCT_CODE_FROM_VALID_MESSAGE = "MZ89";
	private static final int QUANTITY_FROM_VALID_MESSAGE = 5678;
	private static final int UOM_FROM_VALID_MESSAGE = 50;

	private InstructionMessageParser testedInstance = new InstructionMessageParser();

	@Test
	public void shouldSetParsedInstructionType() {

		InstructionMessage instructionMessage = testedInstance.parse(VALID_INSTRUCTION_MESSAGE);
		assertEquals(INSTRUCTION_TYPE_FROM_VALID_MESSAGE, instructionMessage.getInstructionType());
	}

	@Test
	public void shouldSetParsedProductCode() {

		InstructionMessage instructionMessage = testedInstance.parse(VALID_INSTRUCTION_MESSAGE);
		assertEquals(PRODUCT_CODE_FROM_VALID_MESSAGE, instructionMessage.getProductCode());
	}

	@Test
	public void shouldSetParsedQuantity() {

		InstructionMessage instructionMessage = testedInstance.parse(VALID_INSTRUCTION_MESSAGE);
		assertEquals(QUANTITY_FROM_VALID_MESSAGE, instructionMessage.getQuantity());
	}

	@Test
	public void shouldSetParsedUom() {

		InstructionMessage instructionMessage = testedInstance.parse(VALID_INSTRUCTION_MESSAGE);
		assertEquals(UOM_FROM_VALID_MESSAGE, instructionMessage.getUom());
	}

	@Test(expected = InstructionMessageParsingException.class)
	public void shouldThrowExceptionWhenInvalidNumberOfTokens() {
		testedInstance.parse(INVALID_INSTRUCTION_MESSAGE);
	}

	@Test(expected = InstructionMessageParsingException.class)
	public void shouldThrowExceptionWhenIncorrectFirstToken() {
		testedInstance.parse(INSTRUCTION_MESSAGE_WITH_INVALID_FIRST_TOKEN);
	}
}
