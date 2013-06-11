/*
 * Class TestSuite
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class will call the other Test Methods
 *
 * Copyright ZHAW 2013
 *
*/

package ch.zhaw.multiChannel.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SmsValidatorTest.class, MmsValidatorTest.class, FaxValidatorTest.class, EmailValidatorTest.class})

public class TestSuite {

}
