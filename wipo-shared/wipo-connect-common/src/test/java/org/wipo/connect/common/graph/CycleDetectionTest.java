package org.wipo.connect.common.graph;

import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class CycleDetectionTest {
	private static final Logger LOGGER = WipoLoggerFactory.getLogger(CycleDetectionTest.class);

	@BeforeClass
	public static void init() {
	}

	@Test
	public void noCycleTest() {

		DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		String v7 = "v7";


		g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);

        g.addEdge(v1, v2);
        g.addEdge(v1, v3);

        g.addEdge(v2, v4);
        g.addEdge(v2, v5);

        g.addEdge(v3, v5);
        g.addEdge(v3, v6);

        g.addEdge(v5, v4);

        g.addEdge(v6, v7);


        LOGGER.debug(g.toString());

        CycleDetector<String, DefaultEdge> detector = new CycleDetector<>(g);
        Set<String> cycleSet = detector.findCycles();

        for(String c : cycleSet){
        	 LOGGER.debug(c);
        }

        Assert.assertTrue("no cycle", cycleSet.isEmpty());

	}

	@Test
	public void cycleTest() {

		DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		String v7 = "v7";


		g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);

        g.addEdge(v1, v2);
        g.addEdge(v1, v3);

        g.addEdge(v2, v4);
        g.addEdge(v2, v5);

        g.addEdge(v3, v5);
        g.addEdge(v3, v6);

        g.addEdge(v5, v4);

        g.addEdge(v6, v7);

        g.addEdge(v7, v1);


        LOGGER.debug(g.toString());


        CycleDetector<String, DefaultEdge> detector = new CycleDetector<>(g);
        Set<String> cycleSet = detector.findCycles();

        for(String c : cycleSet){
        	 LOGGER.debug(c);
        }

        Assert.assertEquals("cycle size", 4, cycleSet.size());
        Assert.assertTrue("cycle contains v1", cycleSet.contains(v1));
        Assert.assertTrue("cycle contains v3", cycleSet.contains(v3));
        Assert.assertTrue("cycle contains v6", cycleSet.contains(v6));
        Assert.assertTrue("cycle contains v7", cycleSet.contains(v7));

	}

	@Test
	public void cycle3Test() {

		DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		String v7 = "v7";
		String v8 = "v8";
		String v9 = "v9";
		String v10 = "v10";
		String v11 = "v11";


		g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);
        g.addVertex(v11);

        g.addEdge(v1, v2);
        g.addEdge(v1, v3);
        g.addEdge(v1, v4);

        g.addEdge(v2, v9);
        g.addEdge(v2, v10);

        g.addEdge(v3, v10);
        g.addEdge(v3, v11);

        g.addEdge(v4, v5);
        g.addEdge(v4, v6);

        g.addEdge(v5, v7);
        g.addEdge(v5, v8);

        g.addEdge(v6, v8);

        g.addEdge(v8, v4);
        g.addEdge(v10, v2);

        LOGGER.debug(g.toString());


        CycleDetector<String, DefaultEdge> detector = new CycleDetector<>(g);
        Set<String> cycleSet = detector.findCycles();

        for(String c : cycleSet){
        	 LOGGER.debug(c);
        }

        Assert.assertEquals("cycle size", 6, cycleSet.size());
        Assert.assertTrue("cycle contains v2", cycleSet.contains(v2));
        Assert.assertTrue("cycle contains v4", cycleSet.contains(v4));
        Assert.assertTrue("cycle contains v5", cycleSet.contains(v5));
        Assert.assertTrue("cycle contains v6", cycleSet.contains(v6));
        Assert.assertTrue("cycle contains v8", cycleSet.contains(v8));
        Assert.assertTrue("cycle contains v10", cycleSet.contains(v10));

	}
}
