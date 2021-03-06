package my.thereisnospoon.tdd.parsing;

import my.thereisnospoon.tdd.InstructionMessage;
import my.thereisnospoon.tdd.InstructionMessageType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InstructionMessageParser {

	private static final String MESSAGE_DELIMITER = "\\s";
	private static final String FIRST_TOKEN_OF_MESSAGE = "InstructionMessage";

	private static final int NUMBER_OF_TOKENS_IN_MESSAGE = 6;
	private static final int POSITION_OF_FIRST_TOKEN = 0;
	private static final int POSITION_OF_INSTRUCTION_TYPE = 1;
	private static final int POSITION_OF_PRODUCT_CODE = 2;
	private static final int POSITION_OF_QUANTITY = 3;
	private static final int POSITION_OF_UOM = 4;
	private static final int POSITION_OF_TIMESTAMP = 5;

	private static final DateTimeFormatter DATE_TIME_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public InstructionMessage parse(String instructionMessageString) {

		String[] messageTokens = instructionMessageString.split(MESSAGE_DELIMITER);
		validateMessageFormat(messageTokens);
		return parseMessageParameters(messageTokens);
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

	private InstructionMessage parseMessageParameters(String[] messageTokens) {

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(parseInstructionType(messageTokens[POSITION_OF_INSTRUCTION_TYPE]));
		instructionMessage.setProductCode(messageTokens[POSITION_OF_PRODUCT_CODE]);
		instructionMessage.setQuantity(Integer.parseInt(messageTokens[POSITION_OF_QUANTITY]));
		instructionMessage.setUom(Integer.parseInt(messageTokens[POSITION_OF_UOM]));
		instructionMessage.setTimestamp(parseTimestamp(messageTokens[POSITION_OF_TIMESTAMP]));

		return instructionMessage;
	}

	private InstructionMessageType parseInstructionType(String instructionTypeAsString) {
		return InstructionMessageType.valueOf(instructionTypeAsString);
	}

	private Instant parseTimestamp(String timestampString) {

		LocalDateTime timestamp = LocalDateTime.parse(timestampString, DATE_TIME_FORMATTER);
		return ZonedDateTime.of(timestamp, ZoneId.systemDefault()).toInstant();
	}
}
