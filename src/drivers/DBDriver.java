package drivers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

import domain.DataBaseController;
import domain.graph.*;
import domain.User;
import exceptions.ExistingUser;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;

public class DBDriver {
	
	private static String command;
	private static Scanner sc;
	private static Graph test;
    private static DataBaseController db;
	private static long authors, terms, papers, conferences, edges, total;

	private static void load() {
		DataBaseController.load();
		System.out.println("The database has been loaded successfully.");
	}
	
	private static void download() {
		if (test.size() == 0) {
			System.out.println("The graph is empty, you cannot download it.");
		}
		else {
			DataBaseController.download();
			System.out.println("The graph has been downloaded successfully.");
		}
		
	}
	
	private static long countLine(String path) {
		BufferedReader b = null;
		long lines = 0;
		try {
			b = new BufferedReader(new FileReader(new File(path)));
			lines = b.lines().count();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return lines;
    }

	private static void countNodes() {
		long result;
		for (Node n : test.allNodes()) {
			if (n.asTerm() != null) ++terms;
			else if (n.asPaper() != null) ++papers;
			else if (n.asConference() != null) ++conferences;
			else if (n.asAuthor() != null) ++authors;
		}
	}

	private static void check() {
		System.out.println("What operation do you want to check (load,download)?\n");
		command = sc.next();
		while (!command.equals("load") && !command.equals("download")) {
			System.out.println("ERROR: Incorrect order, introduce 'load' or 'download'.");
			command = sc.next();
		}
		authors = papers = terms = conferences = 0;
		countNodes();
		if (command.equals("load")) {

			total = authors + terms + papers + conferences;
			System.out.println("TYPE\t\tEXPECTED\tACTUAL");
			System.out.println("---------------------------------------");
			System.out.println("TOTAL\t\t" + 37791 + "\t\t" + test.size());
			System.out.println("AUTHOR\t\t" + 14475 + "\t\t" + authors);
			System.out.println("TERM\t\t" + 8920 + "\t\t" + terms);
			System.out.println("CONFERENCE\t" + 20 + "\t\t\t" + conferences);
			System.out.println("PAPER\t\t" + 14376 + "\t\t" + papers);
			System.out.println("EDGES\t\t" + 170794 + "\t\t" + test.edgeSize() + '\n');

		}
		else {
			authors = countLine("o_author.txt");
			terms = countLine("o_term.txt");
			papers = countLine("o_paper.txt");
			conferences = countLine("o_conf.txt");
			edges = countLine("o_paper_author.txt") + countLine("o_paper_term.txt") + countLine("o_paper_conf.txt");
			total = authors + terms + papers + conferences;
			System.out.println("TYPE\t\tEXPECTED\tACTUAL");
			System.out.println("---------------------------------------");
			System.out.println("TOTAL\t\t" + test.size() + "\t\t" + total);
			System.out.println("AUTHOR\t\t" + authors + "\t\t" + countLine("o_author.txt"));
			System.out.println("TERM\t\t" + terms + "\t\t" + countLine("o_term.txt"));
			System.out.println("CONFERENCE\t" + conferences + "\t\t\t" +  countLine("o_conf.txt"));
			System.out.println("PAPER\t\t" + papers + "\t\t" + countLine("o_paper.txt"));
			System.out.println("EDGES\t\t" + test.edgeSize() + "\t\t" + (countLine("o_paper_author.txt") + countLine("o_paper_term.txt") + countLine("o_paper_conf.txt")) + '\n');
		}
	}
	
	private static void register() {
		System.out.println("Introduce the name of the new user.");
		String name = sc.next();
		System.out.println("Introduce the pass of the new user.");
		String password = sc.next();
		System.out.println("Is the new user an admin? Awnser yes or no.");
		String awnser = sc.next();
		while (!awnser.equals("yes") && !awnser.equals("no")) {
			System.out.println("Please say yes or no.");
			awnser = sc.next();
		}
		boolean admin =awnser.equals("yes");
		System.out.println("Does the new user have favourite terms? Awnser yes or no.");
		awnser = sc.next();
		while (!awnser.equals("yes") && !awnser.equals("no")) {
			System.out.println("Please say yes or no.");
			awnser = sc.next();
		}
		if (awnser.equals("yes")) {
			TreeSet<String> favourites = new TreeSet<>();
			System.out.println("Introduce the number of favourite terms.");
			int size = sc.nextInt();
			for (int i = 0; i < size; ++i) {
				favourites.add(sc.next());
			}
			try {
				DataBaseController.registerUser(new User(name,password));
			} catch (ExistingUser existingUser) {
				existingUser.printStackTrace();
			}
		}
		else {
			try {
				DataBaseController.registerUser(new User(name,password));
			} catch (ExistingUser existingUser) {
				existingUser.printStackTrace();
			}
		}
	}
	
	public static void destroy() {
		System.out.println("Introduce the name of the user that you want to destroy.");
		String name = sc.next();
		try {
			DataBaseController.deleteUser(new User(name,""));
		} catch (NonExistentUser nonExistentUser) {
			nonExistentUser.printStackTrace();
			System.out.println("YOU TRIED TO DESTROY ME.");
		}
	}
	
	public static void login() {
		System.out.println("Introduce username.");
		String username = sc.next();
		System.out.println("Introduce password.");
		String pass = sc.next();
		try {
			DataBaseController.logIn(new User(username,pass));
			System.out.println("Login succesfull");
		} catch (NonExistentUser nonExistentUser) {
			System.out.println(nonExistentUser.getMessage());
			System.out.println("Login failed");
		} catch (IncorrectPassword nonExistentUser) {
			System.out.println(nonExistentUser.getMessage());
			System.out.println("Login failed");
		}
	}
	
	private static void help () {
		  System.out.println("load : Loads the content of the database into the graph.");
          System.out.println("download : Downloads the content of the graph into the database.");
          System.out.println("check : Check either if the load or download has been done correctly.");
          System.out.println("register : Registrer a new user in the database.");
          System.out.println("destroy : Destyroy the specified user in the database.");
          System.out.println("login : Login in the application.");
          System.out.println("exit : Close the driver.");
	}
	
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        test = Graph.getInstance();
        
        
        System.out.println("We know that the database has 37791 entities, 14475 of which\nare authors, 14376 are articles, 20 are conferences and 8920 are terms.");  
        System.out.println("\nWe also know that the database has 170794 edges, 114624 of which\nare art-term edges, 14376 are art-conference edges and 41794 are art-author edges.");
        System.out.println("\nINTRODUCE NEXT COMMAND.");
        command = sc.next();
        
        while (!command.equals("exit")) {
            if (command.equals("load")) load();
            else if (command.equals("download")) download();
            else if (command.equals("check")) check();
            else if (command.equals("register")) register();
            else if (command.equals("destroy")) destroy();
            else if (command.equals("login")) login();
            else if (command.equals("help")) help();
            else System.out.println("ERROR: Wrong command, type 'help' for more information.");
            System.out.println("");
            for (int i = 0; i < 120; ++i) System.out.print("#");
            System.out.println("\n\nINTRODUCE NEXT COMMAND.");
            command = sc.next();
        }
    }
}
