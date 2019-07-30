/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;




/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// TODO
	// Representation invariant:
	// TODO
	// Safety from rep exposure:
	// TODO

	// TODO constructor
	// TODO checkRep

	private void checkRep() {
		int sizeVertices = vertices.size();
		int sizeEdges = edges.size();
		boolean flag = true;
		if (sizeEdges > 0 && sizeVertices == 0)
			flag = false;
		for(int i = 0;i < edges.size();i++) {
			L sourcenL = edges.get(i).getsource();
			L targetL = edges.get(i).gettarget();
			if(!vertices.contains(sourcenL) || !vertices.contains(targetL)) {
				flag = false;
				break;
			}
		}
		assert flag;
	}

	@Override
	public boolean add(L vertex) {
		return vertices.add(vertex);
	}

	@Override
	public int set(L source, L target, int weight) {
		checkRep();
		if (weight == 0) {
			for (Edge<L> it : edges) {
				if (it.getsource().equals(source) && it.gettarget().equals(target)) {
					int rec = it.getweight();
					edges.remove(it);
					checkRep();
					return rec;
				}
			}
			checkRep();
			return 0;
		}
		if (weight > 0) {
			for (Edge<L> it : edges) {
				if (it.getsource().equals(source) && it.gettarget().equals(target)) {
					int rec = it.getweight();
					edges.set(edges.indexOf(it), new Edge<L>(it.getsource(), it.gettarget(), weight));
					checkRep();
					return rec;
				}
			}
			edges.add(new Edge<L>(source, target, weight));
			if(!vertices.contains(source))
				vertices.add(source);
			if(!vertices.contains(target))
				vertices.add(target);
		}
		checkRep();
		return 0;
	}

	@Override
	public boolean remove(L vertex) {
		checkRep();
		List<Edge<L>> listEdges = new ArrayList<>();
		if (!vertices.contains(vertex))
			return false;
		for (Edge<L> it : edges) {
			if (it.getsource().equals(vertex) || it.gettarget().equals(vertex))
				listEdges.add(it);
		}
		checkRep();
		vertices.remove(vertex);
		return edges.removeAll(listEdges);
	}

	@Override
	public Set<L> vertices() {
		return vertices;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> relMap = new HashMap<>();
		checkRep();
		for (Edge<L> it : edges) {
			if (it.gettarget().equals(target))
				relMap.put(it.getsource(), (Integer) it.getweight());
		}
		checkRep();
		return relMap;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> relMap = new HashMap<>();
		checkRep();
		for (Edge<L> it : edges) {
			if (it.getsource().equals(source))
				relMap.put(it.gettarget(), (Integer) it.getweight());
		}
		checkRep();
		return relMap;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		checkRep();
		return edges.toString();
	}
}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {
	private final L source;
	private final L target;
	private final int weight;
	// TODO fields

	// Abstraction function:
	// TODO
	// Representation invariant:
	// TODO
	// Safety from rep exposure:
	// TODO

	// TODO constructor

	Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	L getsource() {
		checkRep();
		return this.source;
	}

	L gettarget() {
		checkRep();
		return this.target;
	}

	int getweight() {
		checkRep();
		return this.weight;
	}

	// TODO checkRep
	public void checkRep() {
		assert source != null;
		assert target != null;
		assert weight > 0;
	}

	public boolean edgeEqual(L source,L target,int weight){
		checkRep();
		if(this.source == source && this.target == target && this.weight == weight)
			return true;
		return false;
	}
	
	
	
	// TODO methods

	// TODO toString()
	@Override
	public String toString() {
		checkRep();
		return source + "," + target + "," + weight;
	}
}
