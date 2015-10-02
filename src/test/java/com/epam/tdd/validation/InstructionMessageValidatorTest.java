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

	private InstructionMessageValidator testedInstance = new InstructionMessageValidator();

	private InstructionMessage instructionMessage;

	@Before
	public void setUp() {

		instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INSTRUCTION_TYPE_A);
		instructionMessage.setProductCode(VALID_PRODUCT_CODE);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeA() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_A);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeB() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_B);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeC() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_C);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeD() {

		instructionMessage.setInstructionType(INSTRUCTION_TYPE_D);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateProductCode() {

		instructionMessage.setProductCode(VALID_PRODUCT_CODE);
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
}
