package com.hiberus.gmenar.twittertest.dao;

import org.springframework.data.repository.CrudRepository;

import com.hiberus.gmenar.twittertest.entity.TweetInfo;

public interface TweetInfoDAO extends CrudRepository<TweetInfo, Long>{

}
