package com.redmart.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// This is the only suite I have created, For better Test management we can create multiple suites and configure using Surefire plugin
@RunWith(Suite.class)
@SuiteClasses({ 
		Test001_RedMartSignup.class,
		Test002_Redmart_Login.class
		
})

public class TestSuite {

}
