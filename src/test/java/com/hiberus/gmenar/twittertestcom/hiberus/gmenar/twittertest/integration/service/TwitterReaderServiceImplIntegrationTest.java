package com.hiberus.gmenar.twittertestcom.hiberus.gmenar.twittertest.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.gmenar.twittertest.AccionaServicioTwittertestApplication;
import com.hiberus.gmenar.twittertest.service.TwitterReaderService;

@SpringBootTest(classes = AccionaServicioTwittertestApplication.class)
public class TwitterReaderServiceImplIntegrationTest {

	@Autowired
	private TwitterReaderService twitterReaderService;

	@Test
	public void twitterAccesOk() throws Exception {

		assertThat(twitterReaderService.readTimeLine()).isNotEmpty();
	}
}
