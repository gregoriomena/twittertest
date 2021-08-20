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

		List<Status> tweets = makeStatusMockByFollowers(Arrays.asList(1501, 600, 2000));

		TwitterFilterService twitterFilterService = new TwitterFilterServiceImpl(1500, "");
		assertThat(twitterFilterService.filterByFollowers(tweets)).isNotEmpty().hasSize(2);
	}

	@Test
	public void filterByLanguages() {

		String undefineLanguage = "und";
		List<Status> tweets = makeStatusMockByLanguages(Arrays.asList("es", "en", "en", "da", undefineLanguage));

		TwitterFilterService twitterFilterService = new TwitterFilterServiceImpl(null, "es,en");
		assertThat(twitterFilterService.filterByLang(tweets)).isNotEmpty().hasSize(3);
	}

	@Test
	public void filterByLanguagesOnlyOneLanguageInFilter () {

		List<Status> tweets = makeStatusMockByLanguages(Arrays.asList("es", "en", "en", "da"));

		TwitterFilterService twitterFilterService = new TwitterFilterServiceImpl(null, "es");
		assertThat(twitterFilterService.filterByLang(tweets)).isNotEmpty().hasSize(1);
	}

	private List<Status> makeStatusMockByLanguages(List<String> languages) {
		return languages.stream().map(l -> {
			Status status = mock(Status.class);
			when(status.getLang()).thenReturn(l);
			return status;
		}).collect(Collectors.toList());
	}

	private static List<Status> makeStatusMockByFollowers(List<Integer> followers) {
		return followers.stream().map(f -> {
			Status status = mock(Status.class);
			User user = mock(User.class);
			when(status.getUser()).thenReturn(user);
			when(user.getFollowersCount()).thenReturn(f);
			return status;
		}).collect(Collectors.toList());
	}
}
