package domain;

import comparators.IdComparator;
import domain.graph.Graph;
import domain.graph.Node;
import domain.graph.Term;
import exceptions.ProjectError;

import java.util.TreeSet;

public class User implements Comparable<User> {
	
	
	private String username;
	private String password;
	private boolean isAdmin;	//true => es administrador
	private TreeSet<Term> favorites;
	
	/**
	 * Función que crea un usuario vacío.
	 */
	User() {}

	/**
	 * Funci�n que crea el usuario a partir de un nombre de usuario,
	 * password y privilegio.
	 * 
	 * @param name nombre de usuario que se le quiere dar al nuevo usuario.
	 * @param password password que se le quiere dar al nuevo usuario.
	 */
	public User(String name, String password) {
		this.username = name;
		this.password = password;
		this.isAdmin = false;
		favorites = new TreeSet<>(new IdComparator());
	}
	
	/**
	 * Funci�n que crea el usuario a partir de una linea de la base de datos.
	 * 
	 * @param DBline linea de la base de datos que contiene un nombre, un password y un booleano de privilegio.
	 */
	User(String DBline) {
		String[] fields = DBline.split("\t");
        if (fields.length < 3) throw new ProjectError("FATAL ERROR: CORRUPTED DATABASA D:D:D:D:D");
		username = fields[0];
		password = fields[1];
		isAdmin = (fields[2].equals("true"));
		favorites = new TreeSet<>(new IdComparator());
		if (fields.length > 3) {
			for (int i = 3; i < fields.length; ++i) {
				Term t = Graph.getInstance().getNode(Term.makeId(Integer.parseInt(fields[i]))).asTerm();
				favorites.add(t);
			}
		}
	}
//        User(String username, PairAux<String, Boolean> info){
//            username = username;
//            password = info.First().toString();
//            isAdmin = info.Second();
//	}
	
	/**
	 * Funci�n que devuelve true si password coincide con el password del usuario, de otro modo devuelve false.
	 * 
	 * @param pass password que se quiere validar.
	 * @return	true si password es igual al password del usuario, false en cualquier otro caso.
	 */
	public boolean validPass(String pass){
		return (pass.equals(this.password));
	}
	
	/**
	 * Funci�n que devuelve el username del usuario.
	 * 
	 * @return	el nombre de usuario de este usuario.
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Funci�n que devuelve el password del usuario.
	 * 
	 * @return	el password de este usuario.
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Funci�n que devuelve el nivel de privilegio del usuario.
	 * 
	 * @return	true si el usuario es admin, false en cualquier otro caso.
	 */
	public boolean getAdmin(){
		return isAdmin;
	}
	
	/**
	 * Funci�n que modifica el username del usuario.
	 * 
	 * @param name nombre de usuario que se le quiere dar al usuario.
	 */
	public void setUsername(String name){
		username = name;
	}
	
	/**
	 * Funci�n que modifica el password del usuario.
	 * 
	 * @param password password que se le quiere dar al usuario.
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Funci�n que modifica el privilegio del usuario.
	 * 
	 * @param admin privilegio que se le quiere dar al usuario. True si privilegio, false en cualquier otro caso.
	 */
	public void setAdmin(boolean admin){
		isAdmin = admin;
	}

	public void addFavorite(Term term) {	favorites.add(term);}

	public void deleteFavorite(Term term) {
		favorites.remove(term);
	}
	
	public boolean isFavorite(int term) {
		return favorites.contains(term);
	}
	
	public TreeSet<Term> getFavorites() {
		return favorites;
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
		for (Term term : favorites) {
			result += ('\t' + term.getId().toString());
		}
		return result;
	}
}
