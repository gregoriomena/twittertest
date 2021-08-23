package com.hiberus.gmenar.twittertest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tweets_info")
public class TweetInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String user;

	@Column(nullable = false, length = 1000)
	private String message;

	@Column(nullable = true)
	private String location;

	@Column(nullable = false)
	private String valid;

	@ManyToMany
	@JoinTable(
			name = "tweets_info_hashtags",
			joinColumns = @JoinColumn(name = "tweets_info_id"),
			inverseJoinColumns = @JoinColumn(name = "hashtags_id"))
	private List<Hashtags> hashtags;

	public TweetInfo() {
	}

	public TweetInfo(String user, String message, String location, String valid, List<Hashtags> hashtags) {
		this.user = user;
		this.message = message;
		this.location = location;
		this.valid = valid;
		this.hashtags = hashtags;
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

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public List<Hashtags> getHashtags() {
		return hashtags;
	}
}
