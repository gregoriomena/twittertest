package com.hiberus.gmenar.twittertest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;
import com.hiberus.gmenar.twittertest.service.TweetInfoService;

@RestController
public class TweetInfoController {

	private TweetInfoService tweetInfoService;

	@Autowired
	public TweetInfoController(TweetInfoService tweetInfoService) {
		this.tweetInfoService = tweetInfoService;
	}

	@GetMapping("/tweets")
	public List<TweetInfoDTO> search(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "id,desc") String[] sort) {

		List<Order> orders = new ArrayList<Order>();
		if (sort[0].contains(",")) {
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
			}
		} else {
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}

		Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
		return tweetInfoService.findAll(pagingSort);
	}

	@PutMapping("/tweet/{id}")
	public void markAsValidated(@PathVariable Long id) {
		tweetInfoService.markAsValidated(id);
	}

	private Direction getSortDirection(String sortDirection) {
		return sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.DESC;
	}
}
