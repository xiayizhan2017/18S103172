/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		Instant min, max;
		Timespan result = null;
		if (tweets.size() == 0)
			return result;
		min = tweets.get(0).getTimestamp();
		max = tweets.get(0).getTimestamp();
		for (Tweet it : tweets) {
			if (it.getTimestamp().isAfter(max))
				max = it.getTimestamp();
			if (it.getTimestamp().isBefore(min))
				min = it.getTimestamp();
		}
		result = new Timespan(min, max);
		return result;
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
		Set<String> result = new HashSet<String>();
		for (Tweet it : tweets) {
			for (String s : it.getText().split(" ")) {
				if (s.startsWith("@")) {
					result.add(s.substring(1));
				}
			}
		}
		return result;
	}
}
