package com.hiberus.gmenar.twittertest;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class ListenerTwitter implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${twitter.languages}")
	private String languages;

	@Value("${twitter.minFollowers}")
	private long minFollowers;

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

		String[] tracks = { "COVID-19" };
		query.track(tracks);

		query.language(languages.split(","));

		String pattern = "dd-MM-yyyy HH:mm:ssZ";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		StatusListener listener = new StatusListener() {

			@Override
			public void onStatus(Status status) {

				if (status.getUser().getFollowersCount() >= minFollowers && status.getHashtagEntities().length > 0) {

					System.out.println("#onStatus INI .............................................................");
					System.out.println(status.getUser().getName() + " - " + status.getUser().getScreenName() + " - "
							+ status.getUser().getFollowersCount() + " Followers");
					System.out.println(status.getLang());
					System.out.println(status.getId() + " - " + dateFormat.format(status.getCreatedAt()));

					showHashTag(status);

					System.out.println(status.getText());

					System.out.println("#onStatus FIN .............................................................");
				}
			}

			private void showHashTag(Status status) {

				String hashTagsLabel = "";

				HashtagEntity[] hashtagEntities = status.getHashtagEntities();
				for (HashtagEntity iterHashtag : hashtagEntities) {
					hashTagsLabel += ", #" + iterHashtag.getText();
				}

				if (!hashTagsLabel.isEmpty()) {
					System.out.println("Hashtag: " + hashTagsLabel.substring(2));
				}
				else {
					System.out.println("Hashtag: N/A");
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
