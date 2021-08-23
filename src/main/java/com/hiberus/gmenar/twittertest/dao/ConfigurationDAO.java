package com.hiberus.gmenar.twittertest.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hiberus.gmenar.twittertest.entity.Configuration;

public interface ConfigurationDAO extends CrudRepository<Configuration, Long>{

	Optional<Configuration> findByKey(String key);
}
