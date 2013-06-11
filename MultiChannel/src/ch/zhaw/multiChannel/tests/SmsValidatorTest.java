/*
 * Class SmsValidatorTest
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
import ch.zhaw.multiChannel.model.SmsValidator;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.*;

public class SmsValidatorTest {

	@Test
	public void testValidationOfValidMessage() throws Exception {
		
		SmsValidator sut = new SmsValidator(getValidMessage());
		assertTrue("Validating valid message", sut.isValid());
	}

	@Test
	public void testMessageWithInvalidReceiver() throws Exception {
		
		SmsValidator sut = new SmsValidator(getMessageWithInvalidReceiver());
		assertFalse("Message with invalid receiver", sut.isValid());
	}

	@Test
	public void testMessageWithAToLongMessage() throws Exception {
		
		SmsValidator sut = new SmsValidator(getMessageWithAToLongMessage());
		assertFalse("Message with a to long message", sut.isValid());
	}

	@Test
	public void testIfErrorMessageIsEmptyIfValidationOk() throws Exception {
		
		SmsValidator sut = new SmsValidator(getValidMessage());
		sut.isValid();
		assertNull("Validating error message if validation ok", sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnReceiverError() throws Exception {
		
		SmsValidator sut = new SmsValidator(getMessageWithInvalidReceiver());
		sut.isValid();
		assertEquals(
				"Validating error message if receiver has an error.",
				"Ungültiger Empfänger, nur Nummern erlaubt: 07845a67890",
				sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnMessageToLong() throws Exception {
		
		SmsValidator sut = new SmsValidator(getMessageWithAToLongMessage());
		sut.isValid();
		assertEquals(
				"Validating error message if message is to long.",
				"Die Nachricht hat 170/160 Zeichen.",
				sut.getErrorMessage());
	}

	private Message getValidMessage() {
		
		return new Message(
				new String[] {"0791234567","0784567890"},
				"My Message"
		);
	}

	private Message getMessageWithInvalidReceiver() {
		
		return new Message(
				new String[] {"0791234567","07845a67890"},
				"My Message",
				new Date()
		);
	}

	private Message getMessageWithAToLongMessage() {
		
		return new Message(
				new String[] {"0791234567","0784567890"},
				"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
				"1234567890123456789012345678901234567890123456789012345678901234567890",
				new Date()
		);
	}
}
