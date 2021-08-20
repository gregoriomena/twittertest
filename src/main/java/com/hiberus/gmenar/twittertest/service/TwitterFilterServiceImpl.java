package com.hiberus.gmenar.twittertest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import twitter4j.Status;

public class TwitterFilterServiceImpl implements TwitterFilterService {

	private Integer minFollowers;

	public TwitterFilterServiceImpl(@Value("${twitter.minFollowers}")Integer minFollowers) {
		this.minFollowers = minFollowers;
	}


	@Override
	public List<Status> filterByFollowers(List<Status> tweets) {
		return tweets.stream().filter(tweet -> tweet.getUser().getFollowersCount() > minFollowers).collect(Collectors.toList());
	}

}
