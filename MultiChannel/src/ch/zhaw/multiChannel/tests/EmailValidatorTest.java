/*
 * Class EmailValidatorTest
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

import ch.zhaw.multiChannel.model.AttachmentMessage;
import ch.zhaw.multiChannel.model.EmailValidator;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class EmailValidatorTest {

	@Test
	public void testValidationOfValidMessage() throws Exception {

		EmailValidator sut = new EmailValidator(getValidMessage());
		assertTrue("Validating valid message", sut.isValid());
	}

	@Test
	public void testMessageWithInvalidReceiver() throws Exception {

		EmailValidator sut = new EmailValidator(getMessageWithInvalidReceiver());
		assertFalse("Message with invalid receiver", sut.isValid());
	}

	@Test
	public void testMessageWithAnIllegalFile() throws Exception {

		EmailValidator sut = new EmailValidator(getMessageWithAnIllegalFile());
		assertFalse("Message with a illegal file", sut.isValid());
	}

	@Test
	public void testIfErrorMessageIsEmptyIfValidationOk() throws Exception {

		EmailValidator sut = new EmailValidator(getValidMessage());
		sut.isValid();
		assertNull("Validating error message if validation ok", sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnReceiverError() throws Exception {

		EmailValidator sut = new EmailValidator(getMessageWithInvalidReceiver());
		sut.isValid();
		assertEquals(
				"Validating error message if receiver has an error.",
				"Ungültige EMail: redlobster.com",
				sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnAnIllegalFile() throws Exception {

		EmailValidator sut = new EmailValidator(getMessageWithAnIllegalFile());
		sut.isValid();
		assertEquals(
				"Validating error message if message is to long.",
				"Ungültiges File (erlaubt ist alles ausser .exe): foo.exe",
				sut.getErrorMessage());
	}

	private AttachmentMessage getValidMessage() {

		return new AttachmentMessage(
				new String[]{"red@lobster.com", "blue@panda.net"},
				"My Message",
				new Date(),
				getValidFiles()
		);
	}

	private AttachmentMessage getMessageWithInvalidReceiver() {

		return new AttachmentMessage(
				new String[]{"redlobster.com", "blue@panda.net"},
				"My Message",
				new Date(),
				getValidFiles()
		);
	}

	private AttachmentMessage getMessageWithAnIllegalFile() {

		return new AttachmentMessage(
				new String[]{"red@lobster.com", "blue@panda.net"},
				"My Message",
				new Date(),
				getInvalidValidFiles()
		);
	}

	private ArrayList<File> getValidFiles() {

		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("foo.gif"));
		files.add(new File("bar.txt"));
		return files;
	}

	private ArrayList<File> getInvalidValidFiles() {

		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("foo.exe"));
		files.add(new File("bar.jpg"));
		return files;
	}
}
