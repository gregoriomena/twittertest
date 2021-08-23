package com.hiberus.gmenar.twittertest.dto;

import com.hiberus.gmenar.twittertest.entity.TweetInfo;

import twitter4j.Status;

public class TweetInfoDTO {

	private Long id;
	private String user;
	private String message;
	private String location;
	private boolean valid;

	public TweetInfoDTO() {
	}

	public TweetInfoDTO(String user, String message, String location, boolean valid) {
		this.user = user;
		this.message = message;
		this.location = location;
		this.valid = valid;
	}

	public TweetInfoDTO(TweetInfo tweetInfo) {
		id = tweetInfo.getId();
		user = tweetInfo.getUser();
		message = tweetInfo.getMessage();
		location = tweetInfo.getLocation();
		valid = tweetInfo.getValid().equals("Y");
	}

	public TweetInfoDTO(Status status) {
		user = status.getUser().getName();
		message = status.getText();
		location = status.getUser().getLocation();
		valid = false;
	}

	public TweetInfo generateBO() {

		return new TweetInfo(user, message, location, validLabel());
	}

	public String validLabel() {
		return (valid) ? "Y" : "N";
	}

	public Long getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public String getLocation() {
		return location;
	}

	public boolean isValid() {
		return valid;
	}

}
