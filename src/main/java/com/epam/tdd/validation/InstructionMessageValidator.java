package com.epam.tdd.validation;

import com.epam.tdd.InstructionMessage;

public class InstructionMessageValidator {

	private static final String INSTRUCTION_TYPE_PATTERN = "[A-D]";
	private static final String PRODUCT_CODE_PATTERN = "[A-Z]{2}\\d{2}";

	private static final int MIN_VALID_QUANTITY = 1;

	private static final int MIN_VALID_UOM = 0;
	private static final int MAX_VALID_UOM = 255;

	public void validate(InstructionMessage instructionMessage) {

		String instructionType = instructionMessage.getInstructionType();

		if (!instructionType.matches(INSTRUCTION_TYPE_PATTERN)) {
			throw new InstructionMessageValidationException("Invalid Instruction type: " + instructionType);
		}

		String productCode = instructionMessage.getProductCode();
		if (!productCode.matches(PRODUCT_CODE_PATTERN)) {
			throw new InstructionMessageValidationException("Invalid Product Code: " + productCode);
		}

		int quantity = instructionMessage.getQuantity();
		if (quantity < MIN_VALID_QUANTITY) {
			throw new InstructionMessageValidationException("Quantity is too small: " + quantity);
		}

		int uom = instructionMessage.getUom();
		if (uom < MIN_VALID_UOM || MAX_VALID_UOM < uom) {
			throw new InstructionMessageValidationException("UOM is not in valid range: " + uom);
		}
	}
}
