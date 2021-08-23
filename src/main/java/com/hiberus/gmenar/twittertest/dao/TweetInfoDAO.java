package com.hiberus.gmenar.twittertest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hiberus.gmenar.twittertest.entity.TweetInfo;

public interface TweetInfoDAO extends PagingAndSortingRepository<TweetInfo, Long>{

	@Query("select t from TweetInfo t where lower(t.user) like lower(concat('%', :user,'%'))")
	Page<TweetInfo> findAllByUserIgnoreCase(@Param("user") String user, Pageable pagingSort);

}
