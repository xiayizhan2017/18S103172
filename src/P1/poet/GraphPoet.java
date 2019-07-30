/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

	private final Graph<String> graph = Graph.empty();

	// Abstraction function:
	// TODO
	// Representation invariant:
	// TODO
	// Safety from rep exposure:
	// TODO

	/**
	 * Create a new poet with the graph from corpus (as described above).
	 * 
	 * @param corpus text file from which to derive the poet's affinity graph
	 * @throws IOException if the corpus file cannot be found or read
	 */
	public GraphPoet(File corpus) throws IOException {
		FileReader itFileReader = new FileReader(corpus);
		try (BufferedReader reader = new BufferedReader(itFileReader)) {
			String line;
			List<String> saveList = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				line = line.replace('.', ' ');
				line = line.replace(',', ' ');
				String[] words = line.split("\\s+");
				int len = words.length;
				int[][] matrix = new int[len][len];
				for (int i = 0; i < len - 1; i++) {
					String str1 = words[i].toLowerCase();
					String str2 = words[i + 1].toLowerCase();
					if (saveList.indexOf(str1) < 0)
						saveList.add(str1);
					if (saveList.indexOf(str2) < 0)
						saveList.add(str2);
					int num1 = saveList.indexOf(str1);
					int num2 = saveList.indexOf(str2);
					graph.set(str1, str2, matrix[num1][num2] + 1);
					matrix[num1][num2] += 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO checkRep
 
	/**
	 * Generate a poem.
	 * 
	 * @param input string from which to create the poem
	 * @return poem (as described above)
	 */
	public String poem(String input) {
		String[] words = input.split("\\s+");
		int len = words.length;
		List<String> reList = new ArrayList<String>();
		for (int i = 0; i < len - 1; i++) {
			Set<String> vertexSet = graph.vertices();
			reList.add(words[i]);
			String str1 = words[i].toLowerCase();
			if(str1.contains("."))
				str1 = str1.substring(0,str1.length());
			String str2 = words[i + 1].toLowerCase();
			if(str2.contains("."))
				str2 = str2.substring(0,str2.length()-1);
			System.out.println("str1:"+str1);
			System.out.println("str2:"+str2);
			if (vertexSet.contains(str1) && vertexSet.contains(str2)) {
				System.out.println(44444);
				Map<String, Integer> targetMap = graph.targets(str1);
				Map<String, Integer> sourceMap = graph.sources(str2);
				for (String it : targetMap.keySet()) {
					System.out.println(it);
					if (sourceMap.containsKey(it)) {
						reList.add(it);
						break;
					}
				}
			}
		}
		reList.add(words[len - 1]);
		String relString = reList.get(0);
		for (int i = 1; i < reList.size(); i++)
			relString = relString.concat(" ").concat(reList.get(i));
		return relString;
	}

	// TODO toString()
}
