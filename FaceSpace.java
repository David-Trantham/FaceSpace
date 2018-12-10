package finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class FaceSpace {
	private HashTableChained htc;
	private Graph friends;
	private int nextid;
	private String pathToFile;
	
	public FaceSpace() {
		this.nextid = 0;
		this.htc = new HashTableChained(31);
		this.pathToFile = "/Users/arvinwang/Desktop/NameList.txt";
		friends = new Graph(31);
		
	}

	public void AddUser(String name) {	//Adds user to the htc based on the name string
		User newUser = new User(name, nextid++);
		htc.put(newUser);
		//System.out.println(htc.hash(name));

	}

	public User searchUser(String name) {
		// Search for user
		return htc.get(name);
	}
	
	public void AddFriend(String name1, String name2) {
		// Add friends --> add edges
		friends.addEdge(searchUser(name1).id(),searchUser(name2).id());
	}
	
	public void RemoveFriend(String name1, String name2) {
		// Remove friends
		friends.removeEdge(searchUser(name1).id(), searchUser(name2).id());
	}
	
	public int degreeOfSeperation(String name1, String name2) {
		int count = 0;
		BreadthFirstPaths paths = new BreadthFirstPaths(friends, searchUser(name1).id());
		for(int i : paths.pathTo(searchUser(name2).id())) {
			count++;
		}
		return count - 1;
		
	}
	// Find the shortest path
	public static void main(String[] args) throws FileNotFoundException {
		FaceSpace f = new FaceSpace();
		f.AddUser("0");
		f.AddUser("1");
		f.AddUser("2");
		f.AddUser("3");
		f.AddFriend("2", "3");
		f.AddFriend("0", "2");
		f.AddFriend("0", "3");
		f.AddFriend("0", "1");
//		f.RemoveFriend("0", "1");
//		f.RemoveFriend("1", "0");
//		f.RemoveFriend("3", "2");
		System.out.println(f.friends.toString());
		System.out.println(f.degreeOfSeperation("2", "1"));

	}
}

