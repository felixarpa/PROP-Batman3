package drivers;
import java.util.*;

import domain.Relation;
import domain.UserController;
import domain.graph.*;
import domain.PageRank;
import domain.Searcher;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;
import util.ProjectConstants;

public class DriverSearch {
	static Searcher search;
	static Graph test;
	static ArrayList<Node> vnode;
	static String command;
	static Scanner sc;
	static PageRank pr;
	
	private static void show() {
		System.out.println("NODES:\n");
		for (Node n : vnode) {
            System.out.println(n);
        }
		System.out.println("\nEDGES:\n");
		for (Node n : test.allNodes()) {
            System.out.println(n);
            System.out.println("Adjacent to:");
            for (Node m : n.getAdjacentNode()) {
                System.out.print(m + " Weight: " + n.getWeight(m) + "\n");
            }
            System.out.print("\n");
        }
	}
	
	private static void firstsearch() {
        System.out.println("Select the node of wich you want to execute the first search (number between 0 and "+(vnode.size()-1)+" ).");

        int i = 0;
        for (Node n : vnode) {
            System.out.println(i + "- "+n);
            ++i;
        }
        int choice = sc.nextInt();
        while(choice < 0||choice > vnode.size()-1) {
            System.out.println("ERROR: Number must be between 0 and " + (vnode.size()-1));
            System.out.println("Select the node of wich you want to execute the first search (number between 0 and "+(vnode.size()-1)+" ).");
            choice = sc.nextInt();
        }
        System.out.println("Do you want the result with the relevance of the adjacent node or with the relevance of the relation?");
		System.out.println("Write node or relation.");
        command = sc.next(); 
        while (!command.equals("node") && !command.equals("relation")) {
        	System.out.println("ERROR: Wrong type.");
        	System.out.println("Select the type of search (regular, ordered)");
            command = sc.next(); 
        }
        if (command.equals("node")) {
			TreeSet<Paper> resultPaper = new TreeSet<>(new Comparator<Paper>() {
				@Override
				public int compare(Paper o1, Paper o2) {
					int compare = (int)(o2.getRelevance()-o1.getRelevance());
					if (compare == 0) {
						compare = o1.getId().compareTo(o2.getId());
					}
					return compare;
				}
			});
			TreeSet<Author> resultAuthor = new TreeSet<>(new Comparator<Author>() {
				@Override
				public int compare(Author o1, Author o2) {
					int compare = (int)(o2.getRelevance()-o1.getRelevance());
					if (compare == 0) {
						compare = o1.getId().compareTo(o2.getId());
					}
					return compare;
				}
			});
			TreeSet<Conference> resultConference = new TreeSet<>(new Comparator<Conference>() {
				@Override
				public int compare(Conference o1, Conference o2) {
					int compare = (int)(o2.getRelevance()-o1.getRelevance());
					if (compare == 0) {
						compare = o1.getId().compareTo(o2.getId());
					}
					return compare;
				}
			});
			TreeSet<Term> resultTerm = new TreeSet<>(new Comparator<Term>() {
				@Override
				public int compare(Term o1, Term o2) {
					int compare = (int)(o2.getRelevance()-o1.getRelevance());
					if (compare == 0) {
						compare = o2.getId().compareTo(o1.getId());
					}
					return compare;
				}
			});
            Searcher.search(vnode.get(choice), resultPaper, resultConference, resultAuthor, resultTerm);
            System.out.println("AUTHORS");
           
            for (Node n : resultAuthor) {
            	System.out.println(n);
            }
            System.out.println("----------------------------");
            System.out.println("CONFERENCES");
            
            for (Node n : resultConference) {
            	System.out.println(n);
            }
            System.out.println("----------------------------");
            System.out.println("TERMS");
            
            for (Node n : resultTerm) {
            	System.out.println(n);
            }
            System.out.println("----------------------------");
            System.out.println("PAPERS");
            
            for (Node n : resultPaper) {
            	System.out.println(n);
            }
        }
        else {
        	TreeMap<Double,TreeSet<Paper>> resultPaper = new TreeMap<>();
            TreeMap<Double,TreeSet<Term>> resultTerm = new TreeMap<>();
            TreeMap<Double,TreeSet<Conference>> resultConference = new TreeMap<>();
            TreeMap<Double,TreeSet<Author>> resultAuthor = new TreeMap<>();
            Searcher.search(vnode.get(choice), resultPaper, resultConference, resultAuthor, resultTerm);
            System.out.println("AUTHORS");
            for (Map.Entry<Double, TreeSet<Author>> nodes : resultAuthor.entrySet()) {
            	System.out.println("RELEVANCE :" + nodes.getKey());
            	for (Node n : nodes.getValue()) {
            		System.out.println(n);
            	}
            }
            System.out.println("----------------------------");
            System.out.println("CONFERENCES");
            for (Map.Entry<Double, TreeSet<Conference>> nodes : resultConference.entrySet()) {
            	System.out.println("RELEVANCE :" + nodes.getKey());
            	for (Node n : nodes.getValue()) {
            		System.out.println(n);
            	}
            }
            System.out.println("----------------------------");
            System.out.println("TERMS");
            for (Map.Entry<Double, TreeSet<Term>> nodes : resultTerm.entrySet()) {
            	System.out.println("RELEVANCE :" + nodes.getKey());
            	for (Node n : nodes.getValue()) {
            		System.out.println(n);
            	}
            }
            System.out.println("----------------------------");
            System.out.println("PAPERS");
            for (Map.Entry<Double, TreeSet<Paper>> nodes : resultPaper.entrySet()) {
            	System.out.println("RELEVANCE :" + nodes.getKey());
            	for (Node n : nodes.getValue()) {
            		System.out.println(n);
            	}
            }
        }
        
        
	}

	private static void secondsearch() {
		System.out.println("Introduce the type of the nodes you want to show (author,conference,paper,term).");
		command = sc.next();
		while (!command.equals("author") && !command.equals("conference") && !command.equals("paper") && !command.equals("term")) {
			System.out.println("ERROR: Wrong type.");
			command = sc.next();
		}
		int type = -1;
		if (command.equals("author")) {
			type = ProjectConstants.AUTHOR_TYPE;
		}
		else if(command.equals("conference")) {
			type = ProjectConstants.CONFERENCE_TYPE;
		}
		else if (command.equals("paper")) {
			type = ProjectConstants.PAPER_TYPE;
		}
		else if (command.equals("term")) {
			type = ProjectConstants.TERM_TYPE;
		}
		int i = 0;
		ArrayList<Node> result = new ArrayList<Node>(Searcher.allTypeNodes(type));
		for (Node n: result) {
			System.out.println(i + "- " + n);
			++i;
		}
		System.out.println("Do you want to search relative nodes of a specified one?");
		System.out.println("Awnser yes or no.");
		command = sc.next();
		while (!command.equals("yes") && !command.equals("no")) {
			System.out.println("ERROR: Wrong awnser.");
			System.out.println("Awnser yes or no.");
			command = sc.next();
		}
		if (command.equals("yes")) {
			System.out.println("Select a node (0.." + result.size() + ')');
			int choice = sc.nextInt();
			while (choice < 0 && choice > result.size()) {
				System.out.println("ERROR: Wrong node.");
				System.out.println("Select a node (0.." + result.size() + ')');
				choice = sc.nextInt();
			}
			System.out.println("Do you want to see more, less or equal relevant nodes?");
			System.out.println("Type more, less or equal.");
			command = sc.next();
			while (!command.equals("more") && !command.equals("less") && !command.equals("equal")) {
				System.out.println("ERROR: Wrong type.");
				System.out.println("Type more, less or equal.");
				command = sc.next();

			}
			TreeSet<Node> similar;
			if (command.equals("more")) {
				similar = Searcher.similarRelevance(result.get(choice),1);
			}
			else if (command.equals("less")) {
				similar = Searcher.similarRelevance(result.get(choice),-1);
			}
			else {
				similar = Searcher.similarRelevance(result.get(choice),0);
			}
			for (Node n: similar) {
				System.out.println(n);
			}
		}
	}

	private static void thirdsearch() {
		/*System.out.println("Introduce the first type of the nodes you want to show (author,conference,paper,term).");
		String type1 = sc.next();
		while (!type1.equals("author") && !type1.equals("conference") && !type1.equals("paper") && !type1.equals("term")) {
			System.out.println("ERROR: Wrong type.");
			type1 = sc.next();
		}
		int typeone = -1;
		if (type1.equals("author")) {
			typeone = util.ProjectConstants.AUTHOR_TYPE;
		}
		else if(type1.equals("conference")) {
			typeone = util.ProjectConstants.CONFERENCE_TYPE;
		}
		else if (type1.equals("paper")) {
			typeone = util.ProjectConstants.TERM_TYPE;
		}
		else if (type1.equals("term")) {
			typeone = util.ProjectConstants.TERM_TYPE;
		}
		System.out.println("Introduce the first type of the nodes you want to show (author,conference,paper,term).");
		String type2 = sc.next();
		while (!type2.equals("author") && !type2.equals("conference") && !type2.equals("paper") && !type2.equals("term")) {
			System.out.println("ERROR: Wrong type.");
			type2 = sc.next();
		}
		int typesecond = -1;
		if (type2.equals("author")) {
			typesecond = util.ProjectConstants.AUTHOR_TYPE;
		}
		else if(type2.equals("conference")) {
			typesecond = util.ProjectConstants.CONFERENCE_TYPE;
		}
		else if (type2.equals("paper")) {
			typesecond = util.ProjectConstants.TERM_TYPE;
		}
		else if (type2.equals("term")) {
			typesecond = util.ProjectConstants.TERM_TYPE;
		}
		TreeSet<Relation> result = Searcher.allRelevanceTypeNodes(typeone,typesecond);
		int i = 0;
		ArrayList<Node[]> toSelect = new ArrayList<>();
		for (Map.Entry<Double,TreeSet<Node[]>> entry : result.entrySet()) {
			System.out.println('\n' + "RELEVANCE: " + entry.getKey());
			for (Node[] pair: entry.getValue()) {
				if (pair.length != 2) throw new ProjectError("Search didn't geat a coherent result.");
				System.out.println("Edge " + i + ':');
				System.out.println(pair[0] + "\n" + pair[1]);
				toSelect.add(pair);
				++i;
			}
		}
		System.out.println("Do you want to search relative edges of a specified one?");
		System.out.println("Awnser yes or no.");
		command = sc.next();
		while (!command.equals("yes") && !command.equals("no")) {
			System.out.println("ERROR: Wrong awnser.");
			System.out.println("Awnser yes or no.");
			command = sc.next();
		}
		if (command.equals("yes")) {
			System.out.println("Select a edge (0.." + (toSelect.size()-1) + ')');
			int choice = sc.nextInt();
			while (choice < 0 && choice > toSelect.size()-1) {
				System.out.println("ERROR: Wrong node.");
				System.out.println("Select a edge (0.." + (toSelect.size()-1) + ')');
				choice = sc.nextInt();
			}
			System.out.println("Do you want to see more, less or equal relevant edges?");
			System.out.println("Type more, less or equal.");
			command = sc.next();
			while (!command.equals("more") && !command.equals("less") && !command.equals("equal")) {
				System.out.println("ERROR: Wrong type.");
				System.out.println("Type more, less or equal.");
				command = sc.next();

			}

			SortedMap<Double,TreeSet<Node[]>> similar;
			if (command.equals("more")) {
				similar = Searcher.similarRelationRelevance(toSelect.get(choice)[0],toSelect.get(choice)[1],1,result);
			}
			else if (command.equals("less")) {
				similar = Searcher.similarRelationRelevance(toSelect.get(choice)[0],toSelect.get(choice)[1],-1,result);
			}
			else {
				similar = Searcher.similarRelationRelevance(toSelect.get(choice)[0],toSelect.get(choice)[1],0,result);
			}
			for (Map.Entry<Double,TreeSet<Node[]>> entryordered : similar.entrySet()) {
				System.out.println("RELEVANCE: " + entryordered.getKey() + '\n');
				for (Node[] pair: entryordered.getValue()) {
					if (pair.length != 2) throw new ProjectError("Search didn't geat a coherent result.");
					System.out.println(pair[0] + "\n" + pair[1] + '\n');
				}
			}
		}*/

	}

	private static void search() {
		System.out.println("What search do you want to execute? (1,2,3)");
		int choice = sc.nextInt();
		while (choice > 3 || choice < 1) {
			System.out.println("ERROR: Wrong search");
			System.out.println("What search do you want to execute? (1,2,3)");
			choice = sc.nextInt();
		}
		switch (choice) {
			case 1:
				firstsearch();
				break;
			case 2:
				secondsearch();
				break;
			case 3:
				thirdsearch();
				break;
		}
	}
	
	private static void calculate() {
		PageRank.execute();
		System.out.println("The PageRank has been executed successfully.");
	}
	
	private static void help() {
         System.out.println("show: Shows the default graph.");
         System.out.println("search: Applies the first type of search of a specified node.");
         System.out.println("exit : Close the driver.");
   }
	
	private static void initialize() {
		try {
			UserController.logIn("hola","123");
		} catch (IncorrectPassword | NonExistentUser incorrectPassword) {
			incorrectPassword.printStackTrace();
		}
		test = Graph.getInstance();
		sc = new Scanner(System.in);
		vnode =  new ArrayList<>();
		vnode.add(new Paper("p1",1));
		vnode.add(new Paper("p2",2));
		vnode.add(new Paper("p3",3));
		vnode.add(new Paper("p4",4));
		vnode.add(new Author("a1",5));
		vnode.add(new Author("a2",6));
		vnode.add(new Author("a3",7));
		vnode.add(new Author("a4",8));
		vnode.add(new Author("a5",9));
		vnode.add(new Conference("c1",10));
		vnode.add(new Conference("c2",11));
		vnode.add(new Term("t1",12));
		vnode.add(new Term("t2",13));
		vnode.add( new Term("t3",14));
		vnode.add( new Term("t4",15));
		try {
			for (Node n : vnode) test.addNode(n);
			test.addEdge(vnode.get(0).getId(), vnode.get(4).getId());
			test.addEdge(vnode.get(0).getId(), vnode.get(5).getId());
			test.addEdge(vnode.get(0).getId(), vnode.get(6).getId());
			test.addEdge(vnode.get(0).getId(), vnode.get(11).getId());
			test.addEdge(vnode.get(0).getId(), vnode.get(12).getId());
			test.addEdge(vnode.get(0).getId(), vnode.get(13).getId());
			test.addEdge(vnode.get(0).getId(), vnode.get(9).getId());
			test.addEdge(vnode.get(6).getId(), vnode.get(2).getId());
			test.addEdge(vnode.get(7).getId(), vnode.get(1).getId());
			test.addEdge(vnode.get(14).getId(), vnode.get(1).getId());
			test.addEdge(vnode.get(14).getId(), vnode.get(2).getId());
			test.addEdge(vnode.get(2).getId(), vnode.get(10).getId());
			test.addEdge(vnode.get(10).getId(), vnode.get(3).getId());
			test.addEdge(vnode.get(3).getId(), vnode.get(8).getId());
			test.addEdge(vnode.get(9).getId(), vnode.get(2).getId());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		calculate();
	}
		
	public static void main(String[] args) {
		initialize();
		System.out.println("Hey user, welcome to the driver of the first type of search. We've done for you a brief list of nodes in order to help you with testing."
        		+ "\nThese are the nodes:\n ");
        for (Node n : vnode) {
            System.out.println(n);
        }
        System.out.println("\nINTRODUCE NEXT COMMAND.");
        command = sc.next();
        while (!command.equals("exit")) {
            if (command.equals("show")) show();
            else if (command.equals("search")) search();
            else if (command.equals("help")) help();
            else System.out.println("ERROR: Wrong command, type 'help' for more information.");
            System.out.println("");
            for (int i = 0; i < 120; ++i) System.out.print("#");
            System.out.println("\n\nINTRODUCE NEXT COMMAND.");
            command = sc.next();
        }
	}
}
