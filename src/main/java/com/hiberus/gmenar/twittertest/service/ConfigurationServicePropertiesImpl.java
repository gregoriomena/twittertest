package com.hiberus.gmenar.twittertest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "configurationServiceProperties")
public class ConfigurationServicePropertiesImpl implements ConfigurationService {

	@Value("${twitter.languages}")
	private String languages;

	@Value("${twitter.minFollowers}")
	private long minFollowers;

	@Value("${twitter.tracks}")
	private String[] tracks;

	@Override
	public String getLanguages() {
		return languages;
	}

	@Override
	public Long getMinFollowers() {
		return minFollowers;
	}

	@Override
	public String[] getTracks() {
		return tracks;
	}
}
