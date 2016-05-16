package drivers;
import domain.DomainController;
import domain.PageRank;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import domain.UserController;
import domain.graph.*;
import comparators.RelevanceComparator;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;
import exceptions.ProjectError;

public class DriverPageRank {

	public static void main(String[] args) {
		try {
			UserController.logIn("hola","123");
		} catch (IncorrectPassword | NonExistentUser incorrectPassword) {
			throw new ProjectError(incorrectPassword.getMessage());
		}
		Graph test = Graph.getInstance();
		ArrayList<Node> vnode = new ArrayList<>();
		vnode.add(new Paper("p1",1)); //0
		vnode.add(new Paper("p2",2)); //1
		vnode.add(new Paper("p3",3)); //2
		vnode.add(new Paper("p4",4)); //3
		vnode.add(new Author("a1",5)); //4
		vnode.add(new Author("a2",6)); //5
		vnode.add(new Author("a3",7)); //6
		vnode.add(new Author("a4",8));
		vnode.add(new Author("a5",9)); //8
		vnode.add(new Conference("c1",10));
		vnode.add(new Conference("c2",11)); //10
		vnode.add(new Term("t1",12));
		vnode.add(new Term("t2",13)); //12
		vnode.add( new Term("t3",14));
		vnode.add( new Term("t4",15)); //14
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
			System.out.println("This driver just applies the Page Rank algorithm on this Graph:\n");
			/*for (Node n : test.allNodes()) {
	            System.out.println(n);
	            System.out.println("Adjacent to:");
	            for (Node m : n.getAdjacentNode()) {
	                System.out.print(m + " Weight: " + n.getWeight(m) + "\n");
	            }
	            System.out.print("\n");
	        }*/
			System.out.println("Introduce 'go' to GO!");
			String order;
			Scanner sc = new Scanner(System.in);
			order = sc.next();
			while (!order.equals("go")) {
				System.out.println("Introduce 'go' to GO!");
				order = sc.next();
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		PageRank.execute();
		System.out.println("The nodes are:");
		TreeSet<Node> result = new TreeSet<>(new RelevanceComparator());
		result.addAll(test.allNodes());
		for (Node n: result) System.out.println(n);
		System.out.println("Thanks for trying goodbye! :)");

	}
		
}
