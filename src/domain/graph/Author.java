package domain.graph;

import util.ProjectConstants;

import java.util.LinkedList;

public class Author extends Node {

	public Author(String name, int id) {
        super(name);
        this.id = new Id(id, ProjectConstants.AUTHOR_TYPE);
	}
    public Author(String name, int id, int label) {
        super(name,label);
        this.id = new Id(id, ProjectConstants.AUTHOR_TYPE);
    }

	@Override
	public void calculateRelevance() {
        double result = 0;
        for (Node node : adjacent.keySet()) {
            Paper paperAdjacent = node.asPaper();
            if (paperAdjacent != null) {
                result += paperAdjacent.getRelevance() / paperAdjacent.getAdjacentAuthors();
            }
        }
        relevance = (1 - ProjectConstants.PAGERANK_VALUE) + ProjectConstants.PAGERANK_VALUE * result;
	}

	@Override
	public Author asAuthor() {
		return this;
	}

	public LinkedList<Paper> getPapers() {
        LinkedList<Paper> papers = new LinkedList<>();
        for (Node adjacentNode : adjacent.keySet()) {
            if (adjacentNode.asPaper() != null) papers.add(adjacentNode.asPaper());
        }
		return papers;
	}

    public static Id makeId(int id) {
        return new Id(id, ProjectConstants.AUTHOR_TYPE);
    }
}
