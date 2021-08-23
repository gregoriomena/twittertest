package com.hiberus.gmenar.twittertest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.hiberus.gmenar.twittertest.service.TweetInfoService;

public class TweetInfoControllerTest {

	@Test
	public void searchTweets() {

		TweetInfoService tweetInfoService = mock(TweetInfoService.class);

		TweetInfoController tweetInfoController = new TweetInfoController(tweetInfoService);
		tweetInfoController.search(1, 10, new String[] { "id,desc" });

		verify(tweetInfoService).findAll(any());

	}

	@Test
	public void markAsValidated() {

		TweetInfoService tweetInfoService = mock(TweetInfoService.class);

		TweetInfoController tweetInfoController = new TweetInfoController(tweetInfoService);
		tweetInfoController.markAsValidated(10L);

		verify(tweetInfoService).markAsValidated(10L);

	}
}
