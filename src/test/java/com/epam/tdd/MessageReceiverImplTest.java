package com.epam.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.tdd.parsing.InstructionMessageParser;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageReceiverImplTest {

	private static final String INSTRUCTION_MESSAGE = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z";

	@InjectMocks
	private MessageReceiverImpl testedInstance = new MessageReceiverImpl();

	@Mock
	private InstructionMessageParser instructionMessageParser;

	@Test
	public void shouldParseReceivedMessage() {

		testedInstance.receive(INSTRUCTION_MESSAGE);
		verify(instructionMessageParser).parse(INSTRUCTION_MESSAGE);
	}
}
