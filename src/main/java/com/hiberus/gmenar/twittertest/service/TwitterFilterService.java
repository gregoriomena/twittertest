package com.hiberus.gmenar.twittertest.service;

import java.util.List;

import twitter4j.Status;

public interface TwitterFilterService {

	List<Status> filterByFollowers(List<Status> tweets);
	List<Status> filterByLang(List<Status> tweets);
}
