package com.hiberus.gmenar.twittertest.service;

import java.util.List;

import com.hiberus.gmenar.twittertest.dto.HashtagsUsageDTO;
import com.hiberus.gmenar.twittertest.entity.Hashtags;

public interface HashtagsService {

	Hashtags findOrCreateIfNoExists(String hashtag);

	List<HashtagsUsageDTO> mostUsedHashtags(int size);

}
