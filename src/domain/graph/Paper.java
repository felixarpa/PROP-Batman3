package domain.graph;

import domain.PageRank;
import util.ProjectConstants;

import java.util.LinkedList;
import java.util.Set;

public class Paper extends Node {

	public Paper(String name, int id) {
		super(name);
        this.id = new Id(id, ProjectConstants.PAPER_TYPE);
	}
	public Paper(String name, int id, int label) {
		super(name, label);
        this.id = new Id(id, ProjectConstants.PAPER_TYPE);
	}

    @Override
	public void calculateRelevance() {
		double result = 0;
		for (Node node : adjacent.keySet()) {
			result += node.getRelevance() / node.getAdjacentPapers();
		}
		relevance = (1 - ProjectConstants.PAGERANK_VALUE) + ProjectConstants.PAGERANK_VALUE * result;
	}

    @Override
    public Paper asPaper() {
    	return this;
    }

	public LinkedList<Author> getAuthors() {
		Set<Node> adjacents = getAdjacentNode();
		LinkedList<Author> papers = new LinkedList<>();
		for (Node adjacent : adjacents) {
			if (adjacent.asAuthor() != null) papers.add(adjacent.asAuthor());
		}
		return papers;
	}

	public LinkedList<Conference> getConferences() {
		Set<Node> adjacents = getAdjacentNode();
		LinkedList<Conference> papers = new LinkedList<Conference>();
		for (Node adjacent : adjacents) {
			if (adjacent.asConference() != null) papers.add(adjacent.asConference());
		}
		return papers;
	}

	public LinkedList<Term> getTerms() {
		Set<Node> adjacents = getAdjacentNode();
		LinkedList<Term> papers = new LinkedList<Term>();
		for (Node adjacent : adjacents) {
			if (adjacent.asTerm() != null) papers.add(adjacent.asTerm());
		}
		return papers;
	}

	public static Id makeId(int id) {
		return new Id(id, ProjectConstants.PAPER_TYPE);
	}
}