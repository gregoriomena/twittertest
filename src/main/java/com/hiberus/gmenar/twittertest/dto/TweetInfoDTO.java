package com.hiberus.gmenar.twittertest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hiberus.gmenar.twittertest.entity.Hashtags;
import com.hiberus.gmenar.twittertest.entity.TweetInfo;

import twitter4j.HashtagEntity;
import twitter4j.Status;

public class TweetInfoDTO {

	private Long id;
	private String user;
	private String message;
	private String location;
	private boolean valid;
	private List<HashtagsDTO> hashtags;

	public TweetInfoDTO() {
	}

	public TweetInfoDTO(String user, String message, String location, boolean valid) {
		this.user = user;
		this.message = message;
		this.location = location;
		this.valid = valid;

		hashtags = new ArrayList<HashtagsDTO>();
	}

	public TweetInfoDTO(TweetInfo tweetInfo) {
		id = tweetInfo.getId();
		user = tweetInfo.getUser();
		message = tweetInfo.getMessage();
		location = tweetInfo.getLocation();
		valid = tweetInfo.getValid().equals("Y");

		if (tweetInfo.getHashtags() != null) {
			hashtags = tweetInfo.getHashtags().stream().map(h -> {
				return new HashtagsDTO(h);

			}).collect(Collectors.toList());
		}

	}

	public TweetInfoDTO(Status status) {
		user = status.getUser().getName();
		message = status.getText();
		location = status.getUser().getLocation();
		valid = false;
		hashtags = new ArrayList<HashtagsDTO>();

		if (status.getHashtagEntities() != null) {
			for (HashtagEntity iter : status.getHashtagEntities()) {
				HashtagsDTO dto = new HashtagsDTO(iter.getText());
				hashtags.add(dto);
			}
		}
	}

	public TweetInfo generateBO(List<Hashtags> hashtags) {

		return new TweetInfo(user, message, location, validLabel(), hashtags);
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

	public List<HashtagsDTO> getHashtags() {
		return hashtags;
	}

}
