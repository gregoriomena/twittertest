package com.hiberus.gmenar.twittertest.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.gmenar.twittertest.AccionaServicioTwittertestApplication;
import com.hiberus.gmenar.twittertest.dto.HashtagsDTO;
import com.hiberus.gmenar.twittertest.dto.HashtagsUsageDTO;
import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;
import com.hiberus.gmenar.twittertest.service.HashtagsService;
import com.hiberus.gmenar.twittertest.service.TweetInfoService;

@SpringBootTest(classes = AccionaServicioTwittertestApplication.class)
public class HashtagsServiceImplIntegrationTest {

	@Autowired
	private HashtagsService hashtagsService;

	@Autowired
	private TweetInfoService tweetInfoService;

	@Test
	public void generarHashTag() {

		assertThat(hashtagsService.findOrCreateIfNoExists("Nuevo").getId()).isNotNull();
	}

	@Test
	public void count() {

		HashtagsDTO hashtag1 = new HashtagsDTO("tagtestA");
		HashtagsDTO hashtag2 = new HashtagsDTO("tagtestB");

		saveTweet(hashtag1);
		saveTweet(hashtag1);
		saveTweet(hashtag2);

		List<HashtagsUsageDTO> mostUsedHashtags = hashtagsService.mostUsedHashtags(1);

		assertThat(mostUsedHashtags).isNotNull().isNotEmpty();
		assertThat(mostUsedHashtags.get(0).getHashtag()).isEqualTo("tagtestA");
	}

	private void saveTweet(HashtagsDTO hashtag1) {
		TweetInfoDTO tweetInfo = new TweetInfoDTO("TestUser", "TestMessage", "TestLocation", false);
		tweetInfo.getHashtags().add(hashtag1);
		tweetInfoService.create(tweetInfo);
	}
}
