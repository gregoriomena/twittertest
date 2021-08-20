package com.hiberus.gmenar.twittertest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import twitter4j.Status;
import twitter4j.User;

public class TwitterFilterServiceImplTest {

	@Test
	public void filterByFollowers() {

		List<Status> tweets = makeStatusMock(Arrays.asList(1501, 600, 2000));

		TwitterFilterService twitterFilterService = new TwitterFilterServiceImpl(1500);
		assertThat(twitterFilterService.filterByFollowers(tweets)).isNotEmpty().hasSize(2);
	}

	private static List<Status> makeStatusMock(List<Integer> followers) {
		return followers.stream().map(f -> {
			Status status = mock(Status.class);
			User user = mock(User.class);
			when(status.getUser()).thenReturn(user);
			when(user.getFollowersCount()).thenReturn(f);
			return status;
		}).collect(Collectors.toList());
	}
}
