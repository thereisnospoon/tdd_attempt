package my.thereisnospoon.tdd.validation;

import my.thereisnospoon.tdd.InstructionMessage;
import my.thereisnospoon.tdd.InstructionMessageType;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstructionMessageValidatorTest {

	private static final String VALID_PRODUCT_CODE = "MB78";
	private static final String INVALID_PRODUCT_CODE = "M123";

	private static final int MIN_VALID_QUANTITY = 1;
	private static final int INVALID_QUANTITY = 0;
	private static final int VALID_QUANTITY = 50;

	private static final int TOO_LOW_UOM = -1;
	private static final int MIN_VALID_UOM = 0;
	private static final int VALID_UOM = 128;
	private static final int MAX_VALID_UOM = 255;
	private static final int TOO_LARGE_UOM = 256;

	private static final Instant TIMESTAMP_BEFORE_EPOCH =
			ZonedDateTime.of(1969, 1, 1, 1, 1, 1, 1, ZoneId.systemDefault()).toInstant();

	private static final Instant VALID_TIMESTAMP =
			ZonedDateTime.of(2014, 1, 1, 1, 1, 1, 1, ZoneId.systemDefault()).toInstant();

	private static final Instant TIMESTAMP_FROM_FUTURE =
			ZonedDateTime.of(9999, 1, 1, 1, 1, 1, 1, ZoneId.systemDefault()).toInstant();

	private InstructionMessageValidator testedInstance = new InstructionMessageValidator();

	private InstructionMessage instructionMessage;

	@Before
	public void setUp() {

		instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(InstructionMessageType.A);
		instructionMessage.setProductCode(VALID_PRODUCT_CODE);
		instructionMessage.setQuantity(VALID_QUANTITY);
		instructionMessage.setUom(VALID_UOM);
		instructionMessage.setTimestamp(VALID_TIMESTAMP);
	}

	@Test
	public void shouldValidateInstructionTypeA() {

		instructionMessage.setInstructionType(InstructionMessageType.A);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateInstructionTypeB() {

		instructionMessage.setInstructionType(InstructionMessageType.B);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateInstructionTypeC() {

		instructionMessage.setInstructionType(InstructionMessageType.C);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateInstructionTypeD() {

		instructionMessage.setInstructionType(InstructionMessageType.D);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateProductCode() {

		instructionMessage.setProductCode(VALID_PRODUCT_CODE);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateQuantity() {

		instructionMessage.setQuantity(VALID_QUANTITY);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateMinQuantity() {

		instructionMessage.setQuantity(MIN_VALID_QUANTITY);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateUom() {

		instructionMessage.setUom(VALID_UOM);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateMinUom() {

		instructionMessage.setUom(MIN_VALID_UOM);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateMaxUom() {

		instructionMessage.setUom(MAX_VALID_UOM);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateTimestamp() {

		instructionMessage.setTimestamp(VALID_TIMESTAMP);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateCurrentTimestamp() {

		instructionMessage.setTimestamp(Instant.now());
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenInvalidProductCode() {

		instructionMessage.setProductCode(INVALID_PRODUCT_CODE);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenInvalidQuantity() {

		instructionMessage.setQuantity(INVALID_QUANTITY);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenTooLowUom() {

		instructionMessage.setUom(TOO_LOW_UOM);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenTooLargeUom() {

		instructionMessage.setUom(TOO_LARGE_UOM);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenTimestampBeforeEpoch() {

		instructionMessage.setTimestamp(TIMESTAMP_BEFORE_EPOCH);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenTimestampEqualsEpoch() {

		instructionMessage.setTimestamp(Instant.EPOCH);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenTimestampFromFuture() {

		instructionMessage.setTimestamp(TIMESTAMP_FROM_FUTURE);
		testedInstance.validate(instructionMessage);
	}
}
