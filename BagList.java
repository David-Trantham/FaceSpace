package finalProject;

import java.util.Iterator;

public class BagList<Item> implements Iterable<Item> {
	private int size;
	private Node first; // first node in list

	private class Node {
		Item item;
		Node next;
	}

	public void add(Item item) { // same as push() in Stack
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;

		}
		
		public void remove() { 
		}

		public Item next() {
			Item item = current.item;
			current = current.next;

			return item;

		}

	}
	
	public void remove(Item key) { 
		
			
			if (this.isEmpty()) {
			System.out.println("Empty");
			throw new RuntimeException("try to remove from an empty list");
			}
			
			if (size == 1) {
				first = first.next;
				size--;
			}
			else {
				for (Node x = first; x != null; x = x.next) {
				//System.out.println("HERE?");
				if (x.item.equals(key)) {
					System.out.println("HI");
					x = x.next;
					size--;
					System.out.println(size);
				}
			}
			
		}
		
	}

}
