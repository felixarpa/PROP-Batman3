package domain.graph;

import domain.DomainController;
import domain.PageRank;
import jdk.nashorn.internal.runtime.GlobalConstants;
import util.ProjectConstants;

import java.util.LinkedList;

public class Term extends Node {

	public Term(String name, int id) {
		super(name);
        this.id = new Id(id, ProjectConstants.TERM_TYPE);
        if (DomainController.getCurrentUser().isFavorite(id)) {
        	relevance = Graph.getInstance().size();
        }
	}

    @Override
    public void calculateRelevance() {
    	double result = 0;
        for (Node node : adjacent.keySet()) {
            Paper paperAdjacent = node.asPaper();
            if (paperAdjacent != null) {
                result += paperAdjacent.getRelevance() /paperAdjacent.getAdjacentTerms();
            }
        }
        relevance = (1 - ProjectConstants.PAGERANK_VALUE) + ProjectConstants.PAGERANK_VALUE * result;
    }

	@Override
	public Term asTerm() {
		return this;
	}

	@Override
	void resetRelevance() {
		if (DomainController.getCurrentUser().isFavorite(id.getId())) {
			relevance = Graph.getInstance().size();
		}
		else relevance = 1;
	}
	
    public LinkedList<Paper> getPapers() {
        LinkedList<Paper> papers = new LinkedList<>();
        for (Node adjacentNode : adjacent.keySet()) {
            if (adjacentNode.asPaper() != null) papers.add(adjacentNode.asPaper());
        }
        return papers;
    }

    public static Id makeId(int id) {
        return new Id(id, ProjectConstants.TERM_TYPE);
    }

}