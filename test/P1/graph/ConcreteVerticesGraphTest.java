/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;




/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		return new ConcreteVerticesGraph<String>();
	}

	/*
	 * Testing ConcreteVerticesGraph...
	 */
	// Testing strategy for ConcreteVerticesGraph.Add()
	// Add a vertex that is already in the graph and not in
	@Test
	public void testAdd() {
		Graph<String> myGraph = emptyInstance();
		assertEquals(true, myGraph.add("a"));
		assertEquals(false, myGraph.add("a"));
	}

	// Testing strategy for ConcreteVerticesGraph.Set()
	// Input different types of examples,to test if we can get the right @param
	@Test
	public void testSet() {
		Graph<String> myGraph = emptyInstance();

		int num = myGraph.set("a", "b", 1);
		assertEquals(0, num);

		num = myGraph.set("a", "b", 2);
		assertEquals(1, num);

		num = myGraph.set("a", "c", 0);
		assertEquals(0, num);

		num = myGraph.set("b", "d", 3);
		assertEquals(0, num);

		num = myGraph.set("b", "d", -1);
		assertEquals(0, num);

		num = myGraph.set("b", "d", 0);
		assertEquals(3, num);

	}

	// Testing strategy for ConcreteVerticesGraph.Remove()
	// remove one is legal and another is illegal
	@Test
	public void testRemove() {
		Graph<String> myGraph = emptyInstance();
		myGraph.set("a", "b", 2);
		assertEquals(true, myGraph.remove("a"));
		assertEquals(false, myGraph.remove("a"));
	}

	// Testing strategy for ConcreteVerticesGraph.vertices()
	// Input some vertices to see if the vertices in the graph is in the set
	@Test
	public void testVertices() {
		Graph<String> myGraph = emptyInstance();
		myGraph.set("a", "b", 2);
		myGraph.set("c", "a", 1);
		assertEquals(true, myGraph.vertices().contains("a"));
		assertEquals(true, myGraph.vertices().contains("b"));
		assertEquals(true, myGraph.vertices().contains("c"));
		assertEquals(false, myGraph.vertices().contains("d"));
	}

	// Testing strategy for ConcreteVerticesGraph.Sources()
	// Test different vertices about their sources
	@Test
	public void testSources() {
		Graph<String> myGraph = emptyInstance();
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		Map<String, Integer> temp = new HashMap<String, Integer>();
		myGraph.set("a", "b", 2);
		myMap.put("a", 2);
		assertEquals(myMap, myGraph.sources("b"));
		assertEquals(temp, myGraph.sources("a"));

		myGraph.set("a", "c", 2);
		assertEquals(myMap, myGraph.sources("b"));
		assertEquals(myMap, myGraph.sources("c"));
		assertEquals(temp, myGraph.sources("a"));

	}

	// Testing strategy for ConcreteVerticesGraph.Targets()
	// Test different vertices about their targets
	@Test
	public void testTargets() {
		Graph<String> myGraph = emptyInstance();
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		Map<String, Integer> temp = new HashMap<String, Integer>();
		myGraph.set("a", "b", 2);
		myMap.put("b", 2);
		assertEquals(myMap, myGraph.targets("a"));
		assertEquals(temp, myGraph.targets("b"));

		myGraph.set("c", "b", 2);
		assertEquals(myMap, myGraph.targets("c"));
		assertEquals(myMap, myGraph.targets("a"));
		assertEquals(temp, myGraph.targets("b"));

	}

	// Testing strategy for ConcreteVerticesGraph.toString()
	// Test different edges
	@Test
	public void testToString() {
		Graph<String> myGraph = emptyInstance();
		List<Vertex<String>> myVerticesList = new ArrayList<Vertex<String>>();

		myGraph.set("a", "b", 1);
		myVerticesList.add(new Vertex<String>("a"));
		myVerticesList.get(0).putTarget("b", 1);
		myVerticesList.add(new Vertex<String>("b"));
		myVerticesList.get(1).putSource("a", 1);
		assertEquals(myVerticesList.toString(), myGraph.toString());

		myGraph.set("c", "a", 2);
		myVerticesList.get(0).putSource("c", 2);
		myVerticesList.add(new Vertex<String>("c"));
		myVerticesList.get(2).putTarget("a", 2);
		assertEquals(myVerticesList.toString(), myGraph.toString());

		myGraph.set("d", "c", 3);
		myVerticesList.add(new Vertex<String>("d"));
		myVerticesList.get(3).putTarget("c", 3);
		myVerticesList.get(2).putSource("d", 3);
		assertEquals(myVerticesList.toString(), myGraph.toString());

	}

	/*
	 * Testing Vertex...
	 */

	// Testing strategy for Vertex
	@Test
	public void testVertex() {
		Vertex<String> myVertex = new Vertex<String>("a");
		Map<String,Integer> myMap = new HashMap<>();
		Map<String,Integer> myMap1 = new HashMap<>();
		
		// TODO tests for operations of Vertex

		// Tests getName()
		assertEquals("a", myVertex.getName());

		// Test getSource()
		assertEquals(myMap, myVertex.getSource());

		// Test getTarget()
		assertEquals(myMap, myVertex.getTarget());
		
		// Test removeTarget()
		assertEquals(0, (int)myVertex.removeSource("a"));
		
		// Test removeSource()
		assertEquals(0, (int)myVertex.removeTarget("a"));
		
		// Test putSource()
		myVertex.putSource("b", 2);
		myMap.put("b", 2);
		assertEquals(myMap, myVertex.getSource());
		
		// Test putTarget()
		myVertex.putTarget("c", 3);
		myMap1.put("c", 3);
		assertEquals(myMap1, myVertex.getTarget());
		
		//Test toString()
		
		assertEquals("a"+myMap+myMap1, myVertex.toString());
		
	}

}
