package com.epam.tdd.validation;

import com.epam.tdd.InstructionMessage;

public class InstructionMessageValidator {

	private static final String INSTRUCTION_TYPE_PATTERN = "[A-D]";

	public void validate(InstructionMessage instructionMessage) {

		String instructionType = instructionMessage.getInstructionType();
		if (!instructionType.matches(INSTRUCTION_TYPE_PATTERN)) {
			throw new InstructionMessageValidationException("Invalid Instruction type: " + instructionType);
		}
	}
}
