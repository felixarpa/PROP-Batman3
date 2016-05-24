package drivers;

import domain.*;
import exceptions.*;
import util.ProjectConstants;

import java.util.*;


public class DriverDomainController {

	private static String command;
	private static Scanner sc;
	private static DomainController god;
	private static AdminController adminController = new AdminController();
	private static ArrayList<String> reordedResult;

	private static void search() {
		pl();
		pi("search");
		pl();

		int choice;
		String ans;
		do {
			p("search (1, 2, 3): ");
			ans = sc.next();
			if (ans.equals("help")) helpSearch();
			else {
				choice = Integer.parseInt(ans);
				switch (choice) {
					case 1:
						firstSearch();
						break;
					case 2:
						secondSearch();
						break;
					case 3:
						thirdSearch();
						break;
				}
			}
		} while (ans.equals("exit"));
	}

	private static void helpSearch() {
		pl("1: Give a node and search all its relations");
		pl("2: Give a type and search all nodes of this type");
		pl("3: Give two types search all their relation between them");
		pl("exit: Exit from search mode");
		pl();
	}

	private static void firstSearch() {
		pi("search/1");
		pl();
		p("name: ");
		command = sc.next();
		ArrayList<String> possibleNodes = god.searchingANode(command);
		pl("found:");
		if (possibleNodes != null) {
			int i = 0;
			for (String s : possibleNodes) {
				pl((i+1) + ".- " + s);
				++i;
			}

			int selection;

			do {
				pl("Node to apply your search: ");
				selection = sc.nextInt() - 1;

			} while (selection >= possibleNodes.size() || selection < 0);

			String selectedNode = possibleNodes.get(selection);
			do {
				p("relation/node relevance: ");
				command = sc.next().toLowerCase();
			} while (!command.equals("node") && !command.equals("relation"));

			ArrayList<ArrayList<String>> searchResult = new ArrayList<>();
			if (command.equals("node")) {
				searchResult = god.firstSearch(selectedNode, 0);
				pl();
				pl();
				pl("AUTHORS:");
				pl();
				int n = searchResult.get(0).size();
				for (int j = 0; j < 10 && j < n; ++j) {
					pl(searchResult.get(0).get(j));
				}
				pl();
				pl();
				pl("CONFERENCES:");
				pl();
				n = searchResult.get(1).size();
				for (int j = 0; j < 10 && j < n; ++j) {
					pl(searchResult.get(1).get(j));
				}
				pl();
				pl();
				pl("PAPERS:");
				pl();
				n = searchResult.get(2).size();
				for (int j = 0; j < 10 && j < n; ++j) {
					pl(searchResult.get(2).get(j));
				}
				pl();
				pl();
				pl("TERMS:");
				pl();
				n = searchResult.get(3).size();
				for (int j = 0; j < 10 && j < n; ++j) {
					pl(searchResult.get(3).get(j));
				}
			}
			else {
				int j;
				searchResult = god.firstSearch(selectedNode,1);
				pl();
				pl();
				pl("AUTHORS");
				pl();
				for (i = 0; i < searchResult.get(0).size(); ++i) {
					int auxnum = Integer.parseInt(searchResult.get(0).get(i));
					++i;
					pl("Relevance: " + searchResult.get(0).get(i));
					++i;
					for (j = 0; j < auxnum; ++j) {
						pl(searchResult.get(0).get(i+j));
					}
					i += auxnum-1;
					pl();
				}
				pl();
				pl();
				pl("CONFERENCES");
				pl();
				for (i = 0; i < searchResult.get(1).size(); ++i) {
					int auxnum = Integer.parseInt(searchResult.get(1).get(i));
					++i;
					pl("Relevance: " + searchResult.get(1).get(i));
					++i;
					for (j = 0; j < auxnum; ++j) {
						pl(searchResult.get(1).get(i+j));
					}
					i += auxnum-1;
					pl();
				}
				pl();
				pl();
				pl("PAPERS");
				pl();
				for (i = 0; i < searchResult.get(2).size(); ++i) {
					int auxnum = Integer.parseInt(searchResult.get(2).get(i));
					++i;
					pl("Relevance: " + searchResult.get(2).get(i));
					++i;
					for (j = 0; j < auxnum; ++j) {
						pl(searchResult.get(2).get(i+j));
					}
					i += auxnum-1;
					pl();
				}
				pl();
				pl();
				pl("TERMS");
				pl();
				for (i = 0; i < searchResult.get(3).size(); ++i) {
					int auxnum = Integer.parseInt(searchResult.get(3).get(i));
					++i;
					pl("Relevance: " + searchResult.get(3).get(i));
					++i;
					for (j = 0; j < auxnum; ++j) {
						pl(searchResult.get(3).get(i+j));
					}
					i += auxnum-1;
					pl();
				}
			}
			reordedResult = new ArrayList<>();
		}
		else {
			pl("no results found");
			pl();
		}
	}

	private static void secondSearch() {
		pi("search/2");
		pl();
		do {
			p("type: ");
			command = sc.next().toLowerCase();
		} while (!command.equals("author") && !command.equals("conference") && !command.equals("paper") && !command.equals("term"));

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

		ArrayList<String> result = god.secondSearch(type);
		int n = result.size();
		for (int i = 0; i < n && i < 42; ++i) {
			pl((i+1) + ".- " + result.get(i));
		}

		do {
			p("search relative nodes? (yes/no): ");
			command = sc.next().toLowerCase();
		} while (!command.equals("yes") && !command.equals("no"));

		if (command.equals("yes")) {
			int choice;
			do {
				p("select a node (1.." + result.size() + "): ");
				choice = sc.nextInt() - 1;
			} while (choice < 0 && choice > result.size());

			do {
				p("more, less or equal relevant: ");
				command = sc.next().toLowerCase();
			} while (!command.equals("more") && !command.equals("less") && !command.equals("equal"));

			if (command.equals("more")) {
				result = god.searchSimilarRelevance(result.get(choice), 1);
			}
			else if (command.equals("less")) {
				result = god.searchSimilarRelevance(result.get(choice), -1);
			}
			else {
				result = god.searchSimilarRelevance(result.get(choice), 0);
			}
			n = result.size();
			for (int i = 0; i < n && i < 42; ++i) {
				pl(result.get(i));
			}
		}
	}

	private static void thirdSearch() {
		pi("search/3");
		pl();
		do {
			p("type 1: ");
			command = sc.next().toLowerCase();
		} while (!command.equals("author") && !command.equals("conference") && !command.equals("paper") && !command.equals("term"));

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

		do {
			p("type 2: ");
			command = sc.next().toLowerCase();
		} while (!command.equals("author") && !command.equals("conference") && !command.equals("paper") && !command.equals("term"));

		int type2 = -1;
		if (command.equals("author")) {
			type2 = ProjectConstants.AUTHOR_TYPE;
		}
		else if(command.equals("conference")) {
			type2 = ProjectConstants.CONFERENCE_TYPE;
		}
		else if (command.equals("paper")) {
			type2 = ProjectConstants.PAPER_TYPE;
		}
		else if (command.equals("term")) {
			type2 = ProjectConstants.TERM_TYPE;
		}

		ArrayList<String> result = god.thirdSearch(type,type2);
		ArrayList<ArrayList<String> > toSelect = new ArrayList<>();
		int i = 0;
		int k = 1;
		int contador = 0;
		while (i < result.size() && contador < 10)  {
			int aux = Integer.parseInt(result.get(i))*2;
			++i;
			pl('\n' + "RELEVANCE: " + result.get(i));
			++contador;
			++i;
			//MIRAR
			for (int j = 0; j < aux && j < 20; j += 2) {
				pl("edge " + k + ':');
				pl(result.get(i+j) + '\n' + result.get(i + j + 1) + '\n');
				ArrayList<String> auxar = new ArrayList<>();
				auxar.add(result.get(i+j));
				auxar.add(result.get(i+j+1));
				toSelect.add(auxar);
				++k;
			}
			i += aux;
		}
		if (result.size() != 0) {
			do {
				p("search relative edges? (yes/no): ");
				command = sc.next().toLowerCase();
			} while (!command.equals("yes") && !command.equals("no"));
	
			if (command.equals("yes")) {
				int choice;
				do {
					p("select an edge (1.." + toSelect.size() + "): ");
					choice = sc.nextInt()-1;
				} while (choice < 0 && choice > toSelect.size()-1);
	
				do {
					p("more, less or equal relevant: ");
					command = sc.next().toLowerCase();
				} while (!command.equals("more") && !command.equals("less") && !command.equals("equal"));
	
	
				ArrayList<String> similar;
				if (command.equals("more")) {
					similar = god.searchSimilarRelationRelevance(toSelect.get(choice).get(0),toSelect.get(choice).get(1),1);
				}
				else if (command.equals("less")) {
					similar = god.searchSimilarRelationRelevance(toSelect.get(choice).get(0),toSelect.get(choice).get(1),-1);
				}
				else {
					similar =  god.searchSimilarRelationRelevance(toSelect.get(choice).get(0),toSelect.get(choice).get(1),0);
				}
				//PARA MIRAR
				i = 0;
				contador = 0;
				while (i < similar.size() && contador < 10)  {
					int aux = Integer.parseInt(similar.get(i))*2;
					++i;
					pl('\n' + "RELEVANCE: " + similar.get(i));
					++contador;
					++i;
					//MIRAR
					for (int j = 0; j < aux && j < 20; j += 2) {
						pl(similar.get(i+j) + '\n' + similar.get(i + j + 1) + '\n');
					}
					i += aux;
				}
			}
		}
		else {
			pl("No results found.");
		}



	}

	private static void reorder(){
		if (reordedResult == null) {
			pl("You have to make a search to reorder the result.");
		}
		else {
			int typeOfEntity;
			int typeOfOrdering;
			pl("What type of entity of the result do you want to order? (author,conference,term,paper)");
			String type = sc.next();
			while (!type.equals("author") && !type.equals("conference") && !type.equals("term") && !type.equals("paper")) {
				pl("ERROR: Wrong type.");
				pl("What type of entity of the result do you want to order? (author,conference,term,paper)");
				type = sc.next();
			}
			if (type.equals("author")) typeOfEntity = 0;
			else if (type.equals("conference")) typeOfEntity = 1;
			else if (type.equals("paper")) typeOfEntity = 2;
			else typeOfEntity = 3;
			pl("According to wich parameter do you want to order the result? (name,id,relevance)");
			type = sc.next();
			while (!type.equals("name") && !type.equals("id") && !type.equals("relevance")) {
				pl("ERROR: Wrong type.");
				pl("According to wich parameter do you want to order the result? (name,id,relevance)");
				type = sc.next();
			}
			if (type.equals("name")) typeOfOrdering = 0;
			else if (type.equals("id")) typeOfOrdering = 1;
			else typeOfOrdering = 2;
			pl("Do you want to show the result ordered ascending o descending?");
			command = sc.next();
			while (!command.equals("ascending") && !command.equals("descending")) {
				pl("ERROR: Please write ascending or descending.");
				command = sc.next();
			}
			//reordedResult = god.reorderSearch1(typeOfEntity,typeOfOrdering,command.equals("ascending"));
			for (String s : reordedResult) {
				pl(s);
			}
		}


	}

	private static void help() {
		pl("search : Looks for a node of the graph.");
		pl("edit: Edit graph (only admin)");
		pl("delete user: Delete a user (only admin)");
		pl("exit : Close the driver.");
		pl();
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		// Iniciar sesion
		while (!login()) {
			pl("Wrong username/password.");
			pl("Do you want to register? (yes/no)");
			command = sc.next();
			if (command.equals("yes")) {
				register();
			}
		}

		sc.nextLine();
		god = new DomainController();

		do {
			pi("");
			command = sc.nextLine().toLowerCase();

			if (command.equals("search")) search();
			else if (command.equals("reorder")) reorder();
			else if (command.equals("help")) help();
			else if (command.equals("delete user")) removeUser();
			else if (command.equals("edit")) edit();
			else if (command.equals("predictor")) {
				String search = sc.next();
				for (String s : god.searchPredictor(search)) {
					System.out.println(s);
				}

			}
			else {
				System.out.println("CXommand error");
			}
		} while (!command.equals("exit"));

		god.close();

		pl("Goodbye");

	}

	private static void removeUser() {
		if (!DomainController.getCurrentUser().getAdmin()) {
			pl("Only admin users can edit");
			return;
		}
		pl("Introduce the name of the user that you want to remove.");
		String name = sc.next();
		try {
			DataBaseController.deleteUser(new User(name,""));
		} catch (NonExistentUser nonExistentUser) {
			nonExistentUser.printStackTrace();
		}
	}

	private static void edit() {
		if (!DomainController.getCurrentUser().getAdmin()) {
			pl("only admin users can edit");
			return;
		}
		// hello
		pi("edit");
		pl();
		String asdf;
		do {
			p("add or remove: ");
			asdf = sc.nextLine().toLowerCase();
		} while (!asdf.equals("add") && !asdf.equals("remove"));
		// it's me
		String qwer;
		if (asdf.equals("add")) {
			do {
				p(asdf + " node or edge: ");
				qwer = sc.nextLine().toLowerCase();
			} while (!qwer.equals("node") && !qwer.equals("edge"));
			if (qwer.equals("edge")) {
				String type1, type2;
				int id1, id2;
				do {
					p("type of first node (author/conference/paper/term): ");
					type1 = sc.nextLine().toLowerCase();
				} while (!type1.equals("author") && !type1.equals("conference") && !type1.equals("paper") && !type1.equals("term"));
				p("first id: ");
				id1 = sc.nextInt();
				pl();
				do {
					p("type of second node (author/conference/paper/term): ");
					type2 = sc.nextLine().toLowerCase();
				} while (!type2.equals("author") && !type2.equals("conference") && !type2.equals("paper") && !type2.equals("term"));
				p("second id: ");
				id2 = sc.nextInt();
				pl();
				try {
					adminController.addNewEdge(id1, type1, id2, type2);
				} catch (ExistingEdge | NonExistentEdgeNodes existingEdge) {
					existingEdge.printStackTrace();
				}


			}
			else {
				do {
					p("type of desired node (author/conference/paper/term) :");
					qwer = sc.nextLine().toLowerCase();
				} while (!qwer.equals("author") && !qwer.equals("conference") && !qwer.equals("paper") && !qwer.equals("term"));
				String name;
				int label;
				p("name of " + qwer + ": ");
				name = sc.nextLine();
				label = -1;
				if (!qwer.equals("term")) {
					p("label of " + qwer + ": ");
					label = sc.nextInt();
				}
				if (qwer.equals("author")) {
					try {
						adminController.addNewNode(name, label, ProjectConstants.AUTHOR_TYPE);
					} catch (ExistingNode existingNode) {
						existingNode.printStackTrace();
					}
				}
				else if (qwer.equals("conference")) {
					try {
						adminController.addNewNode(name, label, ProjectConstants.CONFERENCE_TYPE);
					} catch (ExistingNode existingNode) {
						existingNode.printStackTrace();
					}
				}
				else if (qwer.equals("paper")) {
					try {
						adminController.addNewNode(name, label, ProjectConstants.PAPER_TYPE);
					} catch (ExistingNode existingNode) {
						existingNode.printStackTrace();
					}
				}
				else {
					try {
						adminController.addNewNode(name, label, ProjectConstants.TERM_TYPE);
					} catch (ExistingNode existingNode) {
						existingNode.printStackTrace();
					}
				}
			}
					
		}
		else {
			do {
				p(asdf + " node or edge? ");
				qwer = sc.nextLine().toLowerCase();
			} while (!qwer.equals("node") && !qwer.equals("edge"));
			if (qwer.equals("edge")) {
				String type1, type2;
				int id1, id2;
				do {
					p("type of first node (author/conference/paper/term): ");
					type1 = sc.nextLine().toLowerCase();
				} while (!type1.equals("author") && !type1.equals("conference") && !type1.equals("paper") && !type1.equals("term"));
				p("first id: ");
				id1 = sc.nextInt();
				pl();
				do {
					p("type of second node (author/conference/paper/term): ");
					type2 = sc.nextLine().toLowerCase();
				} while (!type2.equals("author") && !type2.equals("conference") && !type2.equals("paper") && !type2.equals("term"));
				p("second id: ");
				id2 = sc.nextInt();
				pl();
				try {
					adminController.deleteEdge(id1, type1, id2, type2);
				} catch (NonExistentEdgeNodes | NonExistentEdge nonExistentEdgeNodes) {
					nonExistentEdgeNodes.printStackTrace();
				}

			}
			else {
				do {
					p("type of desired node (author/conference/paper/term): ");
					qwer = sc.nextLine().toLowerCase();
				} while (!qwer.equals("author") && !qwer.equals("conference") && !qwer.equals("paper") && !qwer.equals("term"));
				pl("id: ");
				int id;
				id = sc.nextInt();
				try {
					adminController.deleteNode(id, qwer);
				} catch (NonExistentNode nonExistentNode) {
					nonExistentNode.printStackTrace();
				}
			}
		}
        god.recalculate();

	}

	private static boolean login() {
		pl("null:login~$ ");
		p("username: ");
		String username = sc.next();
		p("password: ");
		String password = sc.next();
		User aux = new User(username, password);
		try {
			DataBaseController.logIn(aux);
			return true;
		}
		catch (NonExistentUser | IncorrectPassword nonExistentUser) {
			return false;
		}
	}

	private static void register() {

		pl();
		p("null:register~$ ");
		pl();
		p("username: ");
		String name = sc.next();
		p("password: ");
		String password = sc.next();

		String answer;
		do {
			p("admin (yes/no): ");
			answer = sc.next().toLowerCase();
		} while (!answer.equals("yes") && !answer.equals("no"));
		boolean admin = answer.equals("yes");

		do {
			p("favorite terms (yes/no): ");
			answer = sc.next().toLowerCase();
		} while (!answer.equals("yes") && !answer.equals("no"));
		User user;
		if (answer.equals("yes")) {
			TreeSet<String> favourites = new TreeSet<>();
			p("number of favorite terms:       ");
			int size = sc.nextInt();
			pl("list of favorite terms:");
			for (int i = 0; i < size; ++i) {
				p(" ");
				favourites.add(sc.next());
			}
			user = new User(name, password);
		}
		else {
			user = new User(name, password);
		}
		try {
			DataBaseController.registerUser(user);
		} catch (ExistingUser existingUser) {
			existingUser.printStackTrace();
			pl(existingUser.getMessage());
		}

	}

	private static void pi(String mode) {
		p(DomainController.getCurrentUser().getUsername() + ":" + mode + "~$ ");
	}

	private static void pl(String s) {
		System.out.println(s);
	}

	private static void p(String s) {
		System.out.print(s);
	}

	private static void pl() {
		System.out.println();
	}
}
