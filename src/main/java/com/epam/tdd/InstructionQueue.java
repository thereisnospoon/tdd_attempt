package com.epam.tdd;

import com.epam.tdd.validation.InstructionMessageValidator;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class InstructionQueue {

	private static class InstructionMessageWrapper {

		private InstructionMessage instructionMessage;
		private long serialNumber;

		public InstructionMessageWrapper(InstructionMessage instructionMessage, long serialNumber) {
			this.instructionMessage = instructionMessage;
			this.serialNumber = serialNumber;
		}

		public InstructionMessage getInstructionMessage() {
			return instructionMessage;
		}

		public long getSerialNumber() {
			return serialNumber;
		}
	}

	private static final Comparator<InstructionMessageWrapper> BY_SERIAL_NUMBER_COMPARATOR =
			Comparator.comparing(InstructionMessageWrapper::getSerialNumber);

	private static final Comparator<InstructionMessageWrapper> BY_PRIORITY_COMPARATOR =
			Comparator.comparing(wrapper -> wrapper.getInstructionMessage().getInstructionType().getPriority());

	private static final Comparator<InstructionMessageWrapper> MESSAGES_COMPARATOR = BY_PRIORITY_COMPARATOR
			.thenComparing(BY_SERIAL_NUMBER_COMPARATOR);

	private Queue<InstructionMessageWrapper> messageQueue;

	private InstructionMessageValidator instructionMessageValidator;

	private long messagesCounter;

	public InstructionQueue() {

		messageQueue = new PriorityQueue<>(MESSAGES_COMPARATOR);
		instructionMessageValidator = new InstructionMessageValidator();
		messagesCounter = 0;
	}

	public boolean isEmpty() {
		return messageQueue.isEmpty();
	}

	public void enqueue(InstructionMessage instructionMessage) {

		instructionMessageValidator.validate(instructionMessage);
		messageQueue.add(new InstructionMessageWrapper(instructionMessage, ++messagesCounter));
	}

	public InstructionMessage dequeue() {
		return messageQueue.poll().getInstructionMessage();
	}

	public int count() {
		return messageQueue.size();
	}

	public InstructionMessage peek() {
		return messageQueue.peek().getInstructionMessage();
	}
}
