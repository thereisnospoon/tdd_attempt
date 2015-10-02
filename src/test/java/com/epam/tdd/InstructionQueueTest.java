package com.epam.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstructionQueueTest {

	private InstructionQueue testedInstance = new InstructionQueue();

	private InstructionMessage instructionMessage;

	@Before
	public void setUp() {
		instructionMessage = new InstructionMessage();
	}

	@Test
	public void shouldBeEmptyQueue() {
		assertTrue(testedInstance.isEmpty());
	}

	@Test
	public void shouldBeNonEmpty() {

		assertTrue(testedInstance.isEmpty());
		testedInstance.enqueue(instructionMessage);
		assertFalse(testedInstance.isEmpty());
	}
}
