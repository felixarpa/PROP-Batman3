package domain.graph;

import domain.DomainController;
import util.ProjectConstants;

import java.util.LinkedList;

public class Term extends Node {

    /**
     * Crea un tema amb nom = name i id = id
     *
     * <p><b>Param:</b></p> <p>name Nom que se li donara al nou autor</p>
     * <p><b>Param:</b></p> <p>id Id que rebra el nou autor</p>
     * <p><b>Pre:</b></p> <p>name no es null i l'id no esta repetit</p>
     * <p><b>Post:</b></p> <p>Es crea un tema amb nom = name, rellevancia = 1, etiqueta = -1 i 0 adjacents</p>
     */
	public Term(String name, int id) {
		super(name);
        this.id = new Id(id, ProjectConstants.TERM_TYPE);
        if (DomainController.getCurrentUser().isFavorite(this)) {
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
		if (DomainController.getCurrentUser().isFavorite(this)) {
			relevance = Graph.getInstance().size();
		}
		else relevance = 1;
	}

    /**
     * Retorna tots els Articles relacionats directament amb ell
     *
     * <p><b>Return:</b></p> Articles adjacents
     */
    public LinkedList<Paper> getPapers() {
        LinkedList<Paper> papers = new LinkedList<>();
        for (Node adjacentNode : adjacent.keySet()) {
            if (adjacentNode.asPaper() != null) papers.add(adjacentNode.asPaper());
        }
        return papers;
    }

    /**
     * Transforma la Id generica a Id de tema
     *
     * <p><b>Return:</b></p> Id id de tipus tema
     */
    public static Id makeId(int id) {
        return new Id(id, ProjectConstants.TERM_TYPE);
    }

}