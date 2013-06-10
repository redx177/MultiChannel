package ch.zhaw.multiChannel.tests;

import ch.zhaw.multiChannel.model.AttachmentMessage;
import ch.zhaw.multiChannel.model.Message;
import ch.zhaw.multiChannel.model.MmsValidator;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class MmsValidatorTest {

	@Test
	public void testValidationOfValidMessage() throws Exception {
		MmsValidator sut = new MmsValidator(getValidMessage());
		assertTrue("Validating valid message", sut.isValid());
	}

	@Test
	public void testMessageWithInvalidReceiver() throws Exception {
		MmsValidator sut = new MmsValidator(getMessageWithInvalidReceiver());
		assertFalse("Message with invalid receiver", sut.isValid());
	}

	@Test
	public void testMessageWithAnIllegalFile() throws Exception {
		MmsValidator sut = new MmsValidator(getMessageWithAnIllegalFile());
		assertFalse("Message with a illegal file", sut.isValid());
	}

	@Test
	public void testIfErrorMessageIsEmptyIfValidationOk() throws Exception {
		MmsValidator sut = new MmsValidator(getValidMessage());
		sut.isValid();
		assertNull("Validating error message if validation ok", sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnReceiverError() throws Exception {
		MmsValidator sut = new MmsValidator(getMessageWithInvalidReceiver());
		sut.isValid();
		assertEquals(
				"Validating error message if receiver has an error.",
				"Ungültiger Empfänger, nur Nummern erlaubt: 07845a67890",
				sut.getErrorMessage());
	}

	@Test
	public void testErrorMessageOnAnIllegalFile() throws Exception {
		MmsValidator sut = new MmsValidator(getMessageWithAnIllegalFile());
		sut.isValid();
		assertEquals(
				"Validating error message if message is to long.",
				"Ungültiges File: foo.exe",
				sut.getErrorMessage());
	}

	private AttachmentMessage getValidMessage() {
		return new AttachmentMessage(
				new String[] {"0791234567","0784567890"},
				"My Message",
				new Date(),
				getValidFiles()
		);
	}

	private AttachmentMessage getMessageWithInvalidReceiver() {
		return new AttachmentMessage(
				new String[] {"0791234567","07845a67890"},
				"My Message",
				new Date(),
				getValidFiles()
		);
	}

	private AttachmentMessage getMessageWithAnIllegalFile() {
		return new AttachmentMessage(
				new String[] {"0791234567","0784567890"},
				"My Message",
				new Date(),
				getInvalidValidFiles()
		);
	}

	private ArrayList<File> getValidFiles() {
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("foo.gif"));
		files.add(new File("bar.jpg"));
		return files;
	}

	private ArrayList<File> getInvalidValidFiles() {
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("foo.exe"));
		files.add(new File("bar.jpg"));
		return files;
	}
}
