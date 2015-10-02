package com.epam.tdd;

import com.epam.tdd.validation.InstructionMessageValidationException;
import com.epam.tdd.validation.InstructionMessageValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class InstructionQueueTest {

	private static final int ZERO_COUNT = 0;
	private static final int COUNT_FOR_QUEUE_WITH_TWO_MESSAGES = 2;

	private static final String VALID_PRODUCT_CODE = "MB12";
	private static final String VALID_INSTRUCTION_TYPE = "A";
	private static final int VALID_QUANTITY = 1;
	private static final int VALID_UOM = 1;
	private static final Instant VALID_TIMESTAMP = Instant.now();

	private static final int INVALID_UOM = 256;

	@InjectMocks
	private InstructionQueue testedInstance = new InstructionQueue();

	@Mock
	private InstructionMessageValidator instructionMessageValidator;

	private InstructionMessage instructionMessage;

	@Before
	public void setUp() {
		instructionMessage = createValidInstructionMessage();
	}

	private InstructionMessage createValidInstructionMessage() {

		InstructionMessage validInstructionMessage = new InstructionMessage();
		validInstructionMessage.setInstructionType(VALID_INSTRUCTION_TYPE);
		validInstructionMessage.setProductCode(VALID_PRODUCT_CODE);
		validInstructionMessage.setQuantity(VALID_QUANTITY);
		validInstructionMessage.setUom(VALID_UOM);
		validInstructionMessage.setTimestamp(VALID_TIMESTAMP);

		return validInstructionMessage;
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

	@Test
	public void shouldDequeueMessagesByPriority() {

		InstructionMessage messageWithTypeA = createValidInstructionMessage();
		messageWithTypeA.setInstructionType("A");

		InstructionMessage messageWithTypeB = createValidInstructionMessage();
		messageWithTypeB.setInstructionType("B");

		InstructionMessage messageWithTypeC = createValidInstructionMessage();
		messageWithTypeC.setInstructionType("C");

		InstructionMessage messageWithTypeD = createValidInstructionMessage();
		messageWithTypeD.setInstructionType("D");

		List<InstructionMessage> messagesOrderByPriority = new LinkedList<>();
		messagesOrderByPriority.add(messageWithTypeA);
		messagesOrderByPriority.add(messageWithTypeB);
		messagesOrderByPriority.add(messageWithTypeC);
		messagesOrderByPriority.add(messageWithTypeD);

		testedInstance.enqueue(messageWithTypeB);
		testedInstance.enqueue(messageWithTypeC);
		testedInstance.enqueue(messageWithTypeD);
		testedInstance.enqueue(messageWithTypeA);

		List<InstructionMessage> retrievedMessages = new LinkedList<>();
		while (!testedInstance.isEmpty()) {
			retrievedMessages.add(testedInstance.dequeue());
		}

		assertEquals(messagesOrderByPriority, retrievedMessages);
	}

	@Test(expected = InstructionMessageValidationException.class)
	public void shouldThrowExceptionWhenAddingInvalidMessage() {

		InstructionMessage invalidInstructionMessage = createValidInstructionMessage();
		invalidInstructionMessage.setUom(INVALID_UOM);

		doThrow(new InstructionMessageValidationException("invalid message"))
				.when(instructionMessageValidator).validate(invalidInstructionMessage);

		testedInstance.enqueue(invalidInstructionMessage);
	}
}
