/*
 * Class FaxValidatorTest
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class will offer Methods to test the functionality from other Class
 *
 * Copyright ZHAW 2013
 *
*/

package ch.zhaw.multiChannel.tests;

import ch.zhaw.multiChannel.model.Message;
import ch.zhaw.multiChannel.model.FaxValidator;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FaxValidatorTest {

	@Test
	public void testValidationOfValidMessage() throws Exception {
		
		FaxValidator sut = new FaxValidator(getValidMessage());
		assertTrue("Validating valid message", sut.isValid());
	}

	@Test
	public void testMessageWithInvalidReceiver() throws Exception {
		
		FaxValidator sut = new FaxValidator(getMessageWithInvalidReceiver());
		assertFalse("Message with invalid receiver", sut.isValid());
	}

	@Test
	public void testIfErrorMessageIsEmptyIfValidationOk() throws Exception {
		
		FaxValidator sut = new FaxValidator(getValidMessage());
		sut.isValid();
		assertNull("Validating error message if validation ok", sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnReceiverError() throws Exception {
		
		FaxValidator sut = new FaxValidator(getMessageWithInvalidReceiver());
		sut.isValid();
		assertEquals(
				"Validating error message if receiver has an error.",
				"Ungültiger Empfänger, nur Nummern erlaubt: 07845a67890",
				sut.getErrorMessage());
	}

	private Message getValidMessage() {
		
		return new Message(
				new String[] {"0791234567","0784567890"},
				"My Message",
				new Date()
		);
	}

	private Message getMessageWithInvalidReceiver() {
		
		return new Message(
				new String[] {"0791234567","07845a67890"},
				"My Message",
				new Date()
		);
	}
}
