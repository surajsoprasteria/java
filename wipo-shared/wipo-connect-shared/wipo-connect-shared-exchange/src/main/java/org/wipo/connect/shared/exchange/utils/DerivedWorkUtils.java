/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.exchange.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.wipo.connect.common.vo.PairVO;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;

public class DerivedWorkUtils {

	private DerivedWorkUtils(){
		super();
	}

	public static Set<PairVO<Long, String>> detectCycle(List<DerivedWork> derivedWorkList) {
		DirectedGraph<Long, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
		Map<Long,String> idMap = new HashMap<>();
		Set<PairVO<Long, String>> cyclePairOut = new HashSet<>();

		for(DerivedWork dw : derivedWorkList){
			if(dw.getFkParentWork() != null && !graph.containsVertex(dw.getFkParentWork())){
				graph.addVertex(dw.getFkParentWork());
			}

			if(dw.getFkWork() != null && !graph.containsVertex(dw.getFkWork())){
				graph.addVertex(dw.getFkWork());
			}

			if(dw.getFkParentWork() != null && dw.getFkWork() != null && !graph.containsEdge(dw.getFkParentWork(), dw.getFkWork())){
				graph.addEdge(dw.getFkParentWork(), dw.getFkWork());
			}

			if(dw.getWork() != null){
				idMap.put(dw.getFkWork(), dw.getWork().getMainId());
			}
		}

		CycleDetector<Long, DefaultEdge> detector = new CycleDetector<>(graph);
        Set<Long> cycleIdSet = detector.findCycles();

        cycleIdSet.forEach(id -> {
        	cyclePairOut.add(new PairVO<>(id, idMap.get(id)));
        });

        return cyclePairOut;
	}
}
