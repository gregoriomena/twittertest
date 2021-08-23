package com.hiberus.gmenar.twittertest.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.hiberus.gmenar.twittertest.dao.ConfigurationDAO;

public class ConfigurationServiceBDImplTest {

	private ConfigurationService configurationService;
	private ConfigurationDAO dao;

	@Before
	public void init() {
		dao = mock(ConfigurationDAO.class);
		configurationService  = new ConfigurationServiceBDImpl(dao);
	}

	@Test
	public void getLanguages() {
		configurationService.getLanguages();
		verify(dao).findByKey("languages");
	}

	@Test
	public void getMinFollowers() {
		configurationService.getMinFollowers();
		verify(dao).findByKey("minFollowers");
	}

	@Test
	public void getTracks() {
		configurationService.getTracks();
		verify(dao).findByKey("tracks");
	}
}
