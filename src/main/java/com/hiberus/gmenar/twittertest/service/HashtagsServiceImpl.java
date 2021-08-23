package com.hiberus.gmenar.twittertest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hiberus.gmenar.twittertest.dao.HashtagsDAO;
import com.hiberus.gmenar.twittertest.dto.HashtagsUsageDTO;
import com.hiberus.gmenar.twittertest.entity.Hashtags;

@Service
public class HashtagsServiceImpl implements HashtagsService {

	@Autowired
	private HashtagsDAO hashtagsDAO;

	@Override
	public Hashtags findOrCreateIfNoExists(String hashtag) {

		Hashtags hashtagBO = null;
		Optional<Hashtags> optionalResult = hashtagsDAO.findByHashtag(hashtag);
		if (optionalResult.isPresent()) {
			hashtagBO = optionalResult.get();
		}
		else {
			hashtagBO = new Hashtags(hashtag);
			hashtagBO = hashtagsDAO.save(hashtagBO);
		}

		return hashtagBO;
	}

	@Override
	public List<HashtagsUsageDTO> mostUsedHashtags(int size) {

		List<HashtagsUsageDTO> result = new ArrayList<HashtagsUsageDTO>();


		Pageable pagingSort = PageRequest.of(0, size);

		List<Object[]> mostUsedHashtags = hashtagsDAO.mostUsedHashtags(size, pagingSort);

		for (Object[] iterResult : mostUsedHashtags) {
			result.add(new HashtagsUsageDTO((String) iterResult[0], (Long) iterResult[1]));
		}

		return result;
	}

}
