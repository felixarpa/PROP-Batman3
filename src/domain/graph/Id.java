package domain.graph;

import exceptions.ProjectError;

public class Id implements Comparable<Id>{

	private int id;

	public Id(int id, int type){
		if(type < 0 || type > 3) throw new ProjectError("Type must be between 0 and 3");
		this.id = (id << 2) + type;
	}
	
	@Override
	public int compareTo(Id o) {
		int result = (id & 3) - (o.id & 3);
		return (result == 0 ? id-o.id : result);
	}
	
	@Override
	public boolean equals(Object obj) {
		return id == ((Id) obj).id;
	}
	
	@Override
	public String toString() {
		return ("" + (id >> 2));
	}

	public int getId() {
		return id >> 2;
	}
	
	public int getType(){
		return id & 3;
	}
}
