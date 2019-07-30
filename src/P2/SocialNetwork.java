/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets a list of tweets providing the evidence, not modified by this
	 *               method.
	 * @return a social network (as defined above) in which Ernie follows Bert if
	 *         and only if there is evidence for it in the given list of tweets. One
	 *         kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
	 *            may be used at the implementor's discretion. All the Twitter
	 *            usernames in the returned social network must be either authors
	 *            or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		Map<String, Set<String>> result = new HashMap<String, Set<String>>();
		for (Tweet it : tweets) {
			Set<String> iSet = new HashSet<String>();
			for (String s : it.getText().split(" "))
				if (s.startsWith("@"))
					iSet.add(s.substring(1).toLowerCase());
			if (result.containsKey(it.getAuthor()))
				result.get(it.getAuthor()).addAll(iSet);
			else
				result.put(it.getAuthor().toLowerCase(), iSet);
		}
		
		return result;
	}

	/**
	 * Find the people in a social network who have the greatest influence, in the
	 * sense that they have the most followers.
	 * 
	 * @param followsGraph a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {
		List<String> result = new ArrayList<String>();
		HashMap<String, Integer> mid = new HashMap<String, Integer>();
		int flag = 0;
		for (String key : followsGraph.keySet()) {
			for(String it:followsGraph.get(key))
				if(mid.containsKey(it)) {
					int num = mid.get(it) + 1;
					mid.put(it, num);
				}
				else
					mid.put(it,1);
		}
		while (flag > -1) {
			flag = -1;
			String recString = null;
			for (String key : mid.keySet()) {
				if (mid.get(key) > flag) {
					flag = mid.get(key);
					recString = key;
				}
			}
			if (flag > -1) {
				mid.put(recString, -1);
				result.add(recString);
			}
		}
		return result;
	}
}
