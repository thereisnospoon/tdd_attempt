package com.epam.tdd.parsing;

import com.epam.tdd.InstructionMessage;
import com.epam.tdd.InstructionMessageType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;

public class InstructionMessageParserTest {

	private static final String INVALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50";
	private static final String INSTRUCTION_MESSAGE_WITH_INVALID_FIRST_TOKEN =
			"Instruction A MZ89 5678 50 2015-03-05T10:04:56.012Z";

	private static final String INSTRUCTION_MESSAGE_WITH_INVALID_TIMESTAMP_FORMAT =
			"InstructionMessage A MZ89 5678 50 2015/03/05T10:04:56.012Z";

	private static final String VALID_INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.000Z";
	private static final InstructionMessageType INSTRUCTION_TYPE_FROM_VALID_MESSAGE = InstructionMessageType.A;
	private static final String PRODUCT_CODE_FROM_VALID_MESSAGE = "MZ89";
	private static final int QUANTITY_FROM_VALID_MESSAGE = 5678;
	private static final int UOM_FROM_VALID_MESSAGE = 50;

	private static final LocalDateTime TIMESTAMP_AS_DATE_TIME_FROM_VALID_MESSAGE =
			LocalDateTime.of(2015, 3, 5, 10, 4, 56);

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

	@Test
	public void shouldSetParsedTimestamp() {

		InstructionMessage instructionMessage = testedInstance.parse(VALID_INSTRUCTION_MESSAGE);
		assertEquals(TIMESTAMP_AS_DATE_TIME_FROM_VALID_MESSAGE,
				LocalDateTime.ofInstant(instructionMessage.getTimestamp(), ZoneId.systemDefault()));
	}

	@Test(expected = InstructionMessageParsingException.class)
	public void shouldThrowExceptionWhenInvalidNumberOfTokens() {
		testedInstance.parse(INVALID_INSTRUCTION_MESSAGE);
	}

	@Test(expected = InstructionMessageParsingException.class)
	public void shouldThrowExceptionWhenIncorrectFirstToken() {
		testedInstance.parse(INSTRUCTION_MESSAGE_WITH_INVALID_FIRST_TOKEN);
	}

	@Test(expected = DateTimeParseException.class)
	public void shouldThrowExceptionWhenInvalidTimestampFormat() {
		testedInstance.parse(INSTRUCTION_MESSAGE_WITH_INVALID_TIMESTAMP_FORMAT);
	}
}
