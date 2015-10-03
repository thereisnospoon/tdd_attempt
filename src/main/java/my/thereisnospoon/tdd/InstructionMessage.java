package my.thereisnospoon.tdd;

import java.time.Instant;

public class InstructionMessage {

	private InstructionMessageType instructionType;
	private String productCode;
	private int quantity;
	private int uom;
	private Instant timestamp;

	public InstructionMessageType getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(InstructionMessageType instructionType) {
		this.instructionType = instructionType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUom() {
		return uom;
	}

	public void setUom(int uom) {
		this.uom = uom;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}
