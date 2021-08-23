package com.hiberus.gmenar.twittertest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.gmenar.twittertest.dto.HashtagsUsageDTO;
import com.hiberus.gmenar.twittertest.service.HashtagsService;

@RestController
public class HashtagController {

	private HashtagsService hashtagsService;

	@Autowired
	public HashtagController(HashtagsService hashtagsService) {
		this.hashtagsService = hashtagsService;
	}

	@GetMapping("/mostUsedHashtags")
	public List<HashtagsUsageDTO> search(@RequestParam(defaultValue = "10") int size) {

		return hashtagsService.mostUsedHashtags(size);
	}

}
