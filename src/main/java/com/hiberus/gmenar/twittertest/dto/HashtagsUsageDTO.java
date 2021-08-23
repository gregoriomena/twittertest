package com.hiberus.gmenar.twittertest.dto;

public class HashtagsUsageDTO {
	private Long count;
	private String hashtag;

	public HashtagsUsageDTO(String hashtag, Long count) {
		this.count = count;
		this.hashtag = hashtag;
	}

	public Long getCount() {
		return count;
	}

	public String getHashtag() {
		return hashtag;
	}

}
