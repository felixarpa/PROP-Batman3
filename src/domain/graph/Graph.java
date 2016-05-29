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

	public static Graph getInstance() {
		return instance;
	}

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

	public void deleteNode(Id id) throws NonExistentNode {
		Node node = idMap.remove(id);
		if (node == null) throw new NonExistentNode(id);

		node.deleteNode();
		if (!graph.remove(node)) throw new ProjectError("Graph not coherent with the id map");
	}

	public void addSingleEdge(Node src, Node dst, double weight) throws ExistingEdge, NonExistentEdgeNodes {
		if (src.asPaper() != null || dst.asPaper() != null) throw new ProjectError("Attempted to add a single edge between two directly connected nodes");
        if (!existsNode(src)) throw new NonExistentEdgeNodes(src, null);
		src.addEdge(dst, weight);
		++edges;
	}

	public void addEdge(Id id1, Id id2) throws ExistingEdge, NonExistentEdgeNodes {
		Node node1 = idMap.get(id1);
		Node node2 = idMap.get(id2);
		if (node1 == null || node2 == null) throw new NonExistentEdgeNodes(node1, node2);
		node1.addEdge(node2, -1);
		node2.addEdge(node1, -1);
		edges += 2;
	}

	public void addEdge(Node node1, Node node2) throws NonExistentEdgeNodes, ExistingEdge {
		Node exception1 = (existsNode(node1) ? null : node1);
		Node exception2 = (existsNode(node2) ? null : node2);
		if (exception1 != null || exception2 != null) throw new NonExistentEdgeNodes(node1, node2);
		node1.addEdge(node2, -1);
		node2.addEdge(node1, -1);
		edges += 2;
	}

	public void deleteEdge(Node src, Node dst) throws NonExistentEdge, NonExistentEdgeNodes {
        if (!existsNode(src)) throw new NonExistentEdgeNodes(src, null);
		src.deleteEdge(dst);
		dst.deleteEdge(src);
		edges -= 2;
	}

	public void setWeight(Node src, Node dst, double weight) throws NonExistentEdge {
		src.setWeight(dst, weight);
	}

	public Set<Node> allNodes() {
		return graph;
	}
	
	public Collection<Node> allNodesId() {
		return idMap.values();
	}
	
	/*public Node isSingleTerm(String name) {
		Set<Node> result = (graph.subSet(new Term(name, 0), true, new Term(name, 536870911), true));
		if (result.size() == 1) {
			for (Node n: result) return n;
		}
		return null;
	}*/
	
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
		for (LinkedList<Node> nodes : map.values()) {
			result.addAll(nodes);
		}
		return result;
	}

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
		for (LinkedList<Term> nodes : map.values()) {
			result.addAll(nodes);
		}
		return result;
	}

	public Node getNode(Id id) {
		return idMap.get(id);
	}

	public int size() {
		return graph.size();
	}

    public int edgeSize() {
        return edges/2;
    }

	public boolean existsNode(Node node) {
		return idMap.containsKey(node.getId());
	}

	public void reset() {
        for (Node node : graph) {
            node.resetRelevance();
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






