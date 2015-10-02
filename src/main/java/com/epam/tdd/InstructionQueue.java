package com.epam.tdd;

import java.util.LinkedList;
import java.util.Queue;

public class InstructionQueue {

	private Queue<InstructionMessage> messageQueue;

	public InstructionQueue() {
		messageQueue = new LinkedList<>();
	}

	public boolean isEmpty() {
		return messageQueue.isEmpty();
	}

	public void enqueue(InstructionMessage instructionMessage) {
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
