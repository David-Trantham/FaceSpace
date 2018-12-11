package finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class FaceSpace {
	private HashTableChained htc;
	private Graph friends;
	private int nextid;

	public FaceSpace() {
		this.nextid = 0;
		this.htc = new HashTableChained(31);
		friends = new Graph(31);

	}

	public void AddUser(String name) { // Adds user to the htc based on the name string
		User newUser = new User(name, nextid++);
		htc.put(newUser);

	}

	public User searchUser(String name) {
		// Search for user
		return htc.get(name);
	}

	public void AddFriend(String name1, String name2) {
		// Add friends --> add edges
		friends.addEdge(searchUser(name1).id(), searchUser(name2).id());
	}

	public void RemoveFriend(String name1, String name2) {
		// Remove friends
		int id1 = searchUser(name1).id();
		int id2 = searchUser(name2).id();

		friends.removeEdge(id1, id2);

	}

	public int degreeOfSeperation(String name1, String name2) {
		// Find the shortest path
		int id1 = searchUser(name1).id();
		int id2 = searchUser(name2).id();

		if (isConnected(name1, name2)) {
			BreadthFirstPaths paths = new BreadthFirstPaths(friends, id1);
			Iterable p = paths.pathTo(id2);
			java.util.Stack s = (java.util.Stack) p;
			return s.size() - 1;
		} else {
			return -1;
		}
	}

	private boolean isConnected(String name1, String name2) {
		ConnectedComponents cc;
		int id1 = searchUser(name1).id();
		int id2 = searchUser(name2).id();

		cc = new ConnectedComponents(friends);
		return (cc.id(id1) == cc.id(id2));
	}
	


	public static void main(String[] args) {
		FaceSpace f = new FaceSpace();
		f.AddUser("0");// 0
		f.AddUser("1");// 1
		f.AddUser("2");// 2
		f.AddUser("3");// 3
		f.AddFriend("2", "3");
		f.AddFriend("0", "2");
		f.AddFriend("1", "3");
		f.AddFriend("0", "1");
		f.RemoveFriend("1", "0");
		f.RemoveFriend("1", "3");
//		f.RemoveFriend("3", "2");
		System.out.println(f.friends.toString());
//		System.out.println(f.friends.numVertices());
		System.out.println(f.degreeOfSeperation("1", "2"));

	}
}
