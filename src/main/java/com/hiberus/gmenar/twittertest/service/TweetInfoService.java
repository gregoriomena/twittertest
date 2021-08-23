package com.hiberus.gmenar.twittertest.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;

public interface TweetInfoService {

	TweetInfoDTO create(TweetInfoDTO tweetInfo);

	List<TweetInfoDTO> findAll(Pageable pagingSort);
}
