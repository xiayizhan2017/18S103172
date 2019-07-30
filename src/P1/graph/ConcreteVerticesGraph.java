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
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	// Represents a graph, stored by vertex

	// Representation invariant:
	// vertices contains no repeated Vertex<L>;
	// every Vertex<L> in List is legal

	// Safety from rep exposure:
	// all fields are private
	// vertices is a final list,so it is immutable

	// TODO checkRep
	private void checkRep() {
		boolean flag = true;
		List<L> temp = new ArrayList<>();
		for (Vertex<L> it : vertices) {
			temp.add(it.getName()); 
		}
		for(Vertex<L> it : vertices) {
			Map<L, Integer> sourceL = it.getSource();
			Map<L, Integer> targetL = it.getTarget();
			for(L at:sourceL.keySet() ) {
				if(!temp.contains(at)) {
					flag = false;
					break;
				}
			}
			for(L at:targetL.keySet() ) {
				if(!temp.contains(at)) {
					flag = false;
					break;
				}
			}
		}
		assert flag;
	}

	@Override
	public boolean add(L vertex) {
		for (Vertex<L> it : vertices) {
			if (it.getName().equals(vertex))
				return false;
		}
		return vertices.add(new Vertex<L>(vertex));
	}

	@Override
	public int set(L source, L target, int weight) {
		checkRep();
		int numSourse = -1, numTarget = -1;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getName().equals(source))
				numSourse = i;
			if (vertices.get(i).getName().equals(target))
				numTarget = i;
		}
		if (weight == 0) {
			if (numSourse > -1 && numTarget > -1) {
				int rel = vertices.get(numSourse).removeTarget(target);
				vertices.get(numTarget).removeSource(source);
				checkRep();
				return rel;
			}
			checkRep();
			return 0;
		}
		if (weight > 0) {
			int relSource = 0, relTarget = 0;
			if (numSourse > -1)
				relSource = vertices.get(numSourse).putTarget(target, weight);
			if (numSourse < 0) {
				vertices.add(new Vertex<L>(source));
				vertices.get(vertices.size() - 1).putTarget(target, weight);
			}
			if (numTarget > -1)
				relTarget = vertices.get(numTarget).putSource(source, weight);
			if (numTarget < 0) {
				vertices.add(new Vertex<L>(target));
				relTarget = vertices.get(vertices.size() - 1).putSource(source, weight);
			}
			checkRep();
			return relSource & relTarget;
		}

		return 0;
	}

	@Override
	public boolean remove(L vertex) {
		checkRep();
		int num = -1;
		for (int i = 0; i < vertices.size(); i++)
			if (vertices.get(i).getName().equals(vertex))
				num = i;
		if (num < 0)
			return false;
		Map<L, Integer> targetMap = vertices.get(num).getTarget();
		Map<L, Integer> sourceMap = vertices.get(num).getSource();
		for (L it : targetMap.keySet()) {
			int n = -1;
			for (int i = 0; i < vertices.size(); i++)
				if (vertices.get(i).getName().equals(it)) {
					n = i;
					break;
				}
			vertices.get(n).removeSource(vertex);
		}
		for (L it : sourceMap.keySet()) {
			int n = -1;
			for (int i = 0; i < vertices.size(); i++)
				if (vertices.get(i).getName().equals(it)) {
					n = i;
					break;
				}
			vertices.get(n).removeTarget(vertex);
		}
		vertices.remove(vertices.get(num));
		checkRep();
		return true;
	}

	@Override
	public Set<L> vertices() {
		Set<L> relSet = new HashSet<>();
		for (Vertex<L> it : vertices)
			relSet.add(it.getName());
		checkRep();
		return relSet;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		checkRep();
		int num = -1;
		for (int i = 0; i < vertices.size(); i++)
			if (vertices.get(i).getName().equals(target))
				num = i;
		if (num == -1)
			return null;
		checkRep();
		return vertices.get(num).getSource();
	}

	@Override
	public Map<L, Integer> targets(L source) {
		checkRep();
		int num = -1;
		for (int i = 0; i < vertices.size(); i++)
			if (vertices.get(i).getName().equals(source))
				num = i;
		if (num == -1)
			return null;
		checkRep();
		return vertices.get(num).getTarget();
	}

	// TODO toString()

	@Override
	public String toString() {
		checkRep();
		return vertices.toString();
	}
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	private L name;
	private Map<L, Integer> sources;
	private Map<L, Integer> targets;

	// TODO fields

	// Abstraction function:
	// TODO
	// Representation invariant:
	// TODO
	// Safety from rep exposure:
	// TODO

	// TODO constructor

	Vertex(L vertex) {
		this.name = vertex;
		this.sources = new HashMap<>();
		this.targets = new HashMap<>();
	}

	// TODO checkRep
	private void checkRep() {
		boolean flag1 = true, flag2 = true;
		for (Integer it : sources.values()) {
			if ((int) it <= 0) {
				flag1 = false;
				break;
			}
		}
		for (Integer it : sources.values()) {
			if ((int) it <= 0) {
				flag2 = false;
				break;
			}
		}
		assert flag1&flag2;
	}

	// TODO methods

	public L getName() {
		return name;
	}

	public Map<L, Integer> getSource() {
		return sources;
	}

	public Map<L, Integer> getTarget() {
		return targets;
	}

	public Integer removeSource(L source) {
		checkRep();
		Integer rel = sources.remove(source);
		if (rel == null)
			return 0;
		checkRep();
		return rel;
	}

	public Integer removeTarget(L target) {
		checkRep();
		Integer rel = targets.remove(target);
		if (rel == null)
			return 0;
		checkRep();
		return rel;
	}

	public Integer putSource(L source, Integer weight) {
		Integer rel;
		checkRep();
		rel = sources.put(source, weight);
		if (rel == null)
			return 0;
		checkRep();
		return rel;
		
	}

	public Integer putTarget(L target, Integer weight) {
		Integer rel;
		checkRep();
		rel = targets.put(target, weight);
		if (rel == null)
			return 0;
		checkRep();
		return rel;
	}

	// TODO toString()
	@Override
	public String toString() {
		checkRep();
		return name.toString() + sources.toString() + targets.toString();
	}
	
}
