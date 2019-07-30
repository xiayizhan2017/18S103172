package P2;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;


public class FriendshipGraph {
	Graph<Person> genericGraph = new ConcreteEdgesGraph<Person>();

	public void addVertex(Person addPerson) {
		genericGraph.add(addPerson);
	}

	public void addEdge(Person toName, Person inName) {
		genericGraph.set(toName, inName, 1);
	}

	public int getDistance(Person toName, Person inName) {
		if(toName.getName().equals(inName.getName()))
			return 0;
		if (!genericGraph.vertices().contains(toName) || !genericGraph.vertices().contains(inName))
			return -1;
		HashMap<Person, Integer> persons = new HashMap<Person, Integer>();
		LinkedBlockingQueue<Person> que = new LinkedBlockingQueue<Person>();
		que.add(toName);
		persons.put(toName, 1);
		while (!que.isEmpty()) {
			Person nowPerson = que.poll();
			int len = persons.get(nowPerson) + 1;
			for (Person it : genericGraph.targets(nowPerson).keySet()) {
				if (it.getName().equals(inName.getName())) 
					return persons.get(nowPerson);
				if (!persons.containsKey(it)) {
					que.add(it);
					persons.put(it, len);
				}
			}
		}
		return -1;
	}
	public static void main(String args[]) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));
	}

}

class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}