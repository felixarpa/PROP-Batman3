package domain.graph;

import domain.PageRank;
import util.ProjectConstants;

import java.util.LinkedList;

public class Conference extends Node {

	public Conference(String name, int id) {
		super(name);
        this.id = new Id(id, ProjectConstants.CONFERENCE_TYPE);
	}
	public Conference(String name, int id, int label) {
		super(name, label);
        this.id = new Id(id, ProjectConstants.CONFERENCE_TYPE);
	}

	@Override
	public void calculateRelevance() {
		double result = 0;
		for (Node node : adjacent.keySet()) {
			Paper paperAdjacent = node.asPaper();
			if (paperAdjacent != null) {
				result += paperAdjacent.getRelevance()/paperAdjacent.getAdjacentConferences();
			}
		}
		relevance = (1 - ProjectConstants.PAGERANK_VALUE) + ProjectConstants.PAGERANK_VALUE * result;
	}

	@Override
	public Conference asConference() {
		return this;
	}

	public LinkedList<Paper> getPapers() {
		LinkedList<Paper> papers = new LinkedList<>();
		for (Node adjacentNode : getAdjacentNode()) {
			if (adjacentNode.asPaper() != null) papers.add(adjacentNode.asPaper());
		}
		return papers;
	}

	public static Id makeId(int id) {
		return new Id(id, ProjectConstants.CONFERENCE_TYPE);
	}
}
