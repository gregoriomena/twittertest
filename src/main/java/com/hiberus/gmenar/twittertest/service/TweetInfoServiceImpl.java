package com.hiberus.gmenar.twittertest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hiberus.gmenar.twittertest.dao.TweetInfoDAO;
import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;
import com.hiberus.gmenar.twittertest.entity.TweetInfo;

@Service
public class TweetInfoServiceImpl implements TweetInfoService {

	@Autowired
	private TweetInfoDAO tweetInfoDAO;

	@Override
	public TweetInfoDTO create(TweetInfoDTO tweetInfo) {

		tweetInfo.generateBO();
		TweetInfo tweetInfoBO = tweetInfoDAO.save(tweetInfo.generateBO());
		return new TweetInfoDTO(tweetInfoBO);
	}

	@Override
	public List<TweetInfoDTO> findAll(Pageable pagingSort) {

		Page<TweetInfo> tweetsBO = tweetInfoDAO.findAll(pagingSort);
		return tweetsBO.stream().map(tweet -> {
			return new TweetInfoDTO(tweet);
		}).collect(Collectors.toList());
	}

}
