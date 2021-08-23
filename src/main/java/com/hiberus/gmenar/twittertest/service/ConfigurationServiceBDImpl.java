package com.hiberus.gmenar.twittertest.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.gmenar.twittertest.dao.ConfigurationDAO;
import com.hiberus.gmenar.twittertest.entity.Configuration;

@Service(value = "configurationServiceBD")
public class ConfigurationServiceBDImpl implements ConfigurationService {

	private ConfigurationDAO configurationDAO;

	@Autowired
	public ConfigurationServiceBDImpl(ConfigurationDAO configurationDAO) {
		this.configurationDAO = configurationDAO;
	}

	@Override
	public String getLanguages() {
		return get("languages");
	}

	@Override
	public long getMinFollowers() {
		String value = get("minFollowers");
		return StringUtils.isBlank(value) ? null : Long.parseLong(value);
	}

	@Override
	public String[] getTracks() {
		String value = get("tracks");
		return StringUtils.isBlank(value) ? null : value.split(",");
	}

	private String get(String key) {
		Optional<Configuration> value = configurationDAO.findByKey(key);
		return value.isPresent() ? value.get().getValue() : null;
	}
}
