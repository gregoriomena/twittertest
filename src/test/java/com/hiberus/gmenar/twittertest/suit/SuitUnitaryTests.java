package com.hiberus.gmenar.twittertest.suit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hiberus.gmenar.twittertest.controller.TweetInfoControllerTest;
import com.hiberus.gmenar.twittertest.service.ConfigurationServiceBDImplTest;
import com.hiberus.gmenar.twittertest.service.TweetInfoServiceImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TweetInfoControllerTest.class, TweetInfoServiceImplTest.class,
	ConfigurationServiceBDImplTest.class })
public class SuitUnitaryTests {

}
