package ch.zhaw.multiChannel.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SmsValidatorTest.class, MmsValidatorTest.class })
public class TestSuite {

}
