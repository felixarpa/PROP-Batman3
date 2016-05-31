package domain.graph;

import util.ProjectConstants;

import java.util.LinkedList;
import java.util.Set;

public class Paper extends Node {

	/**
	 * Crea un article amb nom = name i id = id
	 *
	 * <p><b>Param:</b></p> <p>name Nom que se li donara al nou article</p>
	 * <p><b>Param:</b></p> <p>id Id que rebra el nou article</p>
	 * <p><b>Pre:</b></p> <p>name no es null i l'id no esta repetit</p>
	 * <p><b>Post:</b></p> <p>Es crea un article amb nom = name, rellevancia = 1, etiqueta = -1 i 0 adjacents</p>
	 */
	public Paper(String name, int id) {
		super(name);
        this.id = new Id(id, ProjectConstants.PAPER_TYPE);
	}

	/**
	 * Crea un article amb nom = name, id = id i etiqueta = label
	 *
	 * <p><b>Param:</b></p> <p>name Nom que se li donara al nou artile</p>
	 * <p><b>Param:</b></p> <p>id Id que rebra el nou article</p>
	 * <p><b>Param:</b></p> <p>label Etiqueta que rebra el nou article</p>
	 * <p><b>Pre:</b></p> <p>name no es null i label entre 0 i 3</p>
	 * <p><b>Post:</b></p> <p>Es crea un article amb nom = name, rellevancia = 1, etiqueta = label i 0 adjacents</p>
	 */
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

	/**
	 * Retorna tots els autors relacionats directament amb ell
	 *
	 * <p><b>Return:</b></p> Autors adjacents
	 */
	public LinkedList<Author> getAuthors() {
		Set<Node> adjacents = getAdjacentNode();
		LinkedList<Author> papers = new LinkedList<>();
		for (Node adjacent : adjacents) {
			if (adjacent.asAuthor() != null) papers.add(adjacent.asAuthor());
		}
		return papers;
	}

	/**
	 * Retorna tots les conferencies relacionades directament amb ell
	 *
	 * <p><b>Return:</b></p> Conferencies adjacents
	 */
	public LinkedList<Conference> getConferences() {
		Set<Node> adjacents = getAdjacentNode();
		LinkedList<Conference> papers = new LinkedList<Conference>();
		for (Node adjacent : adjacents) {
			if (adjacent.asConference() != null) papers.add(adjacent.asConference());
		}
		return papers;
	}

	/**
	 * Retorna tots els tmes relacionats directament amb ell
	 *
	 * <p><b>Return:</b></p> Temes adjacents
	 */
	public LinkedList<Term> getTerms() {
		Set<Node> adjacents = getAdjacentNode();
		LinkedList<Term> papers = new LinkedList<Term>();
		for (Node adjacent : adjacents) {
			if (adjacent.asTerm() != null) papers.add(adjacent.asTerm());
		}
		return papers;
	}

	/**
	 * Transforma la Id generica a Id d'article
	 *
	 * <p><b>Return:</b></p> Id id de tipus article
	 */
	public static Id makeId(int id) {
		return new Id(id, ProjectConstants.PAPER_TYPE);
	}
}