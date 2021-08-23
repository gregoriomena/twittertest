package com.hiberus.gmenar.twittertest.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hiberus.gmenar.twittertest.AccionaServicioTwittertestApplication;
import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;
import com.hiberus.gmenar.twittertest.service.TweetInfoService;

@SpringBootTest(classes = AccionaServicioTwittertestApplication.class)
public class TweetInfoServiceImplIntegrationTest {

	@Autowired
	private TweetInfoService tweetInfoService;

	@Test
	public void createMethodGeneratesAnId() {

		TweetInfoDTO tweetInfo = new TweetInfoDTO("TestUser", "TestMessage", "TestLocation", false);
		tweetInfo = tweetInfoService.create(tweetInfo);
		assertThat(tweetInfo.getId()).isNotNull();
	}

	@Test
	public void createMethodGeneratesDifferentIds() {

		TweetInfoDTO tweetInfo = new TweetInfoDTO("TestUser", "TestMessage", "TestLocation", false);
		TweetInfoDTO tweetInfoFirst = tweetInfoService.create(tweetInfo);
		TweetInfoDTO tweetInfoSecond = tweetInfoService.create(tweetInfo);

		assertThat(tweetInfoFirst.getId()).isNotEqualTo(tweetInfoSecond.getId());
	}

	@Test
	public void searchTest() {

		createMethodGeneratesAnId();
		Pageable pagingSort = PageRequest.of(0, 5, Sort.by("id"));
		assertThat(tweetInfoService.findAll(pagingSort)).isNotEmpty();
	}

}
