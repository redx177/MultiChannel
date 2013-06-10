package ch.zhaw.multiChannel.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SmsValidatorTest.class, MmsValidatorTest.class, FaxValidatorTest.class, EmailValidatorTest.class })
public class TestSuite {

}
