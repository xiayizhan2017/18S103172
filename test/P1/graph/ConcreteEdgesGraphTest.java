/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.Add()
    // Test the added vertex and edge lists separately
    @Test public void testAdd() {
    	Graph<String> myGraph = emptyInstance();
    	Set<String> myVerticeSet = new HashSet<>();
    	boolean flag = true; 
    	myGraph.add("a");
    	myGraph.add("b");
    	myGraph.add("c");
    	myGraph.add("d");
    	myVerticeSet.add("a");
    	myVerticeSet.add("b");
    	myVerticeSet.add("c");
    	myVerticeSet.add("d");
    	for(String it: myVerticeSet) {
    		if(!myGraph.vertices().contains(it)) {
    			flag = false;
    			break;
    		}
    	}
    	for(String it: myGraph.vertices()) {
    		if(!myVerticeSet.contains(it)) {
    			flag = false;
    			break;
    		}
    	}
    	assert flag;
    }
    
    // Testing strategy for ConcreteEdgesGraph.Set()
    // test when weight > 0 && weight == 0  
    @Test public void testSet() {
    	Graph<String> myGraph = emptyInstance();
    	List<Edge<String>> myEdgeList = new ArrayList<>();
    	Set<String> myVerticeSet = new HashSet<>(); 
    	
    	myGraph.set("a", "b", 1);
    	int num = myGraph.set("a", "b", 2);
    	myEdgeList.add(new Edge<String>("a","b",2));
    	myVerticeSet.add("a");
    	myVerticeSet.add("b");
    	assertEquals(1, num);
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	System.out.println(myGraph.toString());
    	assertEquals(myEdgeList.toString(), myGraph.toString());
    	
    	myGraph.set("a", "c", 2);
    	myEdgeList.add(new Edge<String>("a","c",2));
    	myVerticeSet.add("c");
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	System.out.println(myGraph.toString());
    	assertEquals(myEdgeList.toString(), myGraph.toString());

    	
    	myGraph.set("b", "c", 2);
    	myEdgeList.add(new Edge<String>("b","c",2));
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	System.out.println(myGraph.toString());
    	assertEquals(myEdgeList.toString(), myGraph.toString());

    	
    	myGraph.set("a", "c", 0);
    	for(int i = 0;i < myEdgeList.size();i++) {
    		if(myEdgeList.get(i).getsource() == "a" && myEdgeList.get(i).gettarget() == "c" && myEdgeList.get(i).getweight() == 2)
    			myEdgeList.remove(myEdgeList.get(i));
    	}
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	System.out.println(myGraph.toString());
    	System.out.println(myEdgeList);
    	assertEquals(myEdgeList.toString(), myGraph.toString());

    }

    // Testing strategy for ConcreteEdgesGraph.Remove()
    // remove a vertex that is in the graph and not in the graph
    @Test public void testRemove() {
    	Graph<String> myGraph = emptyInstance();
    	List<Edge<String>> myEdgeList = new ArrayList<Edge<String>>();
    	List<Edge<String>> myEdgeList1 = new ArrayList<Edge<String>>();
    	Set<String> myVerticeSet = new HashSet<String>();
    	
    	assertEquals(false,myGraph.remove("a"));
    	
    	myGraph.set("a", "b", 2);
    	myEdgeList.add(new Edge<String>("a","b",2));
    	myVerticeSet.add("a");
    	myVerticeSet.add("b");
    	myGraph.set("a", "c", 2);
    	myEdgeList.add(new Edge<String>("a","c",2));
    	myVerticeSet.add("c");
    	myGraph.remove("a");
    	System.out.println(myEdgeList1);
    	System.out.println(myGraph.toString());
    	assertEquals(myEdgeList1.toString(), myGraph.toString());
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    }
    
    // Testing strategy for ConcreteEdgesGraph.Vertices()
    // compare the number of vertex in the vertices with expected 
    @Test public void testVertices() {
    	Graph<String> myGraph = emptyInstance();
    	Set<String> myVerticeSet = new HashSet<String>();
    	
    	myGraph.add("a");
    	myVerticeSet.add("a");
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	
    	myGraph.add("b");
    	myVerticeSet.add("b");
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	
    	myGraph.add("c");
    	myVerticeSet.add("c");
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    	
    	myGraph.add("a");
    	myVerticeSet.add("a");
    	System.out.println(myGraph.vertices().size());
    	System.out.println(myVerticeSet.size());
    	assertEquals(myVerticeSet.size(), myGraph.vertices().size());
    }
    
    // Testing strategy for ConcreteEdgesGraph.Sources()
    // count the size of the Sources and compare with expected
    @Test public void testSource() {
    	Graph<String> myGraph = emptyInstance();
    	
    	myGraph.set("a", "b", 2);
    	assertEquals(0, myGraph.sources("a").size());
    	assertEquals(1, myGraph.sources("b").size());
    	
    	myGraph.set("a", "c", 2);
    	assertEquals(0, myGraph.sources("a").size());
    	assertEquals(1, myGraph.sources("b").size());
    	assertEquals(1, myGraph.sources("c").size());
    	
    	myGraph.set("b", "c", 2);
    	assertEquals(0, myGraph.sources("a").size());
    	assertEquals(1, myGraph.sources("b").size());
    	assertEquals(2, myGraph.sources("c").size());
    }

    // Testing strategy for ConcreteEdgesGraph.Targets()
    // count the size of the Targets and compare with expected
    @Test public void testTarget() {
    	Graph<String> myGraph = emptyInstance();
    	
    	myGraph.set("a", "b", 2);
    	assertEquals(1, myGraph.targets("a").size());
    	assertEquals(0, myGraph.targets("b").size());
    	
    	myGraph.set("a", "c", 2);
    	assertEquals(2, myGraph.targets("a").size());
    	assertEquals(0, myGraph.targets("b").size());
    	assertEquals(0, myGraph.targets("c").size());
    	
    	myGraph.set("b", "c", 2);
    	assertEquals(2, myGraph.targets("a").size());
    	assertEquals(1, myGraph.targets("b").size());
    	assertEquals(0, myGraph.targets("c").size());
    }
    
    // Testing strategy for ConcreteEdgesGraph.toString() 
    // compare the expected String with the toString
    @Test public void testToString() {
    	Graph<String> myGraph = emptyInstance();
    	List<Edge<String>> myEdgeList = new ArrayList<>();
    	myGraph.set("a", "b", 2);
    	myEdgeList.add(new Edge<String>("a", "b", 2));
    	myGraph.set("a", "c", 2);
    	myEdgeList.add(new Edge<String>("a", "c", 2));
    	myGraph.set("c", "b", 2);
    	myEdgeList.add(new Edge<String>("c", "b", 2));
    	assertEquals(myEdgeList.toString(), myGraph.toString());
    }
    
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // TODO tests for operations of Edge
    @Test public void testEdge() {
    	Edge<String> myEdge = new Edge<String>("a", "b", 2);
    	assertEquals("a", myEdge.getsource());
    	assertEquals("b", myEdge.gettarget());
    	assertEquals(2, myEdge.getweight());
    	assertEquals(true, myEdge.edgeEqual("a", "b", 2));
    	assertEquals(false, myEdge.edgeEqual("a", "b", 1));
    }
    
    
    
}
