package com.epam.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.tdd.parsing.InstructionMessageParser;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageReceiverImplTest {

	private static final String INSTRUCTION_MESSAGE_STRING = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z";
	private static final InstructionMessage INSTRUCTION_MESSAGE = new InstructionMessage();

	@InjectMocks
	private MessageReceiverImpl testedInstance = new MessageReceiverImpl();

	@Mock
	private InstructionMessageParser instructionMessageParser;

	@Mock
	private InstructionQueue instructionQueue;

	@Test
	public void shouldParseReceivedMessage() {

		testedInstance.receive(INSTRUCTION_MESSAGE_STRING);
		verify(instructionMessageParser).parse(INSTRUCTION_MESSAGE_STRING);
	}

	@Test
	public void shouldAddReceivedMessageToQueue() {

		when(instructionMessageParser.parse(INSTRUCTION_MESSAGE_STRING)).thenReturn(INSTRUCTION_MESSAGE);

		testedInstance.receive(INSTRUCTION_MESSAGE_STRING);
		verify(instructionQueue).enqueue(INSTRUCTION_MESSAGE);
	}
}
