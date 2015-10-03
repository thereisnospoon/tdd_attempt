package my.thereisnospoon.tdd;

public enum InstructionMessageType {

	A(0),
	B(1),
	C(2),
	D(2);

	private int priority;

	InstructionMessageType(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
}
