package finalProject;

public class Chain {

	private class Node {
		
		String name;
		User user;
		Node next;
		
		public Node(User user, Node next) {
			this.user = user;
			this.next = next;
			this.name = user.name();
		}
	}
	
	private Node first;
	private int size;
	
	public void put(User user) {
		String name = user.name();
		for (Node x = first; x != null; x = x.next) { //search the chain if the key already exists;
			if (name.equals(x.name)) {
				x.user = user;
				return;
			}
		}
		first = new Node(user, first);//insert it to the first of the chain
		size++;
	}
	
	public User get(String name) { //get the value;
		for (Node x = first; x != null; x = x.next) {
			if (name.equals(x.name))
				return x.user;
		}
		return null;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String s = "";
		for (Node x = first; x != null; x = x.next) {
			s += x.name;
		}
		return s;
	}
}
