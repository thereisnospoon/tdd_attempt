package com.epam.tdd.validation;

import com.epam.tdd.InstructionMessage;

import java.time.Instant;

public class InstructionMessageValidator {

	private static final String PRODUCT_CODE_PATTERN = "[A-Z]{2}\\d{2}";

	private static final int MIN_VALID_QUANTITY = 1;

	private static final int MIN_VALID_UOM = 0;
	private static final int MAX_VALID_UOM = 255;

	public void validate(InstructionMessage instructionMessage) {

		validateProductCode(instructionMessage.getProductCode());
		validateQuantity(instructionMessage.getQuantity());
		validateUom(instructionMessage.getUom());
		validateTimestamp(instructionMessage.getTimestamp());
	}

	private void validateProductCode(String productCode) {

		if (!productCode.matches(PRODUCT_CODE_PATTERN)) {
			throw new InstructionMessageValidationException("Invalid Product Code: " + productCode);
		}
	}

	private void validateQuantity(int quantity) {

		if (quantity < MIN_VALID_QUANTITY) {
			throw new InstructionMessageValidationException("Quantity is too small: " + quantity);
		}
	}

	private void validateUom(int uom) {

		if (uom < MIN_VALID_UOM || MAX_VALID_UOM < uom) {
			throw new InstructionMessageValidationException("UOM is not in valid range: " + uom);
		}
	}

	private void validateTimestamp(Instant timestamp) {

		if (timestamp.compareTo(Instant.EPOCH) <= 0 || Instant.now().compareTo(timestamp) < 0) {
			throw new InstructionMessageValidationException("Timestamp is not in valid range: " + timestamp);
		}
	}
}
