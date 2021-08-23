package com.hiberus.gmenar.twittertest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hiberus.gmenar.twittertest.dao.TweetInfoDAO;
import com.hiberus.gmenar.twittertest.dto.HashtagsDTO;
import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;
import com.hiberus.gmenar.twittertest.entity.Hashtags;
import com.hiberus.gmenar.twittertest.entity.TweetInfo;

@Service
public class TweetInfoServiceImpl implements TweetInfoService {

	private TweetInfoDAO tweetInfoDAO;
	private HashtagsService hashtagsService;

	@Autowired
	public TweetInfoServiceImpl(TweetInfoDAO tweetInfoDAO, HashtagsService hashtagsService) {
		this.tweetInfoDAO = tweetInfoDAO;
		this.hashtagsService = hashtagsService;
	}

	@Override
	public TweetInfoDTO create(TweetInfoDTO tweetInfo) {

		List<Hashtags> hashtags = new ArrayList<Hashtags>();
		for (HashtagsDTO iterHashTag : tweetInfo.getHashtags()){
			Hashtags hashtag = hashtagsService.findOrCreateIfNoExists(iterHashTag.getHashtag());
			hashtags.add(hashtag);
		}

		TweetInfo tweetInfoBO = tweetInfo.generateBO(hashtags);

		tweetInfoBO = tweetInfoDAO.save(tweetInfoBO);
		return new TweetInfoDTO(tweetInfoBO);
	}

	@Override
	public List<TweetInfoDTO> findAll(String user, Pageable pagingSort) {

		Page<TweetInfo> tweetsBO = null;
		if (StringUtils.isBlank(user)) {
			tweetsBO = tweetInfoDAO.findAll(pagingSort);
		}
		else {
			tweetsBO = tweetInfoDAO.findAllByUserIgnoreCase(user, pagingSort);
		}

		return tweetsBO.stream().map(tweet -> {
			return new TweetInfoDTO(tweet);
		}).collect(Collectors.toList());
	}

	@Override
	public void markAsValidated(Long id) {

		TweetInfo tweet = tweetInfoDAO.findById(id).get();
		tweet.setValid("Y");
		tweetInfoDAO.save(tweet);
	}

}
