package com.hiberus.gmenar.twittertest.service;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

public interface TwitterReaderService {

	List<Status> readTimeLine() throws TwitterException;
}
