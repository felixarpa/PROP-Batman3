package domain.graph;

import util.ProjectConstants;

import java.util.LinkedList;

public class Conference extends Node {

	/**
	 * Crea un autor amb nom = name i id = id
	 *
	 * <p><b>Param:</b></p> <p>name Nom que se li donara al nova conferencia</p>
	 * <p><b>Param:</b></p> <p>id Id que rebra el nova conferencia</p>
	 * <p><b>Pre:</b></p> <p>name no es null i l'id no esta repetit</p>
	 * <p><b>Post:</b></p> <p>Es crea un autor amb nom = name, rellevancia = 1, etiqueta = -1 i 0 adjacents</p>
	 */
	public Conference(String name, int id) {
		super(name);
        this.id = new Id(id, ProjectConstants.CONFERENCE_TYPE);
	}

	/**
	 * Crea una conferencia amb nom = name, id = id i etiqueta = label
	 *
	 * <p><b>Param:</b></p> <p>name Nom que se li donara al nova conferencia</p>
	 * <p><b>Param:</b></p> <p>id Id que rebra el nova conferencia</p>
	 * <p><b>Param:</b></p> <p>label Etiqueta que rebra el nova conferencia</p>
	 * <p><b>Pre:</b></p> <p>name no es null i label entre 0 i 3</p>
	 * <p><b>Post:</b></p> <p>Es crea una conferencia amb nom = name, rellevancia = 1, etiqueta = label i 0 adjacents</p>
	 */
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

	/**
	 * Retorna tots els Articles relacionats directament amb ell
	 *
	 * <p><b>Return:</b></p> Articles adjacents
	 */
	public LinkedList<Paper> getPapers() {
		LinkedList<Paper> papers = new LinkedList<>();
		for (Node adjacentNode : getAdjacentNode()) {
			if (adjacentNode.asPaper() != null) papers.add(adjacentNode.asPaper());
		}
		return papers;
	}

	/**
	 * Transforma la Id generica a Id de conferencia
	 *
	 * <p><b>Return:</b></p> Id id de tipus conferencia
	 */
	public static Id makeId(int id) {
		return new Id(id, ProjectConstants.CONFERENCE_TYPE);
	}
}
