package com.hiberus.gmenar.twittertest.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hiberus.gmenar.twittertest.dto.TweetInfoDTO;
import com.hiberus.gmenar.twittertest.service.ConfigurationService;
import com.hiberus.gmenar.twittertest.service.TweetInfoService;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class ListenerTwitter implements ApplicationListener<ApplicationReadyEvent> {

	private ConfigurationService configurationService;
	private TweetInfoService tweetInfoService;

	@Autowired
	public ListenerTwitter(ConfigurationService configurationService, TweetInfoService tweetInfoService) {
		this.configurationService = configurationService;
		this.tweetInfoService = tweetInfoService;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Se inicia.....");
		try {
			initListener();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void initListener() throws TwitterException {

		FilterQuery query = new FilterQuery();

		query.track(configurationService.getTracks());

		query.language(configurationService.getLanguages().split(","));

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		StatusListener listener = new StatusListener() {

			@Override
			public void onStatus(Status status) {

				if (status.getUser().getFollowersCount() >= configurationService.getMinFollowers() && status.getHashtagEntities().length > 0) {

					TweetInfoDTO tweet = new TweetInfoDTO(status);
					tweetInfoService.create(tweet);
				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println(
						"#onDeletionNotice - Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("#onDeletionNotice - Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println(
						"#onDeletionNotice - Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			@Override
			public void onException(Exception ex) {
				System.out.println("#onException");
				ex.printStackTrace();
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("#onStallWarning");
			}
		};
		twitterStream.addListener(listener);
		twitterStream.filter(query);
	}
}
