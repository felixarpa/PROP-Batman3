package drivers;

import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

import domain.DataBaseController;
import domain.DomainController;
import domain.UserController;
import domain.graph.*;
import exceptions.*;

public class DriverGraph {
	
	private static String command;
	private static Scanner sc;
	private static Graph test;
    private static ArrayList<Node> vnode;
    private static ArrayList<Boolean> existing;
    private static String name;
    private static int label, id;
    
    private static void add() {
    	command = sc.next();
	    if (command.equals("Node")) {
	        System.out.println("What node do you want to add to the Graph? (0.."+(vnode.size()-1)+")");
	        int i = 0;
            for (Node n : vnode) {
                System.out.println(i + "- "+n);
                ++i;
            }
	        int choice = sc.nextInt();
	        while(choice < 0||choice > vnode.size()-1) {
	            System.out.println("ERROR: Number must be between 0 and "+(vnode.size()-1));
	            choice = sc.nextInt();
	        }
            boolean error = false;
            try {
                test.addNode(vnode.get(choice));
                System.out.println("Node added successfully.");
                existing.set(choice, true);
            }
            catch (ExistingNode existingNode) {System.out.println(existingNode.getMessage());}
	    }
	    else if (command.equals("Edge")) {
	        if (test.size() < 2) System.out.println("ERROR: There is not enough nodes for making an edge.");
	        else {
	            System.out.println("Select the nodes connected with the edge.");
	            int node1, node2;
	            node1 = sc.nextInt();
	            node2 = sc.nextInt();
	            while (!existing.get(node1) || !existing.get(node2)) {
	                System.out.println("ERROR: Invalid nodes. Remember that the nodes that are actually in the graph are: ");
	                for (int i  = 0; i < vnode.size(); ++i) {
	                    if (i != 0) System.out.print(',');
	                    if (existing.get(i)) System.out.print(i);
	                }
	                System.out.print("\nSelect the nodes connected with the edge.\n");
	                node1 = sc.nextInt();
	                node2 = sc.nextInt();
	            }
                try {
                    test.addEdge(vnode.get(node1), vnode.get(node2));
                    System.out.println("Edge added successfully.");
                }
                catch (Exception existingEdge) {
                    System.out.println(existingEdge.getMessage());
                }
	        }
	    }

	    else System.out.println("ERROR: Wrong attribute. Remember that attribute € [Node,Edge]");
	    	
    }
    
    private static void remove() {
    	command = sc.next();
        if (command.equals("Node")) {
            System.out.println("What node do you want to remove of the Graph? (0.."+(vnode.size()-1)+")");
            int i = 0;
            for (Node n : vnode) {
                System.out.println(i + "- "+n);
                ++i;
            }
            int choice = sc.nextInt();
            while(choice < 0||choice > vnode.size()-1) {
                System.out.println("ERROR: Number must be between 0 and " + (vnode.size()-1));
                choice = sc.nextInt();
            }
            try {
                test.deleteNode(vnode.get(choice));
                existing.set(choice,false);
                System.out.println("Node removed successfully.");
            } catch (NonExistentNode nonExistentNode) {
                nonExistentNode.getMessage();
            }
        }
        else if (command.equals("Edge")) {
            System.out.println("What edge do you want to remove of the Graph?");
            System.out.println("Introduce the numbers of the nodes (0.."+(vnode.size()-1)+")");
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            while (!existing.get(node1) || !existing.get(node2)) {
                System.out.println("ERROR: Invalid nodes. Remember that the nodes that are actually in the graph are: ");
                for (int i  = 0; i < vnode.size(); ++i) {
                    if (i != 0) System.out.print(',');
                    if (existing.get(i)) System.out.print(i);
                }
                System.out.print("\nSelect the nodes connected with the edge.\n");
                node1 = sc.nextInt();
                node2 = sc.nextInt();
            }
            try {
                test.deleteEdge(vnode.get(node1), vnode.get(node2));
                System.out.println("Edge removed successfully.");
            } catch (Exception nonExistentEdge) {
                nonExistentEdge.printStackTrace();
            }
        }
        else System.out.println("ERROR: Wrong attribute. Remember that attribute € [Node,Edge]");
    	
    }
    
    private static void search() {
    	  System.out.println("Introduce the name of the desired node.");
          String name = sc.next();
          Collection<Node> aux = test.getNode(name);
          System.out.println(name);
          System.out.println("----------");
          if (aux == null || aux.size() == 0) System.out.println("The search found 0 results.");
          else {
        	  for (Node n:aux) {
        		  System.out.println(n);
        	  }
          }
    }
    
    private static void show() {
    	 System.out.println("Do you want to show Node-related or Graph-related stuff? (say Node or Graph).");
         command = sc.next();
        switch (command) {
            case "Node":
                System.out.println("What node do you want to show? (number between 0 and " + (vnode.size() - 1) + " )");
                int choice = sc.nextInt();
                while (choice < 0 || choice > vnode.size() - 1) {
                    System.out.println("ERROR: Number must be between 0 and " + (vnode.size() - 1));
                    choice = sc.nextInt();
                }
                System.out.println("What attribute do you want to show? (name,id,label,relevance)");
                command = sc.next();
                switch (command) {
                    case "name":
                        System.out.println(vnode.get(choice).getName());
                        break;
                    case "id":
                        System.out.println(vnode.get(choice).getId());
                        break;
                    case "label":
                        System.out.println(vnode.get(choice).getLabel());
                        break;
                    case "relevance":
                        System.out.println(vnode.get(choice).getRelevance());
                        break;
                    default:
                        System.out.println("ERROR: Wrong attribute. Remember that attribute € [name,id,label,relevance]");
                        break;
                }
                break;
            case "Graph":
                System.out.println("What attribute do you want to show? (size,nodes,edges)");//,authors,conferences,papers,terms)");

                command = sc.next();
                switch (command) {
                    case "size":
                        System.out.println(test.size());
                        break;
                    case "nodes":
                        for (Node n : test.allNodesId()) {
                            System.out.println(n);
                        }
                        break;
                    case "edges":
                        for (Node n : test.allNodes()) {
                            System.out.println(n);
                            System.out.println("Adjacent to:");
                            for (Node m : n.getAdjacentNode()) {
                                System.out.print(m + " Weight: " + n.getWeight(m) + "\n");
                            }
                            System.out.print("\n");
                        }
                        break;
                    default:
                        System.out.println("ERROR: Wrong attribute. Remember that attribute € [size,nodes,edges]");
                        break;
                }
                break;
            default:
                System.out.println("ERROR: Wrong attribute. Remember that attribute € [Node, Graph]");
                break;
        }
    }
    
    private static void create() {
    	 String type = sc.next();
        switch (type) {
            case "Author":
                readParameters(type, sc);
                vnode.add(new Author(name, id, label));
                existing.add(false);
                break;
            case "Conference":
                readParameters(type, sc);
                vnode.add(new Conference(name, id, label));
                existing.add(false);
                break;
            case "Term":
                readParameters(type, sc);
                vnode.add(new Term(name, id));
                existing.add(false);
                break;
            case "Paper":
                readParameters(type, sc);
                vnode.add(new Paper(name, id, label));
                existing.add(false);
                break;
            default:
                System.out.println("ERROR: Wrong type. Remember that type € [Author,Conference,Term,Paper]");
                break;
        }
    }
    
    private static void set() {
    	 System.out.println("What node do you want to modify? (number between 0 and "+(vnode.size()-1)+" )");
         int choice = sc.nextInt();
         while(choice < 0||choice > vnode.size()-1) {
             System.out.println("ERROR: Number must be between 0 and " + (vnode.size()-1));
             choice = sc.nextInt();
         }
         System.out.println("Introduce the desired label.");
         vnode.get(choice).setLabel(sc.nextInt());
    }
    
    private static void calculate() {
    	 System.out.println("What node do you want to make calculate its relevance? (number between 0 and "+(vnode.size()-1)+" )");
         int choice = sc.nextInt();
         while(choice < 0||choice > vnode.size()-1) {
             System.out.println("ERROR: Number must be between 0 and " + (vnode.size()-1));
             choice = sc.nextInt();
         }
         vnode.get(choice).calculateRelevance();
    }
    
    private static void help() {
    	  System.out.println("create x : Creates a node of type x with x € [Author,Conference,Term,Paper].");
          System.out.println("show : Shows information about the Graph or of a specified node.");
          System.out.println("set : Modifies the label of a specified node.");
          System.out.println("calculate  : Calculates the relevance of a specified node.");
          System.out.println("add : Adds either a existing node or an edge to the graph with x € [Node,Edge].");
          System.out.println("remove x : Deletes either a node or an edge with x € [Node,Edge].");
          System.out.println("search : Looks for a node of the graph.");
          System.out.println("exit : Close the driver.");
    }
    
    private static void  readParameters(String type, Scanner sc) {
        System.out.println("Introduce the name of the "+type);
        name = sc.next();
        System.out.println("Introduce the id of the " + type + " (integer).");
        id = sc.nextInt();
        if (!type.equals("Term")) {
            System.out.println("Introduce the label of the " + type + " (integer).");
            label = sc.nextInt();
        }
        System.out.println("Node created succesfully.");
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        test = Graph.getInstance();
        vnode = new ArrayList<>(4);
        existing = new ArrayList<>(4);

        try {
            UserController.logIn("Mario","123");
        } catch (IncorrectPassword | NonExistentUser incorrectPassword) {
            throw new ProjectError(incorrectPassword.getMessage());
        }

        vnode.add(new Author("Mario",1,1));
        vnode.add(new Paper("Prop",2,1));
        vnode.add(new Conference("PropClass",3,1));
        vnode.add(new Term("Computer Science",4));
        
        for (int i = 0; i < 4; ++i) existing.add(false);
        System.out.println("Hey user, welcome to the driver of node and graph. We've done for you a brief list of nodes in order to help you with testing."
        		+ "\nThese are the nodes:\n ");
        for (Node n : vnode) {
            System.out.println(n);
        }
        System.out.println("\nINTRODUCE NEXT COMMAND.");
        command = sc.next();
        
        while (!command.equals("exit")) {
            switch (command) {
                case "add": add(); break;
                case "remove": remove(); break;
                case "search": search(); break;
                case "show": show(); break;
                case "create": create(); break;
                case "set": set(); break;
                case "calculate": calculate(); break;
                case "help": help(); break;
                default:
                    System.out.println("ERROR: Wrong command, type 'help' for more information.");
                    break;
            }
            System.out.println("");
            for (int i = 0; i < 120; ++i) System.out.print("#");
            System.out.println("\n\nINTRODUCE NEXT COMMAND.");
            command = sc.next();
        }
    }
}
