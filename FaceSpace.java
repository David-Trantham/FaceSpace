package finalProject;

import java.util.ArrayList;
import java.util.Hashtable;

public class FaceSpace {
	private HashTableChained hc;
	private Graph friends;
	private int nextid;
	
	public FaceSpace() {
		this.nextid = 0;
		this.hc = new HashTableChained(31);
		friends = new Graph("/Users/arvinwang/Desktop/NameList.txt");
		
	}

	public void AddUser(String name) {
		
		
		
		
		
	}

	public User searchUser(String name) {
		// Search for user
		
	}
	
	public void AddFriend(String name) {
		// Add friends --> add edges
	}
	
	public void RemoveFriend(String name) {
	// Remove friends
		
	}
	
	
	// Find the shortest path
	public static void main(String[] args) {
		FaceSpace f = new FaceSpace();
		System.out.println(f.friends);
	}
}

