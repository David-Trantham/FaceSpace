package finalProject;
public class User { //the user's name is a key
	
	private String name;
	private int id;
	
	
	public User(String name, int id) {
		this.name = name;
		this.id = id;
		
	}
	
	public String name() {
		return this.name;
	}
	
	public int id() {
		return this.id;
	}
}
