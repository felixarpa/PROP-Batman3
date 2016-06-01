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
	 * <p><b>Return:</b></p> <p>Unica instancia del Graf</p>
     */
	public static Graph getInstance() {
		return instance;
	}


	/**
	 * Nateja el graf
	 *
	 * <p><b>Post:</b></p> <p>El Graf queda net, buit</p>
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
	 * <p><b>Param:</b></p> <p><em>node</em>: Node que es vol afegir</p>
	 * <p><b>Post:</b></p> <p>El graf tindra un nou node</p>
	 * <p><b>Exception:</b></p> <p>El node ja existeix</p>
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
	 * <p><b>Param:</b></p> <p><em>node</em>: Node que es vol esborrar del graf</p>
	 * <p><b>Post:</b></p> <p>El graf ja no conte el node</p>
	 * <p><b>Exception:</b></p> <p>El node no existeix</p>
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
	 * <p><b>Param:</b></p> <p><em>id</em>: Id del node que es vol esborrar</p>
	 * <p><b>Post:</b></p> <p>El node amb id = id</p>
	 * <p><b>Exception:</b></p> <p>No existeix cap el ndoe</p>
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
	 * <p><b>Param:</b></p> <p><em>src</em>: Node origen de la nova arista</p>
	 * <p><b>Param:</b></p> <p><em>dst</em>: Node desti de la nova arista</p>
	 * <p><b>Param:</b></p> <p><em>weight</em>: Pes de la nova arista</p>
	 * <p><b>Post:</b></p> <p>Nova arista entre src i dst amb pes weight</p>
	 * <p><b>Exception:</b></p> <p>L'arista ja existeix</p>
	 * <p><b>Exception:</b></p> <p>No existeix algun dels dos nodes</p>
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
	 * <p><b>Param:</b></p> <p><em>id1</em>: Id del primer node</p>
	 * <p><b>Param:</b></p> <p><em>id2</em>: Id del segon node</p>
	 * <p><b>Post:</b></p> <p>Afegeix l'arista entre el primer i segon node</p>
	 * <p><b>Exception:</b></p> <p>L'arista ja existeix</p>
	 * <p><b>Exception:</b></p> <p>No existeix algun dels dos nodes</p>
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
	 * <p><b>Param:</b></p> <p><em>node1</em>: Primer node de la nova arista</p>
	 * <p><b>Param:</b></p> <p><em>node2</em>: Segon node de la nova arista</p>
	 * <p><b>Post:</b></p> <p>Afegeix l'arista entre el primer i segon node</p>
	 * <p><b>Exception:</b></p> <p>L'arista ja existeix</p>
	 * <p><b>Exception:</b></p> <p>No existeix algun dels dos nodes</p>
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
	 * <p><b>Param:</b></p> <p><em>node1</em>: Primer node de l'arista a esborrar</p>
	 * <p><b>Param:</b></p> <p><em>node2</em>: Segon node de l'arista a esborrar</p>
	 * <p><b>Post:</b></p> <p>Esborra l'arista entre el primer i segon node</p>
	 * <p><b>Exception:</b></p> <p>No existeix l'arista</p>
	 * <p><b>Exception:</b></p> <p>No existeix algun dels dos nodes</p>
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
	 * <p><b>Param:</b></p> <p><em>id1</em>: Id del primer node</p>
	 * <p><b>Param:</b></p> <p><em>id2</em>: Id del segon node</p>
	 * <p><b>Post:</b></p> <p>Esborra l'arista entre el primer i segon node</p>
	 * <p><b>Exception:</b></p> <p>No existeix l'arista</p>
	 * <p><b>Exception:</b></p> <p>No existeix algun dels dos nodes</p>
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
	 * <p><b>Param:</b></p> <p><em>src</em>: Node origen de l'arista</p>
	 * <p><b>Param:</b></p> <p><em>dst</em>: Node desti de l'arista</p>
	 * <p><b>Param:</b></p> <p><em>weight</em>: Pes de l'arista</p>
	 * <p><b>Post:</b></p> <p>L'arista entre src i dst te pes weight</p>
	 * <p><b>Exception:</b></p> <p>L'arista no existeix</p>
     */
	public void setWeight(Node src, Node dst, double weight) throws NonExistentEdge {
		src.setWeight(dst, weight);
	}

	/**
	 * Dona el set de nodes del graf
	 *
	 * <p><b>Return:</b></p> <p>Tots els nodes</p>
     */
	public Set<Node> allNodes() {
		return graph;
	}

	/**
	 * Dona una colleccio de ids
	 *
	 * <p><b>Return:</b></p> <p>Colleccio de totes les IDs</p>
     */
	public Collection<Node> allNodesId() {
		return idMap.values();
	}

	/**
	 * Dona una colleccio de nodes per nom
	 *
	 * <p><b>Param:</b></p> <p><em>name</em>: Nom que es vol buscar</p>
	 * <p><b>Return:</b></p> <p>Colleccio dels nodes que contenen "name"</p>
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
	 * <p><b>Param:</b></p> <p><em>name</em>: Nom que es vol buscar</p>
	 * <p><b>Return:</b></p> <p>Colleccio dels temes que contenen "name"</p>
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
	 * <p><b>Param:</b></p> <p><em>id</em>: Id del node que es vol buscar</p>
	 * <p><b>Post:</b></p> <p>Node amb id = id</p>
     */
	public Node getNode(Id id) {
		return idMap.get(id);
	}

	/**
	 * Dona el numero de nodes del graf
	 *
	 * <p><b>Return:</b></p> <p>Mida del graf</p>
     */
	public int size() {
		return graph.size();
	}

	/**
	 * Dona el numero d'aristes del graf
	 *
	 * <p><b>Return:</b></p> <p>Nombre de connexions</p>
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
	 * <p><b>Post:</b></p> <p>Esborra tot el graf</p>
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






