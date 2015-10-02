package com.epam.tdd.validation;

import com.epam.tdd.InstructionMessage;
import org.junit.Test;

public class InstructionMessageValidatorTest {

	private static final String INSTRUCTION_TYPE_A = "A";
	private static final String INSTRUCTION_TYPE_B = "B";
	private static final String INSTRUCTION_TYPE_C = "C";
	private static final String INSTRUCTION_TYPE_D = "D";

	private static final String INVALID_INSTRUCTION_TYPE = "G";

	private InstructionMessageValidator testedInstance = new InstructionMessageValidator();

	@Test
	public void shouldSuccessfullyValidateInstructionTypeA() {

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INSTRUCTION_TYPE_A);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeB() {

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INSTRUCTION_TYPE_B);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeC() {

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INSTRUCTION_TYPE_C);
		testedInstance.validate(instructionMessage);
	}

	@Test
	public void shouldSuccessfullyValidateInstructionTypeD() {

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INSTRUCTION_TYPE_D);
		testedInstance.validate(instructionMessage);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenInvalidInstructionType() {

		InstructionMessage instructionMessage = new InstructionMessage();
		instructionMessage.setInstructionType(INVALID_INSTRUCTION_TYPE);
		testedInstance.validate(instructionMessage);
	}
}
