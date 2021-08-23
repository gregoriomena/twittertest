package com.hiberus.gmenar.twittertest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hiberus.gmenar.twittertest.entity.Hashtags;

public interface HashtagsDAO extends CrudRepository<Hashtags, Long>{

	Optional<Hashtags> findByHashtag(String hastag);

	@Query("select h.hashtag, count(1) from Hashtags h join h.tweets GROUP BY h.hashtag ORDER BY count(1) DESC")
	List<Object[]> mostUsedHashtags(@Param("size") int size, Pageable pageable);
}
