package com.hiberus.gmenar.twittertest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hashtags")
public class Hashtags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String hashtag;

	@ManyToMany(mappedBy = "hashtags")
	private List<TweetInfo> tweets;

	public Hashtags() {
	}

	public Hashtags(String hashtag) {
		this.hashtag = hashtag;
	}

	public Long getId() {
		return id;
	}

	public String getHashtag() {
		return hashtag;
	}

	public List<TweetInfo> getTweets() {
		return tweets;
	}
}
