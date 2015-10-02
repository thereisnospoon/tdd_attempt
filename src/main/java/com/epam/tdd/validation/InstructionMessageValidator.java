package com.epam.tdd.validation;

import com.epam.tdd.InstructionMessage;

public class InstructionMessageValidator {

	private static final String INSTRUCTION_TYPE_PATTERN = "[A-D]";
	private static final String PRODUCT_CODE_PATTERN = "[A-Z]{2}\\d{2}";

	public void validate(InstructionMessage instructionMessage) {

		String instructionType = instructionMessage.getInstructionType();

		if (!instructionType.matches(INSTRUCTION_TYPE_PATTERN)) {
			throw new InstructionMessageValidationException("Invalid Instruction type: " + instructionType);
		}

		String productCode = instructionMessage.getProductCode();
		if (!productCode.matches(PRODUCT_CODE_PATTERN)) {
			throw new InstructionMessageValidationException("Invalid Product Code: " + productCode);
		}
	}
}
