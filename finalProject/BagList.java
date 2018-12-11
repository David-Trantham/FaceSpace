package finalProject;

import java.util.Iterator;

public class BagList<Item> implements Iterable<Item> {
	private int size;
	private Node first; // first node in list

	private class Node {
		Item item;
		Node next;

		public String toString() {
			return "+" + this.item + ".";
		}
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

		if ((this.size == 1) && (first.item.equals(key))) {
			first = first.next;
			size--;
		}

		else if (first.item.equals(key)) {
			first = first.next;
			size--;
		}

		else {
			Node x = first;

			for (x = first; (x != null) && (x.next != null); x = x.next) {
				if (x.next.item.equals(key)) {
					x.next = x.next.next;
					size--;
				}
			}

		}

	}

}
