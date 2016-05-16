package drivers;

import java.util.ArrayList;
import java.util.Scanner;

import domain.MugendoAlgorithm;
import domain.PageRank;
import domain.UserController;
import domain.graph.*;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;

public class DriverMugendo {
	static String command;
	static Scanner sc;
	static Graph test;
	static PageRank pr;
	static MugendoAlgorithm edge;
    static ArrayList<Node> vnode;
    static ArrayList<Boolean> existing;


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
	
	private static void calculate2() {
		System.out.println("Select the nodes of wich you want to calculate the edge relevance (numbers between 0 and "+(vnode.size()-1)+" ).");
        int i = 0;
        for (Node n : vnode) {
            System.out.println(i + "- "+n);
            ++i;
        }
        int choice1 = sc.nextInt();
        int choice2 = sc.nextInt();
        while(choice1 < 0||choice1 > vnode.size()-1 || choice2 < 0 || choice2 > vnode.size() -1) {
            System.out.println("ERROR: Numbers must be between 0 and " + (vnode.size()-1));
            System.out.println("Select the nodes of wich you want to calculate the edge relevance (numbers between 0 and "+(vnode.size()-1)+" ).");
            choice1 = sc.nextInt();
            choice2 = sc.nextInt();
        }
        double result  = edge.calculateRelevance(vnode.get(choice1),vnode.get(choice2));
        System.out.println("The resulted relevance is " + result);
        
	}
	
	private static void calculate() {
		pr.execute();
		System.out.println("The PageRank has been executed successfully.");
	}
    
    private static void help() {
        System.out.println("show: Shows the default graph.");
        System.out.println("calculate: Calculates the relevance of the edge between to specified nodes.");
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
			test.addEdge(vnode.get(2).getId(), vnode.get(10).getId());;
			test.addEdge(vnode.get(10).getId(), vnode.get(3).getId());
			test.addEdge(vnode.get(3).getId(), vnode.get(8).getId());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		calculate();
	}
    
    public static void main(String[] args) {
		initialize();
		System.out.println("Hey user, welcome to the driver of the Mugendo Algorithm." + '\n' + "This algorithm calculates the relevance betwen two edges." +
				'\n' + "We've done for you a brief list of nodes in order to help you with testing." +
				"\nThese are the nodes:\n ");
        for (Node n : vnode) {
            System.out.println(n);
        }
        System.out.println("\nINTRODUCE NEXT COMMAND.");
        command = sc.next();
        while (!command.equals("exit")) {
            if (command.equals("show")) show();
            else if (command.equals("calculate")) calculate2();
            else if (command.equals("help")) help();
            else System.out.println("ERROR: Wrong command, type 'help' for more information.");
            System.out.println("");
            for (int i = 0; i < 120; ++i) System.out.print("#");
            System.out.println("\n\nINTRODUCE NEXT COMMAND.");
            command = sc.next();
        }
	}
}
