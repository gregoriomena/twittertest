package com.hiberus.gmenar.twittertest.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TwitterReaderServiceImplIntegrationTest {

	@Autowired
	private TwitterReaderService twitterReaderService;

	@Test
	public void twitterAccesOk() throws Exception {

		assertThat(twitterReaderService.readTimeLine()).isNotEmpty();
	}
}
