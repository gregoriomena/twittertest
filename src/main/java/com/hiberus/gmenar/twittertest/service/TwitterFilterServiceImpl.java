package com.hiberus.gmenar.twittertest.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import twitter4j.Status;

public class TwitterFilterServiceImpl implements TwitterFilterService {

	private Integer minFollowers;
	private List<String> languages;

	public TwitterFilterServiceImpl(@Value("${twitter.minFollowers}")Integer minFollowers, @Value("${twitter.languages}")String languages) {
		this.minFollowers = minFollowers;
		this.languages = Arrays.asList(languages.split(","));
	}


	@Override
	public List<Status> filterByFollowers(List<Status> tweets) {
		return tweets.stream().filter(tweet -> tweet.getUser().getFollowersCount() > minFollowers).collect(Collectors.toList());
	}


	@Override
	public List<Status> filterByLang(List<Status> tweets) {
		return tweets.stream().filter(tweet -> languages.contains(tweet.getLang())).collect(Collectors.toList());
	}

}
