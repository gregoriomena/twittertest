package com.hiberus.gmenar.twittertest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hiberus.gmenar.twittertest.entity.TweetInfo;

public interface TweetInfoDAO extends PagingAndSortingRepository<TweetInfo, Long>{

}
