package com.epam.tdd.validation;

import com.epam.tdd.InstructionMessage;
import org.junit.Before;
import org.junit.Test;

public class InstructionMessageValidatorTest {

	private static final String INSTRUCTION_TYPE_A = "A";
	private static final String INSTRUCTION_TYPE_B = "B";
	private static final String INSTRUCTION_TYPE_C = "C";
	private static final String INSTRUCTION_TYPE_D = "D";

	private static final String INVALID_INSTRUCTION_TYPE = "G";

	private static final String VALID_PRODUCT_CODE = "MB78";
	private static final String INVALID_PRODUCT_CODE = "M123";

	private static final int MIN_VALID_QUANTITY = 1;
	private static final int INVALID_QUANTITY = 0;
	private static final int VALID_QUANTITY = 50;

	private InstructionMessageValidator testedInstance = new InstructionMessageValidator();

	private InstructionMessage instructionMessage;

	@Before
	public void setUp() {

		instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INSTRUCTION_TYPE_A);
		instructionMessage.setProductCode(VALID_PRODUCT_CODE);
		instructionMessage.setQuantity(VALID_QUANTITY);
	}

	@Test
	public void shouldValidateInstructionTypeA() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_A);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateInstructionTypeB() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_B);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateInstructionTypeC() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_C);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateInstructionTypeD() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_D);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateProductCode() {

		instructionMessage.setProductCode(VALID_PRODUCT_CODE);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateMinQuantity() {

		instructionMessage.setQuantity(MIN_VALID_QUANTITY);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldValidateQuantity() {

		instructionMessage.setQuantity(VALID_QUANTITY);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenInvalidInstructionType() {

		instructionMessage.setInstructionType(INVALID_INSTRUCTION_TYPE);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenInvalidProductCode() {

		instructionMessage.setInstructionType(INVALID_PRODUCT_CODE);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenInvalidQuantity() {

		instructionMessage.setQuantity(VALID_QUANTITY);
		testedInstance.validate(instructionMessage);
	}
}
