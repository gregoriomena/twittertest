package com.hiberus.gmenar.twittertest.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
