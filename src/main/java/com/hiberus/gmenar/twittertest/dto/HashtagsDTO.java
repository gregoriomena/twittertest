package com.hiberus.gmenar.twittertest.dto;

import com.hiberus.gmenar.twittertest.entity.Hashtags;

public class HashtagsDTO {
	private Long id;
	private String hashtag;

	public HashtagsDTO(Hashtags hashtags) {
		id = hashtags.getId();
		hashtag = hashtags.getHashtag();
	}

	public HashtagsDTO(String hashtag) {
		this.hashtag = hashtag;
	}

	public Long getId() {
		return id;
	}

	public String getHashtag() {
		return hashtag;
	}


}
