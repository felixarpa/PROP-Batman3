package domain.graph;

import util.ProjectConstants;

import java.util.LinkedList;

public class Author extends Node {

    /**
     * Crea un autor amb nom = name i id = id
     *
     * <p><b>Param:</b></p> <p><em>name</em>: Nom que se li donara al nou autor</p>
     * <p><b>Param:</b></p> <p><em>id</em>: Id que rebra el nou autor</p>
     * <p><b>Pre:</b></p> <p>name no es null i l'id no esta repetit</p>
     * <p><b>Post:</b></p> <p>Es crea un autor amb nom = name, rellevancia = 1, etiqueta = -1 i 0 adjacents</p>
     */
	public Author(String name, int id) {
        super(name);
        this.id = new Id(id, ProjectConstants.AUTHOR_TYPE);
	}

    /**
     * Crea un autor amb nom = name, id = id i etiqueta = label
     *
     * <p><b>Param:</b></p> <p><em>name</em>: Nom que se li donara al nou autor</p>
     * <p><b>Param:</b></p> <p><em>id</em>: Id que rebra el nou autor</p>
     * <p><b>Param:</b></p> <p><em>label</em>: Etiqueta que rebra el nou autor</p>
     * <p><b>Pre:</b></p> <p>name no es null i label entre 0 i 3</p>
     * <p><b>Post:</b></p> <p>Es crea un autor amb nom = name, rellevancia = 1, etiqueta = label i 0 adjacents</p>
     */
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

    /**
     * Retorna tots els Articles relacionats directament amb ell
     *
     * <p><b>Return:</b></p> <p>Articles adjacents</p>
     */
	public LinkedList<Paper> getPapers() {
        LinkedList<Paper> papers = new LinkedList<>();
        for (Node adjacentNode : adjacent.keySet()) {
            if (adjacentNode.asPaper() != null) papers.add(adjacentNode.asPaper());
        }
		return papers;
	}

    /**
     * Transforma la Id generica a Id d'autor
     *
     * <p><b>Return:</b></p> <p>Id id de tipus autor</p>
     */
    public static Id makeId(int id) {
        return new Id(id, ProjectConstants.AUTHOR_TYPE);
    }
}
