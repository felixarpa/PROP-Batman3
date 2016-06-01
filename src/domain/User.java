package domain;

import comparators.IdComparator;
import domain.graph.Graph;
import domain.graph.Node;
import domain.graph.Term;
import exceptions.ProjectError;

import java.util.LinkedList;
import java.util.TreeSet;

public class User implements Comparable<User> {
	
	
	private String username;
	private String password;
	private boolean isAdmin;	//true => es administrador
	private LinkedList<Integer> favorites;
	private TreeSet<Term> favoriteTerms;
	
	/**
	 * Funcion que crea un usuario vacio.
	 */
	User() {}

	/**
	 * Funcion que crea el usuario a partir de un nombre de usuario,
	 * password y privilegio.
	 * 
	 * <p><b>Param:</b></p> <p><em>name</em>: nombre de usuario que se le quiere dar al nuevo usuario.</p>
	 * <p><b>Param:</b></p> <p><em>password</em>: password que se le quiere dar al nuevo usuario.</p>
	 */
	public User(String name, String password) {
		this.username = name;
		this.password = password;
		this.isAdmin = false;
		favorites = new LinkedList<>();
		favoriteTerms = new TreeSet<>(new IdComparator());
	}
	
	/**
	 * Funcion que crea el usuario a partir de una linea de la base de datos.
	 * 
	 * <p><b>Param:</b></p> <p><em>DBline</em>: linea de la base de datos que contiene un nombre, un password y un booleano de privilegio.</p>
	 */
	User(String DBline) {
		String[] fields = DBline.split("\t");
        if (fields.length < 3) throw new ProjectError("FATAL ERROR: Corrupted database");
		username = fields[0];
		password = fields[1];
		isAdmin = (fields[2].equals("true"));
		favorites = new LinkedList<>();
		favoriteTerms = new TreeSet<>(new IdComparator());
		if (fields.length > 3) {
			for (int i = 3; i < fields.length; ++i) {
				//Term t = Graph.getInstance().getNode(Term.makeId(Integer.parseInt(fields[i]))).asTerm();
				favorites.add(Integer.parseInt(fields[i]));
			}
		}
	}
//        User(String username, PairAux<String, Boolean> info){
//            username = username;
//            password = info.First().toString();
//            isAdmin = info.Second();
//	}
	
	/**
	 * Funcion que devuelve true si password coincide con el password del usuario, de otro modo devuelve false.
	 * 
	 * <p><b>Param:</b></p> <p><em>pass</em>: password que se quiere validar.</p>
	 * <p><b>Return:</b></p> <p>true si password es igual al password del usuario, false en cualquier otro caso.</p>
	 */
	public boolean validPass(String pass){
		return (pass.equals(this.password));
	}
	
	/**
	 * Funcion que devuelve el username del usuario.
	 * 
	 * <p><b>Return:</b></p> <p>el nombre de usuario de este usuario.</p>
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Funcion que devuelve el password del usuario.
	 * 
	 * <p><b>Return:</b></p> <p>el password de este usuario.</p>
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Funcion que devuelve el nivel de privilegio del usuario.
	 * 
	 * <p><b>Return:</b></p> <p>true si el usuario es admin, false en cualquier otro caso.</p>
	 */
	public boolean getAdmin(){
		return isAdmin;
	}
	
	/**
	 * Funcion que modifica el username del usuario.
	 * 
	 * <p><b>Param:</b></p> <p><em>name</em>: nombre de usuario que se le quiere dar al usuario.</p>
	 */
	public void setUsername(String name){
		username = name;
	}
	
	/**
	 * Funcion que modifica el password del usuario.
	 * 
	 * <p><b>Param:</b></p> <p><em>password</em>: password que se le quiere dar al usuario.</p>
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Funcion que modifica el privilegio del usuario.
	 * 
	 * <p><b>Param:</b></p> <p><em>admin</em>: privilegio que se le quiere dar al usuario. True si privilegio, false en cualquier otro caso.</p>
	 */
	public void setAdmin(boolean admin){
		isAdmin = admin;
	}

	public void addFavorite(Term term) {
		favoriteTerms.add(term);
	}

	public void deleteFavorite(Term term) {
		favoriteTerms.remove(term);
	}
	
	public boolean isFavorite(Term term) {
		return favoriteTerms.contains(term);
	}
	
	public TreeSet<Term> getFavorites() {
		return favoriteTerms;
	}

	public void updateTerms() {
		if (favorites != null) {
			for (Integer term : favorites) {
				Term term1 = (Term)Graph.getInstance().getNode(Term.makeId(term));
				if (term1 != null) favoriteTerms.add(term1);
				else throw new ProjectError("FATAL ERROR: Term "+ term+ " in database not found in graph");
			}
			favorites.clear();
			favorites = null;
		}
	}

	@Override
	public int compareTo(User u) {
		return username.compareTo(u.username);
	}
	
	@Override
	public boolean equals(Object obj) {
		return username.equals(((User) obj).username);
	}
	
	@Override
	public String toString() {
		String result = (username + '\t' + password + '\t' + isAdmin );
		for (Term term : favoriteTerms) {
			result += ("\t" + term.getId().getId());
		}
		return result;
	}
}
