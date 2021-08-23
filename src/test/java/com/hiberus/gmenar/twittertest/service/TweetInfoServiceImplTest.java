package com.hiberus.gmenar.twittertest.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

import com.hiberus.gmenar.twittertest.dao.TweetInfoDAO;
import com.hiberus.gmenar.twittertest.entity.TweetInfo;

public class TweetInfoServiceImplTest {

	@Test
	public void markAsValidated() {
		TweetInfoDAO tweetInfoDAO = mock(TweetInfoDAO.class);

		TweetInfo tweet = mock(TweetInfo.class);

		when(tweetInfoDAO.findById(10L)).thenReturn(Optional.of(tweet));

		TweetInfoServiceImpl tweetInfoService = new TweetInfoServiceImpl(tweetInfoDAO, null);
		tweetInfoService.markAsValidated(10L);

		verify(tweetInfoDAO).findById(10L);
		verify(tweetInfoDAO).save(any());
		verify(tweet).setValid("Y");

	}
}
