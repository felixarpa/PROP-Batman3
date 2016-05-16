package domain.graph;

import comparators.IdComparator;
import exceptions.ExistingEdge;
import exceptions.NonExistentEdge;
import exceptions.ProjectError;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class Node implements Comparable<Node> {



	protected  String name;
	protected  Id id;
	protected double relevance;
	private int label;

	private int adjacentAuthors;
	private int adjacentConferences;
	private int adjacentPapers;
	private int adjacentTerms;

	// Felix's optimization (he does Mugendo)
	TreeMap<Node, Double> adjacent;




	//////// CRETOR ////////

	/**
	 * Crea un node amb nom = name
	 *
	 * @param name Nom que se li donara al nou node
	 * @pre name no es null
	 * @post Es crea un Node amb nom = name, rellevancia = 1, etiqueta = -1 i 0 adjacents
	 * @return No
	 */
	public Node(String name) {
		this.name = name;
		label = -1;
		relevance = 1;
		adjacentAuthors = 0;
		adjacentConferences = 0;
		adjacentPapers = 0;
		adjacentTerms = 0;
		adjacent = new TreeMap<>(new IdComparator());
	}

	/**
	 * Crea un node amb nom = name i etiqueta = label
	 *
	 * @param name Nom que se li donara al nou node
	 * @param label Etiqueta que rebra el nou node
	 * @pre name no es null i 0 <= label <= 3
	 * @post Es crea un node amb nom = name, rellevancia = 1, etiqueta = label i 0 adjacents
	 * @return No
	 */
	public Node(String name, int label) {
		this.name = name;
		setLabel(label);
		relevance = 1;
		adjacentAuthors = 0;
		adjacentConferences = 0;
		adjacentPapers = 0;
		adjacentTerms = 0;
		adjacent = new TreeMap<>(new IdComparator());
	}




	//////// GETTERS ////////

	/**
	 * Retorna el nom del Node instanciat
	 *
	 * @pre No
	 * @post No
	 * @return Nom del node
	 */
	public String getName() {
		return name;
	}


	/**
	 * Retorna la id del Node instanciat
	 *
	 * @pre No
	 * @post No
	 * @return Id del node
	 */
	public Id getId() {
		return id;
	}


	/**
	 * Retorna la rellevancia del Node instanciat
	 *
	 * @pre No
	 * @post No
	 * @return Rellevancia del Node instancat
	 */
	public double getRelevance() {
		return relevance;
	}

	/**
	 * Retorna l'etiquta del Node instanciat
	 *
	 * @pre No
	 * @post No
	 * @return Etiqueta del Node instanciat
	 */
	public int getLabel() {
		return label;
	}


	// Authors
	/**
	 * Retorna el numero d'autors adjacents que te el node instanciats
	 *
	 * @pre No
	 * @post No
	 * @return Numero d'autors adjacents
	 */
	int getAdjacentAuthors() {
		return adjacentAuthors;
	}

	// Conferences
	/**
	 * Retorna el numero de conferencies adjacents que te el node instanciats
	 *
	 * @pre No
	 * @post No
	 * @return Numero de conferencies adjacents
	 */
	int getAdjacentConferences() {
		return adjacentConferences;
	}

	// Papers
	/**
	 * Retorna el numero de papers adjacents que te el node instanciats
	 *
	 * @pre No
	 * @post No
	 * @return Numero de papers adjacents
	 */
	int getAdjacentPapers() {
		return adjacentPapers;
	}
	
	// Terms
		/**
		 * Retorna el numero de temes adjacents que te el node instanciats
		 *
		 * @pre No
		 * @post No
		 * @return Numero de temes adjacents
		 */
	int getAdjacentTerms() {
			return adjacentTerms;
		}

	/**
	 * Retorna la rellevancia de la relacio entre el node instanciat i dst
	 *
	 * @param dst Node desti de l'aresta que es vol saber la rellevancia
	 * @pre Dst es adjacent al node instanciat
	 * @post No
	 * @return Si no s'ha fet cas al PRE i el node no existeix, retorna -1.0. Sino retorna la rellevancia entre el node instanciat i dst
	 */
	public double getWeight(Node dst) {
		Double weight = adjacent.get(dst);
		if (weight == null) return -1.0;
		return weight;
	}




	//////// SETTERS ////////

	/**
	 * Dona valor a la etiqueta del node instanciat
	 *
	 * @param label Etiqueta que se li assignara al node
	 * @pre 0 <= label <= 3
	 * @post Al node etiquta = label
	 * @return No
	 */
	public void setLabel(int label) {
		if (label >= 0 && label <= 3) this.label = label;
		else {
			System.out.println("Label must be between 0 and 3");
		}
	}

	/**
	 * Dona valor a la rellevancia de l'arista del node instanciat amb un altre node
	 *
	 * @throws NonExistentEdge
	 * @param dst Node desti al que se li donara la rellevancia.
	 * @param weight Rellevancia que se li donara a l'arista
	 * @pre No
	 * @post A l'arista node instanciat --> dst, rellevancia = weight
	 * @return No
	 */
	void setWeight(Node dst, double weight) throws NonExistentEdge {
		if (adjacent.put(dst, weight) == null) throw new NonExistentEdge(this, dst);
	}




	//////// NODE FUNTIONS ////////

	/**
	 * Calcula la rellevancia del Node
	 *
	 * @pre No
	 * @post La rellevancia del node es calcula depenent del tipus (Explica't a la documentacio).
	 * @return No
	 */
	public abstract void calculateRelevance();

	/**
	 * Retorna un Set dels nodes adjacents
	 *
	 * @pre No
	 * @post No
	 * @return Set de nodes adjacents al instanciat
	 */
	public Set<Node> getAdjacentNode() {
		return adjacent.keySet();
	}

	/**
	 * Retorna un Map de les adjacencies
	 *
	 * @pre No
	 * @post No
	 * @return Mapa de les adjacencies (Key = Node, Value = Double)
	 */
	public Set<Map.Entry<Node, Double>> getEdges() {
		return adjacent.entrySet();
	}

	/**
	 * Mostra si els nodes son iguals comparant primer les Ids
	 *
	 * @param node Node que es vol comparar
	 * @pre No
	 * @post No
	 * @return {@code true} si son el mateix node. {@code false} si son diferents
	 */
	public boolean equal(Node node) {
		return id.equals(node.id);
	}


	/**
	 * Es retorna a ell mateix com a autor si ho es.
	 *
	 * @pre No
	 * @post No
	 * @return Ell mateix com a autor si ho es.
	 */
	public Author asAuthor() {
		return null;
	}

	/**
	 * Es retorna a ell mateix com a conferencia si ho es.
	 *
	 * @pre No
	 * @post No
	 * @return Ell mateix com a conferencia si ho es.
	 */
	public Conference asConference() {
		return null;
	}

	/**
	 * Es retorna a ell mateix com a paper si ho es.
	 *
	 * @pre No
	 * @post No
	 * @return Ell mateix com a paper si ho es.
	 */
	public Paper asPaper() {
		return null;
	}

	/**
	 * Es retorna a ell mateix com a tema si ho es.
	 *
	 * @pre No
	 * @post No
	 * @return Ell mateix com a tema si ho es.
	 */
	public Term asTerm() {
		return null;
	}




	//////// EDGES FUNCTION ////////

	/**
	 * Afageix una arista que parteix del node instanciat a un altre (dst)
	 *
	 * @throws ExistingEdge
	 * @param dst Node desti de la nova arista.
	 * @param weight rellevancia que tindra la nova arista.
	 * @pre El node desti no es adjacent al instanciat i 0.0 <= weight < 1.0
	 * @post El node instanciat te un nou adjacent: dst. I el pes de la relacio es weight
	 * @return No
	 *
	 */
	void addEdge(Node dst, double weight) throws ExistingEdge {
		Double replace = adjacent.put(dst, weight);
		if (replace != null) throw new ExistingEdge(this, dst, replace);
		addAdjacent(dst);
	}

	/**
	 * Borra l'arista que va del node instanciat al desti dst
	 *
	 * @throws NonExistentEdge
	 * @param dst Node desti de l'arista que es bol esborrar
	 * @pre El node desti es adjacent al node instanciat
	 * @post El node instanciat i dst ja no son adjecents
	 * @return No
	 */
	void deleteEdge(Node dst) throws NonExistentEdge {
		if (adjacent.remove(dst) == null) throw new NonExistentEdge(this, dst);
		deleteAdjacent(dst);
	}

	/**
	 * S'esborra de tots els seus adjacents per ser borrat desde el graf
	 *
	 * @pre No
	 * @post El node ja no es adjacent a cap node. Ningu el referencia.
	 * @return No
	 */
	void deleteNode() {
		for (Node node : adjacent.keySet()) {
			node.deleteAdjacentNode(this);
		}
	}

	/**
	 * Esborra l'adjacencia entre el node instanciat i 'node'
	 *
	 * @param node Node al que se li borrara la relacio
	 * @pre Node es adjacent al instanciat
	 * @post node i l'instanciat ja no son adjacents
	 * @return No
	 */
	private void deleteAdjacentNode(Node node) {
		if (adjacent.remove(node) == null && (asPaper() != null || node.asPaper() != null)) throw new ProjectError(node+ " adjacent to "+this+"but edge not found in graph");
		deleteAdjacent(node);
	}

	/**
	 * Et diu si existeix una relacio entre l'instanciat i el desti
	 *
	 * @param dst Node desti que es mira si escisteix relacio
	 * @pre Dst es adjacent al node instanciat
	 * @post No
	 * @return {@code true} si existeix una relacio d'adjacencia. {@code true} si no.
	 */
	public boolean existsEdge(Node dst) {
		return adjacent.containsKey(dst);
	}

	//////// @OVERRIDE FUNCTIONS ////////

	@Override
	public String toString(){
		return ("Name: " + name + " Id: " + id + " Relevance: " + relevance + (label == -1 ? "":" Label: " +label) + " " + getClass());
	}

	@Override
	public int compareTo(Node o) {
		int compare = name.compareTo(o.name);
		if (compare == 0) return id.compareTo(o.id);
        return compare;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Node) obj).name) && (id.equals(((Node) obj).id));
	}

	/**
	 * Incremente el numero de tipus del node
	 *
	 * @param node Nou node adjacent
	 * @pre No
	 * @post El numero d'adjacents del tipus del nou node a incrementat.
	 * @return No
	 */
	private void addAdjacent(Node node) {
		if (node.asAuthor() != null) ++adjacentAuthors;
		else if (node.asConference() != null) ++adjacentConferences;
		else if (node.asPaper() != null) ++adjacentPapers;
		else if (node.asTerm() != null) ++adjacentTerms;
		else throw new ProjectError("FATAL ERROR: Node "+ node +" it's not a valid type");
	}

	/**
	 * Decrementa el numero de tipus del node
	 *
	 * @param node Antic node adjacent
	 * @pre No
	 * @post El numero d'adjacents del tipus del nou node a decrementat.
	 * @return No
	 */
	private void deleteAdjacent(Node node) {
		if (node.asAuthor() != null) --adjacentAuthors;
		else if (node.asConference() != null) --adjacentConferences;
		else if (node.asPaper() != null) --adjacentPapers;
		else if (node.asTerm() != null) --adjacentTerms;
		else throw new ProjectError("Node "+ node +" it's not a valid type");
	}

	void resetRelevance() {
		relevance = 1;
	}


	// MUGENDO > TEAKWONDO
}