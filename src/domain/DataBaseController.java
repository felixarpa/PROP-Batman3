package domain;

import domain.graph.*;
import exceptions.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public abstract class DataBaseController {

    private static void loadNodes(){
        System.out.println("Begin loadNodes");

        String fileName = null;

        Graph graph = Graph.getInstance();
        for (int i = 0; i < 4; ++i) {
            switch (i) {
                case 0:
                    fileName = "author.txt";
                    break;
                case 1:
                    fileName = "paper.txt";
                    break;
                case 2:
                    fileName = "conf.txt";
                    break;
                case 3:
                    fileName = "term.txt";
                    break;
            }
            try {
                File file = new File("data/" + fileName);
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bf.readLine()) != null) {
                    String num = null;
                    for (int j = 0; j < line.length(); ++j) {
                        if (line.charAt(j) == '\t') {
                            num = line.substring(0, j);
                            line = line.substring(j + 1);
                            j = line.length();
                        }
                    }
                    int id = Integer.parseInt(num);
                    Node node = null;
                    switch (i) {
                        case 0:
                            node = new Author(line, id);
                            break;
                        case 1:
                            node = new Paper(line, id);
                            break;
                        case 2:
                            node = new Conference(line, id);
                            break;
                        case 3:
                            node = new Term(line, id);
                            break;
                    }
                    graph.addNode(node); 
                }
                bf.close();
            } catch (IOException | ExistingNode e) {
                throw new ProjectError(e.getMessage());
            }
        }
        System.out.println("End loadNodes");
    }

    private static void loadEdges() {
        System.out.println("Begin loadEdges");

        Graph graph = Graph.getInstance();
        String fileName = null;

        for (int i = 0; i < 3; ++i) {
            switch (i) {
                case 0:
                    fileName = "paper_author.txt";
                    break;
                case 1:
                    fileName = "paper_conf.txt";
                    break;
                case 2:
                    fileName = "paper_term.txt";
                    break;
            }
            try {
                File file = new File("data/" + fileName);
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bf.readLine()) != null) {
                    String id1 = null;
                    String id2 = null;
                    for (int j = 0; j < line.length(); ++j) {
                        if (line.charAt(j) == '\t') {
                            id1 = line.substring(0, j);
                            id2 = line.substring(j + 1);
                            j = line.length();
                        }
                    }
                    int idd1 = Integer.parseInt(id1);
                    int idd2 = Integer.parseInt(id2);
                    Id identifier1 = Paper.makeId(idd1);
                    Id identifier2 = null;
                    switch (i) {
                        case 0:
                            identifier2 = Author.makeId(idd2);
                            break;
                        case 1:
                            identifier2 = Conference.makeId(idd2);
                            break;
                        case 2:
                            identifier2 = Term.makeId(idd2);
                            break;
                    }
                    graph.addEdge(identifier1, identifier2);
                }
            } catch (IOException | ExistingEdge | NonExistentEdgeNodes e) {
                throw new ProjectError(e.getMessage());
            }
        }
        System.out.println("End loadEdges");
    }

    private static void loadLabels() {
        System.out.println("Begin loadLabels");

        Graph graph = Graph.getInstance();
        String fileName = null;

        for (int i = 0; i < 3; ++i) {
            switch(i) {
                case 0:
                    fileName = "author_label.txt";
                    break;
                case 1:
                    fileName = "paper_label.txt";
                    break;
                case 2:
                    fileName = "conf_label.txt";
                    break;
            }
            try {
                File file = new File("data/" + fileName);
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bf.readLine()) != null) {
                    Scanner sc = new Scanner(line);
                    int id = sc.nextInt();
                    int label = sc.nextInt();
                    Id idd = null;
                    switch (i) {
                        case 0:
                            idd = Author.makeId(id);
                            break;
                        case 1:
                            idd = Paper.makeId(id);
                            break;
                        case 2:
                            idd = Conference.makeId(id);
                            break;
                    }
                    Node node = graph.getNode(idd);
                    node.setLabel(label);
                    sc.close();
                }
            } catch (IOException e) {
                throw new ProjectError(e.getMessage());
            }
        }
        System.out.println("End loadLabels");
    }

    public static void load() {
        loadNodes();
        loadEdges();
        loadLabels();
    }

    private static void downloadNodes() {

        Graph graph = Graph.getInstance();

        System.out.println("Begin downloadNodes");
        try {

            BufferedWriter author = new BufferedWriter(new FileWriter(new File("o_author.txt")));
            BufferedWriter paper = new BufferedWriter(new FileWriter(new File("o_paper.txt")));
            BufferedWriter term = new BufferedWriter(new FileWriter(new File("o_term.txt")));
            BufferedWriter conference = new BufferedWriter(new FileWriter(new File("o_conf.txt")));
            BufferedWriter paperAuthor = new BufferedWriter(new FileWriter(new File("o_paper_author.txt")));
            BufferedWriter paperTerm = new BufferedWriter(new FileWriter(new File("o_paper_term.txt")));
            BufferedWriter paperConference = new BufferedWriter(new FileWriter(new File("o_paper_conf.txt")));
            BufferedWriter authorLabel = new BufferedWriter(new FileWriter(new File("o_author_label.txt")));
            BufferedWriter paperLabel = new BufferedWriter(new FileWriter(new File("o_paper_label.txt")));
            BufferedWriter conferenceLabel = new BufferedWriter(new FileWriter(new File("o_conference_label.txt")));

            for (Node node : graph.allNodes()) {
                String string = node.getId() + "\t" + node.getName()+"\n";
                String label = node.getId() + "\t" + node.getLabel() + "\t" + node.getName() + "\n";
                if (node.asPaper() != null) {
                    for (Node adjacent : node.getAdjacentNode()) {
                        String data = node.getId() + "\t" + adjacent.getId() + "\n";
                        if (adjacent.asAuthor() != null) {
                            paperAuthor.write(data);
                        } else if (adjacent.asTerm() != null) {
                            paperTerm.write(data);
                        } else if (adjacent.asConference() != null) {
                            paperConference.write(data);
                        }
                        else throw new ProjectError("FATAL ERROR: Node "+ node +" it's not a valid type");
                    }
                    paper.write(string);
                    if (node.getLabel() != -1) {
                        paperLabel.write(label);
                    }
                }
                else if (node.asAuthor() != null) {
                    author.write(string);
                    if (node.getLabel() != -1) authorLabel.write(label);
                }
                else if (node.asConference() != null) {
                    conference.write(string);
                    if (node.getLabel() != -1) conferenceLabel.write(label);
                }
                else {
                    term.write(string);
                }
            }
            author.close();
            paper.close();
            term.close();
            conference.close();
            paperAuthor.close();
            paperConference.close();
            paperTerm.close();
            authorLabel.close();
            paperLabel.close();
            conferenceLabel.close();
        } catch (IOException e) {
            throw new ProjectError(e.getMessage());
        }
        System.out.println("End downloadNodes");
    }

    public static void download() {
        downloadNodes();
    }
    
    public static void registerUser(User user) throws ExistingUser {
        LinkedList<User> users = new LinkedList<>();
    	String fileName;
    	try {
    		fileName = "users.txt";
    		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
    		String actual;
    	
    		while ((actual = reader.readLine()) != null )  {
    			User dbUser = new User(actual);
                if (dbUser.equals(user)) throw new ExistingUser(user);
                users.add(dbUser);
    		}
    		users.add(user);
    		reader.close();
    		BufferedWriter register = new BufferedWriter(new FileWriter(new File(fileName)));
    		
    		for (User actualUser : users) {
    			register.write(actualUser + "\n");
    		}
    		register.close();
    		
    	} catch (IOException e) {
            throw new ProjectError(e.getMessage());
        }
    }
    
    public static void deleteUser(User user) throws NonExistentUser {

        if (DomainController.currentUser.equals(user)) {
            throw new ProjectError("ERROR: You cannot delete the actual user.");
        }
        LinkedList<User> users = new LinkedList<>();
    	String fileName;
    	try {
    		fileName = "users.txt";
    		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
    		String actual;
    	    boolean found = false;
    		while ((actual = reader.readLine()) != null) {
                User dbUser = new User(actual);
    			if (!dbUser.equals(user)) users.add(dbUser);
                else if (!found) found = true;
                else throw new ProjectError("FATAL ERROR: CORRUPTED DATA BASE, FOUND TWO EQUAL USERS");
    		}
            if (!found) throw new NonExistentUser(user);
    		reader.close();
    		BufferedWriter register = new BufferedWriter(new FileWriter(new File(fileName)));
    		
    		for (User actualUser : users) {
    			register.write(actualUser + "\n");
    		}
    		register.close();
    		
    	} catch (IOException e) {
            throw new ProjectError(e.getMessage());
        }
    }
    
    public static void logIn(User user) throws NonExistentUser, IncorrectPassword {
    	String fileName = "users.txt";
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
    		String actual;
    	
    		while ((actual = reader.readLine()) != null ) {
                User dbUser = new User(actual);
                if (user.equals(dbUser)) {
                    if (!dbUser.getPassword().equals(user.getPassword())) {
                        reader.close();
                        throw new IncorrectPassword(dbUser, user.getPassword());
                    }
                    DomainController.currentUser = dbUser;
                    reader.close();
                    return;
                }
    		}
            reader.close();
            throw new NonExistentUser(user);
    	} catch (IOException e) {
            throw new ProjectError(e.getMessage());
        }
    }
    
}
