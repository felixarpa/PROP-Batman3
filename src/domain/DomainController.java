package domain;

import comparators.*;
import domain.graph.*;
import exceptions.ProjectError;
import util.ProjectConstants;

import java.util.*;

public class DomainController {

	static User currentUser;

	private Graph graf = Graph.getInstance();

	private TreeSet<Conference> resultConference;
	private TreeSet<Author> resultAuthor;
	private TreeSet<Term> resultTerm;
	private TreeSet<Paper> resultPaper;

	private TreeSet<Node> lastSearchResult;

	private TreeSet<Relation> lastRelevanceResult;

	public DomainController() {
		DataBaseController.load();
		PageRank.execute();
	}

	public void resetGraph() {
		graf.resetGraph();
	}

	public void restoreGrapgh() {
		resetGraph();
		DataBaseController.loadOriginal();
		PageRank.execute();
	}

	public static List<String> allNames() {
		LinkedList<String> result = new LinkedList<>();

		Node ant = null;
		for (Node node : Graph.getInstance().allNodes()) {
			if (ant == null || !ant.getName().equals(node.getName())) {
				result.add(node.getName());
			}
			ant = node;
		}
		return result;
	}


	public static List<String> allNamesWithId() {
		ArrayList<String> result = new ArrayList<>(Graph.getInstance().size());

		for (Node node : Graph.getInstance().allNodes()) {
			result.add(node.getName() + "\t" + node.getId() + "\t" + node.getId().getType());
		}

		return result;
	}

	public static List<String> allTermNames() {
		LinkedList<String> result = new LinkedList<>();

		Node ant = null;
		for (Node node : Graph.getInstance().allNodes()) {
			if (node.asTerm() != null && (ant == null || !ant.getName().equals(node.getName()))) {
				result.add(node.getName());
			}
			ant = node;
		}

		return result;
	}

	public static User getCurrentUser() {
		return currentUser;
	}



	public ArrayList<String> searchingANode(String name) {
		Collection<Node> result = graf.getNode(name);
		if (result.isEmpty()) {
			//ENVIA AL VISTA CONTROLER QUE NO HAY RESULTADOS
			return null;
		}
        ArrayList<String> selected = new ArrayList<>(result.size());
        //DICE AL VISTA CONTROLLER QUE ELIGA EL RESULTADO
        for (Node n : result) {
            selected.add(n.toString());
        }
        return selected;
	}

	public ArrayList<String> searchingATerm(String name) {
    		Collection<Term> result = graf.getTerm(name);
    		if (result.isEmpty()) {
    			//ENVIA AL VISTA CONTROLER QUE NO HAY RESULTADOS
    			return null;
    		}
            ArrayList<String> selected = new ArrayList<>(result.size());
            //DICE AL VISTA CONTROLLER QUE ELIGA EL RESULTADO
            for (Term n : result) {
                selected.add(n.toString());
            }
            return selected;
    }

	public static Node stringToNode(String node) {
		String[] aux = node.split("\t");
		String id = aux[1];
		String type = aux[4];
		return Graph.getInstance().getNode(toId(id,type));
	}

	public ArrayList<ArrayList<String>> firstSearch(String node, int typeOfResult) {
		Node actual = stringToNode(node);
		ArrayList<ArrayList<String>> result = new ArrayList<>(4);
		ArrayList<String> auxiliarPaper;
		ArrayList<String> auxiliarConference;
		ArrayList<String> auxiliarAuthor;
		ArrayList<String> auxiliarTerm;
		switch (typeOfResult) {
			case ProjectConstants.NODE_RELEVANCE_RESULT:
				RelevanceComparator relevanceComparator = new RelevanceComparator();
				resultPaper = new TreeSet<>(relevanceComparator);
				resultAuthor = new TreeSet<>(relevanceComparator);
				resultConference = new TreeSet<>(relevanceComparator);
				resultTerm = new TreeSet<>(relevanceComparator);
				Searcher.search(actual, resultPaper, resultConference, resultAuthor, resultTerm);
				auxiliarPaper = new ArrayList<>(resultPaper.size());
				for (Node n: resultPaper.descendingSet()) {
					auxiliarPaper.add(n.toString());
				}
				auxiliarConference = new ArrayList<>(resultConference.size());
				for (Node n: resultConference.descendingSet()) {
					auxiliarConference.add(n.toString());
				}
				auxiliarAuthor = new ArrayList<>(resultAuthor.size());
				for (Node n: resultAuthor.descendingSet()) {
					auxiliarAuthor.add(n.toString());
				}
				auxiliarTerm = new ArrayList<>(resultTerm.size());
				for (Node n: resultTerm.descendingSet()) {
					auxiliarTerm.add(n.toString());
				}

				break;
			case ProjectConstants.RELATION_RELEVANCE_RESULT:
				TreeMap<Double, TreeSet<Paper>> resultRelevancePaper = new TreeMap<>();
				TreeMap<Double, TreeSet<Term>> resultRelevanceTerm = new TreeMap<>();
				TreeMap<Double, TreeSet<Conference>> resultRelevanceConference = new TreeMap<>();
				TreeMap<Double, TreeSet<Author>> resultRelevanceAuthor = new TreeMap<>();
				Searcher.search(actual, resultRelevancePaper, resultRelevanceConference, resultRelevanceAuthor, resultRelevanceTerm);
				auxiliarAuthor = new ArrayList<>(resultRelevanceAuthor.size()*2);
				for (Map.Entry<Double, TreeSet<Author>> nodes : resultRelevanceAuthor.descendingMap().entrySet()) {
					auxiliarAuthor.add(Integer.toString(nodes.getValue().size()));
					auxiliarAuthor.add(Double.toString(nodes.getKey()));
					for (Node n : nodes.getValue()) {
						auxiliarAuthor.add(n.toString());
					}
				}
				auxiliarConference = new ArrayList<>(resultRelevanceConference.size()*2);
				for (Map.Entry<Double, TreeSet<Conference>> nodes : resultRelevanceConference.descendingMap().entrySet()) {
					auxiliarConference.add(Integer.toString(nodes.getValue().size()));
					auxiliarConference.add(Double.toString(nodes.getKey()));
					for (Node n : nodes.getValue()) {
						auxiliarConference.add(n.toString());
					}
				}
				auxiliarTerm = new ArrayList<>(resultRelevanceTerm.size()*2);
				for (Map.Entry<Double, TreeSet<Term>> nodes : resultRelevanceTerm.descendingMap().entrySet()) {
					auxiliarTerm.add(Integer.toString(nodes.getValue().size()));
					auxiliarTerm.add(Double.toString(nodes.getKey()));
					for (Node n : nodes.getValue()) {
						auxiliarTerm.add(n.toString());
					}
				}
				auxiliarPaper = new ArrayList<>(resultRelevancePaper.size()*2);
				for (Map.Entry<Double, TreeSet<Paper>> nodes : resultRelevancePaper.descendingMap().entrySet()) {
					auxiliarPaper.add(Integer.toString(nodes.getValue().size()));
					auxiliarPaper.add(Double.toString(nodes.getKey()));
					for (Node n : nodes.getValue()) {
						auxiliarPaper.add(n.toString());
					}
				}

				break;
			default:
				throw new ProjectError("Invalid parameter typeOfResult: " + typeOfResult);

		}

		result.add(auxiliarAuthor);
		result.add(auxiliarConference);
		result.add(auxiliarPaper);
		result.add(auxiliarTerm);
		return result;

	}

	public void close() {
	    DataBaseController.download();
	 }

	private static Id toId(String id, String type) {
		int ide = Integer.parseInt(id);
		switch (type) {
			case  "domain.graph.Author":
				return Author.makeId(ide);
			case "domain.graph.Conference":
				return Conference.makeId(ide);
			case "domain.graph.Term":
				return Term.makeId(ide);
			case "domain.graph.Paper":
				return Paper.makeId(ide);
			default:
				throw new ProjectError("Invalid type: "+type);
		}
	}

	public ArrayList<String> reorderSearch(int typeOfSearch, int typeOfEntity, int typeOfOrdering, boolean ascending) {
		ArrayList<String> result;
		TreeSet<Node> aux;

		switch (typeOfOrdering) {
			case ProjectConstants.NAME_ORDER:
				aux = new TreeSet<>();
				break;
			case ProjectConstants.ID_ORDER:
				aux = new TreeSet<>(new IdComparator());
				break;
			case ProjectConstants.RELEVANCE_ORDER:
				aux = new TreeSet<>(new RelevanceComparator());
				break;
			default:
				throw new ProjectError("Invalid parameter typeOfEntity");
		}

		switch (typeOfSearch) {
			case 1:
				switch (typeOfEntity) {
					case ProjectConstants.AUTHOR_TYPE:
						aux.addAll(resultAuthor);
						break;
					case ProjectConstants.CONFERENCE_TYPE:
						aux.addAll(resultConference);
						break;
					case ProjectConstants.PAPER_TYPE:
						aux.addAll(resultPaper);
						break;
					case ProjectConstants.TERM_TYPE:
						aux.addAll(resultTerm);
						break;
					default:
						throw new ProjectError("Invalid parameter typeOfEntity: " + typeOfEntity);
				}
				break;
			case 2:
				aux.addAll(lastSearchResult);
				break;
			default:
				throw new ProjectError("Invalid parameter typeOfSearch: "+ typeOfSearch);
		}

		result = new ArrayList<>(aux.size());
		if (!ascending) {
			for (Node n: aux.descendingSet()) {
				result.add(n.toString());
			}
		}
		else {
			for (Node n: aux) {
				result.add(n.toString());
			}
		}

		return result;
	}


	public ArrayList<String> reorderSearch3(int typeOfOrdering, boolean ascending) {

        TreeSet<Relation> aux;

        switch (typeOfOrdering) {
            case ProjectConstants.NAME_ORDER1:
                aux = new TreeSet<>(new RelationName1Comparator());
                break;
            case ProjectConstants.NAME_ORDER2:
                aux = new TreeSet<>(new RelationName2Comparator());
                break;
            case ProjectConstants.RELEVANCE_ORDER1:
                aux = new TreeSet<>(new RelationSingleRelevance1Comparator());
                break;
            case ProjectConstants.RELEVANCE_ORDER2:
                aux = new TreeSet<>(new RelationSingleRelevance2Comparator());
                break;
            case ProjectConstants.RELATION_ID1:
                aux = new TreeSet<>(new RelationIdComparator1());
                break;
            case ProjectConstants.RELATION_ID2:
                aux = new TreeSet<>(new RelationIdComparator2());
                break;
            case ProjectConstants.RELATION_RELEVANCE:
                aux = new TreeSet<>(new RelationRelevanceComparator());
                break;
            default:
                throw new ProjectError("Invalid parameter typeOfOrdering: "+typeOfOrdering);
        }

        aux.addAll(lastRelevanceResult);

		if (ascending) return convertRelationResult(aux);
		else return convertRelationResult(aux.descendingSet());
    }

	public ArrayList<String> secondSearch(int type) {
		if (type < 0 || type > 3) throw new ProjectError("Invalid parameter type, must be between 0 and 3, we got "+type);
		lastSearchResult = Searcher.allTypeNodes(type);
		ArrayList<String> result = new ArrayList<>(lastSearchResult.size());
		for (Node n : lastSearchResult.descendingSet()) {
			result.add(n.toString());
		}
		return result;
	}

	public ArrayList<String> searchSimilarRelevance(String node, int op) {
		Node searhNode = stringToNode(node);
		TreeSet<Node> treeSet = Searcher.similarRelevance(searhNode, op);
		ArrayList<String> result = new ArrayList<>(treeSet.size());
		for (Node n : treeSet.descendingSet()) {
			result.add(n.toString());
		}
		return result;
	}

	public ArrayList<String> thirdSearch(int typesrc, int typedst) {
		lastRelevanceResult = Searcher.allRelevanceTypeNodes(typesrc, typedst);
		return convertRelationResult(lastRelevanceResult.descendingSet());
	}

	public ArrayList<String> searchSimilarRelationRelevance(String src, String dst, int op) {
		Node nodesrc = stringToNode(src);
		Node nodedst = stringToNode(dst);
		LinkedList<Relation> ret = Searcher.similarRelationRelevance(nodesrc, nodedst, op, lastRelevanceResult.descendingSet());
		return convertRelationResult(ret);
	}

	private ArrayList<String> convertRelationResult(Collection<Relation> resultSearch) {
        ArrayList<String> result = new ArrayList<>(resultSearch.size()*4);
		double ant = -1;
		int pos = -1, count = 0, i = 0;
        for (Relation entry : resultSearch) {
			if (ant != entry.getRelevance()) {
				result.add(null);
				ant = entry.getRelevance();
				if (pos != -1) {
					result.set(pos, Integer.toString(count));
					count = 0;
				}
				pos = i;
				result.add(Double.toString(entry.getRelevance()));
				i += 2;
			}
			result.add(entry.getNode1().toString());
			result.add(entry.getNode2().toString());
			++count;
			i += 2;
        }
		if (pos != -1) {
			result.set(pos, Integer.toString(count));
		}
        return result;
    }

	public static void recalculate() {
		Graph.getInstance().reset();
		PageRank.execute();
	}

	public void doNothing() {
		System.out.println("I do nothing");
	}
}
