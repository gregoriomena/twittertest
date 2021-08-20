package com.hiberus.gmenar.twittertest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class TwitterReaderServiceImpl implements TwitterReaderService {

	public List<Status> readTimeLine() throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		ResponseList<Status> statuses = twitter.getHomeTimeline();

		List<Status> result = statuses.stream().collect(Collectors.toList());
		return result;
	}

}
