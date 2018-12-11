package finalProject;

import java.util.Scanner;

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
//		System.out.println(htc.hash(name));
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
//		**User Interface**
		FaceSpace f = new FaceSpace();

		System.out.println("Welcome to FaceSpace! Please enter a command, or type 'help' for help.");
		String name1, name2;
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while (running) {
			if (scanner.hasNext()) {
				String input = scanner.next();
				input = input.toLowerCase();
				switch (input) {
				case "help":
					System.out.println(f.helpMessage());
					break;
				case "adduser":
					name1 = f.nextParam(scanner);

					if(f.searchUser(name1) == null) {
					f.AddUser(name1);
					System.out.println("User " + name1 + " successfully added to FaceSpace! :)");
					} else {
						System.out.println("User " + name1 + " is already on FaceSpace!");
					}
					break;
				case "searchuser":
					name1 = f.nextParam(scanner);
					User searchedUser = f.searchUser(name1);
					if (searchedUser == null) {
						System.out.println("User '" + name1 + "' not found.");
					} else {
					int id = f.searchUser(name1).id();
					System.out.println("User '" + name1 + "' with ID number " + searchedUser.id() + " was found!");
					System.out.println("Friends: " + friendList(name1, f));
					}
					break;
				case "addfriend":
					name1 = f.nextParam(scanner);
					name2 = f.nextParam(scanner);
					f.AddFriend(name1, name2);
					System.out.println("User " + name1 + " is now friends with user " + name2 + "!");
					break;
				case "removefriend":
					name1 = f.nextParam(scanner);
					name2 = f.nextParam(scanner);

					f.RemoveFriend(name1, name2);
					System.out.println("User " + name1 + " is no longer friends with " + name2 + ".");
					break;
				case "degree":
					name1 = f.nextParam(scanner);
					name2 = f.nextParam(scanner);
					int deg = f.degreeOfSeperation(name1, name2);
					if(deg != -1) {
					System.out.println("User " + name1 + " and user " + name2 + " are " + deg + " degree(s) of separation apart.");
					} else {
						System.out.println(name1 + " and " + name2 + " are not friends!");
					}
					break;
				case "exit":
					scanner.close();
					running = false;
					break;
				default:
					System.out.println("Command not recognized. Please enter a valid command or type 'help' for help.");
				}
			}
		}

//		**Testing**
//		FaceSpace f = new FaceSpace();
//		f.AddUser("0");
//		f.AddUser("1");
//		f.AddUser("2");
//		f.AddUser("3");
//		f.AddFriend("2", "3");
//		f.AddFriend("0", "2");
//		f.AddFriend("0", "3");
//		f.AddFriend("0", "1");
//		System.out.println(f.friends.toString());
//		System.out.println(f.degreeOfSeperation("2", "1"));

	}

	private final String helpMessage() {
		return "=============================================================================================================================="
				+ System.lineSeparator() + "Available commands:" + System.lineSeparator()
				+ "adduser 'name' : Adds a new user with name 'name' to FaceSpace. Welcome aboard!"
				+ System.lineSeparator()
				+ "searchuser 'name' : Searches for an existing user with name 'name' in the FaceSpace network and returns their ID if found."
				+ System.lineSeparator()
				+ "addfriend 'name1' 'name2' : Connects users denoted by 'name1' and 'name2' as friends. "
				+ System.lineSeparator()
				+ "removefriend 'name1' 'name2' : Removes a friendship between users 'name1' and 'name2', if a friendship was there."
				+ System.lineSeparator()
				+ "degree 'name1' 'name2' : Determines the shortest friendship path between user with 'name1' and user with 'name2'."
				+ System.lineSeparator()
				+ "exit : Exits the program"
				+ System.lineSeparator()
				+ "==============================================================================================================================";
	}

	private String nextParam(Scanner s) { // Returns the next param
		if (s.hasNext()) {
			return s.next();
		} else {
			return null; // TODO: Is this alright?
		}
	}

	private static String friendList(String name1, FaceSpace f) {
		int id1 = f.searchUser(name1).id();
		String friendList = "";
		for (int i : f.friends.adj(id1)) {
			friendList += (f.friends.adj(i).iterator().next());
		}
		return friendList;

	}
}
