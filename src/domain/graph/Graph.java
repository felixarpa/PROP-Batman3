package domain.graph;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

import domain.DomainController;
import exceptions.*;

public class Graph {

	private static Graph instance = new Graph();
    private TreeMap<Id, Node> idMap;
	private TreeSet<Node> graph;
	private int edges = 0;

	private Graph() {
		graph = new TreeSet<>();
        idMap = new TreeMap<>();
	}

	/**
	 * Dona la unica instancia del Graf
	 *
	 * <p><b>Return:</b></p> Unica instancia del Graf
     */
	public static Graph getInstance() {
		return instance;
	}


	/**
	 * Nateja el graf
	 *
	 * <p><b>Post:</b></p> El Graf queda net, buit
     */
	public void resetGraph() {
		for (Node node : graph) {
			node.clear();
		}
		idMap.clear();
		graph.clear();
		edges = 0;
		System.gc();
	}

	/**
	 * Afageig un node
	 *
	 * <p><b>Param:</b></p> Node que es vol afegir
	 * <p><b>Post:</b></p> El graf tindra un nou node
	 * <p><b>Exception:</b></p> El node ja existeix
     */
	public void addNode(Node node) throws ExistingNode {
		Node replace  = idMap.get(node.getId());
		if (replace != null) throw new ExistingNode(replace);
		graph.add(node);
		// Esto es exactamente lo que queria hacer para buscar por palabras <3
		/*String[] substrings = node.getName().split(" ");
		for (String s : substrings) {
            TreeSet<Node> nodes = names.get(s);
			if (nodes == null) {
                nodes = new TreeSet<>();
				names.put(s, nodes);
			}
			nodes.add(node);
		}*/
		if (idMap.put(node.getId(),node) != null) throw new ProjectError("Graph not coherent with the id map");
    }

	/**
	 * Esborra un node
	 *
	 * <p><b>Param:</b></p> Node que es vol esborrar del graf
	 * <p><b>Post:</b></p> El graf ja no conte el node
	 * <p><b>Exception:</b></p> El node no existeix
     */
	public void deleteNode(Node node) throws NonExistentNode {
		if (!existsNode(node)) throw new NonExistentNode(node);

		/*String[] substrings = node.getName().split(" ");
		for (String s : substrings) {
            TreeSet<Node> nodes = names.get(s);
			nodes.remove(node);
			if (nodes.size() == 0) names.remove(s);
		}*/

		node.deleteNode();
		graph.remove(node);
		if (idMap.remove(node.getId()) == null) throw new ProjectError("Graph not coherent with the id map");
	}

	/**
	 * Esborra un node amb id = id
	 *
	 * <p><b>Param:</b></p> Id del node que es vol esborrar
	 * <p><b>Post:</b></p> El node amb id = id
	 * <p><b>Exception:</b></p> No existeix cap el ndoe
     */
	public void deleteNode(Id id) throws NonExistentNode {
		Node node = idMap.remove(id);
		if (node == null) throw new NonExistentNode(id);

		node.deleteNode();
		if (!graph.remove(node)) throw new ProjectError("Graph not coherent with the id map");
	}

	/**
	 * Afageix una arista en una sola direccio
	 *
	 * <p><b>Param:</b></p> src Node origen de la nova arista
	 * <p><b>Param:</b></p> dst Node desti de la nova arista
	 * <p><b>Param:</b></p> weight Pes de la nova arista
	 * <p><b>Post:</b></p> Nova arista entre src i dst amb pes weight
	 * <p><b>Exception:</b></p> L'arista ja existeix
	 * <p><b>Exception:</b></p> No existeix algun dels dos nodes
     */
	public void addSingleEdge(Node src, Node dst, double weight) throws ExistingEdge, NonExistentEdgeNodes {
		if (src.asPaper() != null || dst.asPaper() != null) throw new ProjectError("Attempted to add a single edge between two directly connected nodes");
        if (!existsNode(src)) throw new NonExistentEdgeNodes(src, null);
		src.addEdge(dst, weight);
		++edges;
	}

	/**
	 * Afageix una arista en les dues direccions per id
	 *
	 * <p><b>Param:</b></p> id1, id del primer node
	 * <p><b>Param:</b></p> id2, id del segon node
	 * <p><b>Post:</b></p> Afegeix l'arista entre el primer i segon node
	 * <p><b>Exception:</b></p> L'arista ja existeix
	 * <p><b>Exception:</b></p> No existeix algun dels dos nodes
     */
	public void addEdge(Id id1, Id id2) throws ExistingEdge, NonExistentEdgeNodes {
		Node node1 = idMap.get(id1);
		Node node2 = idMap.get(id2);
		if (node1 == null || node2 == null) throw new NonExistentEdgeNodes(node1, node2);
		node1.addEdge(node2, -1);
		node2.addEdge(node1, -1);
		edges += 2;
	}

	/**
	 * Afageix una arista en les dues direccions
	 *
	 * <p><b>Param:</b></p> Primer node de la nova arista
	 * <p><b>Param:</b></p> Segon node de la nova arista
	 * <p><b>Post:</b></p> Afegeix l'arista entre el primer i segon node
	 * <p><b>Exception:</b></p> L'arista ja existeix
	 * <p><b>Exception:</b></p> No existeix algun dels dos nodes
     */
	public void addEdge(Node node1, Node node2) throws NonExistentEdgeNodes, ExistingEdge {
		if (!existsNode(node1) || !existsNode(node2)) throw new NonExistentEdgeNodes(node1, node2);
		node1.addEdge(node2, -1);
		node2.addEdge(node1, -1);
		edges += 2;
	}

	/**
	 * Esborra una arista
	 *
	 * <p><b>Param:</b></p> Primer node de l'arista a esborrar
	 * <p><b>Param:</b></p> Segon node de l'arista a esborrar
	 * <p><b>Post:</b></p> Esborra l'arista entre el primer i segon node
	 * <p><b>Exception:</b></p> No existeix l'arista
	 * <p><b>Exception:</b></p> No existeix algun dels dos nodes
     */
	public void deleteEdge(Node src, Node dst) throws NonExistentEdge, NonExistentEdgeNodes {
        if (!existsNode(src) || !existsNode(dst)) throw new NonExistentEdgeNodes(src, dst);
		src.deleteEdge(dst);
		dst.deleteEdge(src);
		edges -= 2;
	}

	/**
	 * Esborra una arista per id
	 *
	 * <p><b>Param:</b></p> id1, id del primer node
	 * <p><b>Param:</b></p> id2, id del segon node
	 * <p><b>Post:</b></p> Esborra l'arista entre el primer i segon node
	 * <p><b>Exception:</b></p> No existeix l'arista
	 * <p><b>Exception:</b></p> No existeix algun dels dos nodes
     */
	public void deleteEdge(Id src, Id dst) throws NonExistentEdgeNodes, NonExistentEdge {
		Node node1 = idMap.get(src);
		Node node2 = idMap.get(dst);
		if (node1 == null || node2 == null) throw new NonExistentEdgeNodes(node1, node2);

		node1.deleteEdge(node2);
		node2.deleteEdge(node1);
		edges -= 2;
	}

	/**
	 * Dona un valor a una arista
	 *
	 * <p><b>Param:</b></p> src Node origen de l'arista
	 * <p><b>Param:</b></p> dst Node desti de l'arista
	 * <p><b>Param:</b></p> weight Pes de l'arista
	 * <p><b>Post:</b></p> L'arista entre src i dst te pes weight
	 * <p><b>Exception:</b></p> L'arista no existeix
     */
	public void setWeight(Node src, Node dst, double weight) throws NonExistentEdge {
		src.setWeight(dst, weight);
	}

	/**
	 * Dona el set de nodes del graf
	 *
	 * <p><b>Return:</b></p> Tots els nodes
     */
	public Set<Node> allNodes() {
		return graph;
	}

	/**
	 * Dona una colleccio de ids
	 *
	 * <p><b>Return:</b></p> Colleccio de totes les IDs
     */
	public Collection<Node> allNodesId() {
		return idMap.values();
	}

	/**
	 * Dona una colleccio de nodes per nom
	 *
	 * <p><b>Param:</b></p> Nom que es vol buscar
	 * <p><b>Return:</b></p> Colleccio dels nodes que contenen "name"
     */
	public Collection<Node> getNode(String name) {
		TreeMap<Integer, LinkedList<Node>> map = new TreeMap<>();
		for (Node node : graph) {
			int index = node.getName().toLowerCase().indexOf(name.toLowerCase());
			if (index >= 0) {
				LinkedList<Node> nodes = map.get(index);
				if (nodes == null) {
					nodes = new LinkedList<>();
					map.put(index, nodes);
				}
				nodes.add(node);
			}
		}
		LinkedList<Node> result = new LinkedList<>();
		map.values().forEach(result::addAll);
		return result;
	}

	/**
	 * Dona una colleccio amb tots els temes
	 *
	 * <p><b>Param:</b></p> Nom que es vol buscar
	 * <p><b>Return:</b></p> Colleccio dels temes que contenen "name"
     */
	public Collection<Term> getTerm(String name) {
		TreeMap<Integer, LinkedList<Term>> map = new TreeMap<>();
		for (Node node : graph) {
			if (node.asTerm() != null && !DomainController.getCurrentUser().isFavorite(node.asTerm())) {
				int index = node.getName().toLowerCase().indexOf(name.toLowerCase());
				if (index >= 0) {
					LinkedList<Term> nodes = map.get(index);
					if (nodes == null) {
						nodes = new LinkedList<>();
						map.put(index, nodes);
					}
					nodes.add(node.asTerm());
				}
			}
		}
		LinkedList<Term> result = new LinkedList<>();
		map.values().forEach(result::addAll);
		return result;
	}

	/**
	 * Retorna el node amb id = id
	 *
	 * <p><b>Param:</b></p> id del node que es vol buscar
	 * <p><b>Post:</b></p> Node amb id = id
     */
	public Node getNode(Id id) {
		return idMap.get(id);
	}

	/**
	 * Dona el numero de nodes del graf
	 *
	 * <p><b>Return:</b></p> Mida del graf
     */
	public int size() {
		return graph.size();
	}

	/**
	 * Dona el numero d'aristes del graf
	 *
	 * <p><b>Return:</b></p> Nombre de connexions
     */
    public int edgeSize() {
        return edges/2;
    }

	private boolean existsNode(Node node) {
		return idMap.containsKey(node.getId());
	}

	/**
	 * Fa reset al graf, dona els valors per defecte. Borra els nodes i aristes afegits i esborrats.
	 *
	 * <p><b>Post:</b></p> Esborra tot el graf
     */
	public void reset() {
        for (Node node : graph) {
            if (node.asPaper() == null) {
                LinkedList<Node> copy = new LinkedList<>(node.getAdjacentNode());
                for (Node adjacent : copy) {
                    if (adjacent.asPaper() == null) {
                        try {
                            node.deleteEdge(adjacent);
                        } catch (NonExistentEdge nonExistentEdge) {
                            throw new ProjectError("FATAL ERROR: UNEXPECTED ERROR ERASING ADJACENT NODES IN RESET");
                        }
                    }
                }
            }
			node.resetRelevance();
        }
    }

}

//  37.791	NODES
//	14.475	Authors
//      20	Conferences
//	14.376	Paper
// 	 8.920	Term

// 170.794	EDGES
// 114.624	Edge(Pap-Term)
//  14.376	Edge(Pap-Conf)
//  41.794	Edge(Pap-Auth)






