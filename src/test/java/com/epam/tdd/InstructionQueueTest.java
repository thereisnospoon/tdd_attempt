package com.epam.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstructionQueueTest {

	private static final int ZERO_COUNT = 0;
	private static final int COUNT_FOR_QUEUE_WITH_TWO_MESSAGES = 2;

	private InstructionQueue testedInstance = new InstructionQueue();

	private InstructionMessage instructionMessage;

	@Before
	public void setUp() {
		instructionMessage = new InstructionMessage();
	}

	@Test
	public void shouldBeEmpty() {
		assertTrue(testedInstance.isEmpty());
	}

	@Test
	public void shouldBeNotEmpty() {

		assertTrue(testedInstance.isEmpty());
		testedInstance.enqueue(instructionMessage);
		assertFalse(testedInstance.isEmpty());
	}

	@Test
	public void shouldBeEmptyAfterMessageDequeue() {

		assertTrue(testedInstance.isEmpty());
		testedInstance.enqueue(instructionMessage);
		testedInstance.dequeue();
		assertTrue(testedInstance.isEmpty());
	}

	@Test
	public void shouldDequeuePreviouslyAddedMessage() {

		testedInstance.enqueue(instructionMessage);
		InstructionMessage retrievedInstructionMessage = testedInstance.dequeue();
		assertEquals(instructionMessage, retrievedInstructionMessage);
	}

	@Test
	public void shouldHaveZeroCountWhenEmpty() {

		assertTrue(testedInstance.isEmpty());
		assertEquals(ZERO_COUNT, testedInstance.count());
	}

	@Test
	public void shouldHaveCorrectCountWhenTwoMessageInQueue() {

		testedInstance.enqueue(instructionMessage);
		testedInstance.enqueue(instructionMessage);
		assertEquals(COUNT_FOR_QUEUE_WITH_TWO_MESSAGES, testedInstance.count());
	}

	@Test
	public void shouldHaveZeroCountWhenAllMessagesRemoved() {

		testedInstance.enqueue(instructionMessage);
		testedInstance.enqueue(instructionMessage);

		testedInstance.dequeue();
		testedInstance.dequeue();

		assertEquals(ZERO_COUNT, testedInstance.count());
	}

	@Test
	public void shouldReturnPreviouslyAddedMessageWhenPeek() {

		testedInstance.enqueue(instructionMessage);
		InstructionMessage retrievedMessage = testedInstance.peek();
		assertEquals(instructionMessage, retrievedMessage);
	}

	@Test
	public void shouldNotChangeCountAfterPeek() {

		testedInstance.enqueue(instructionMessage);
		testedInstance.enqueue(instructionMessage);

		assertEquals(COUNT_FOR_QUEUE_WITH_TWO_MESSAGES, testedInstance.count());

		testedInstance.peek();
		assertEquals(COUNT_FOR_QUEUE_WITH_TWO_MESSAGES, testedInstance.count());
	}
}
