package com.epam.tdd;

import com.epam.tdd.validation.InstructionMessageValidator;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class InstructionQueue {

	private Queue<InstructionMessage> messageQueue;

	private InstructionMessageValidator instructionMessageValidator;

	public InstructionQueue() {

		messageQueue = new PriorityQueue<>(Comparator.comparing(message -> message.getInstructionType().getPriority()));
		instructionMessageValidator = new InstructionMessageValidator();
	}

	public boolean isEmpty() {
		return messageQueue.isEmpty();
	}

	public void enqueue(InstructionMessage instructionMessage) {

		instructionMessageValidator.validate(instructionMessage);
		messageQueue.add(instructionMessage);
	}

	public InstructionMessage dequeue() {
		return messageQueue.poll();
	}

	public int count() {
		return messageQueue.size();
	}

	public InstructionMessage peek() {
		return messageQueue.peek();
	}
}
