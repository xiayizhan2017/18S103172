package P2;

import static org.junit.Assert.*;

import org.junit.*;


public class FriendshipGraphTest {
	FriendshipGraph graph = new FriendshipGraph();
	Person xiaomi = new Person("xiaomi");
	Person leijun = new Person("leijun");
	Person linbin = new Person("linbin");
	Person luweibin = new Person("luweibin");
	Person zmi = new Person("zmi");
	Person redmi = new Person("redmi");
	Person xiaoai = new Person("xiaoai");

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}

	@Test
	public void addVertex() {
		graph.addVertex(xiaomi);
		graph.addVertex(leijun);
		graph.addVertex(linbin);
		graph.addVertex(luweibin);
		graph.addVertex(zmi);
		graph.addVertex(redmi);
		graph.addVertex(xiaoai);
	}

	@Test
	public void addEdge() {
		addVertex();
		graph.addEdge(xiaoai, xiaomi);
		graph.addEdge(xiaoai, redmi);
		graph.addEdge(xiaoai, leijun);
		graph.addEdge(xiaomi, zmi);
		graph.addEdge(leijun, xiaomi);
		graph.addEdge(leijun, linbin);
		graph.addEdge(leijun, zmi);
		graph.addEdge(leijun, luweibin);
	}

	@Test(expected = NullPointerException.class)
	public void getDistance() {
		addEdge();
		assertEquals( 1, graph.getDistance(xiaomi, zmi));
		System.out.println(graph.getDistance(xiaomi, zmi));
		assertEquals("expected equals", 2, graph.getDistance(xiaoai, luweibin));
		assertEquals("expected equals", -1, graph.getDistance(redmi, luweibin));
		assertEquals("expected equals", 1, graph.getDistance(leijun, zmi));
		assertEquals("expected equals", 2, graph.getDistance(xiaoai, linbin));
		assertEquals("expected equals", 0, graph.getDistance(leijun, leijun));
	}
}
