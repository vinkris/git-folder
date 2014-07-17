import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		Item item;
		Node next;
		Node prev;
	}

	private Node first;
	private Node last;
	private int N;

	public Deque() {
		first = last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return (N == 0);
	}

	public int size() {
		return N;
	}

	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node oldFirst = first;

		first = new Node();
		first.item = item;
		first.next = null;
		first.prev = null;

		if (isEmpty())
			last = first;
		else {
			first.next = oldFirst;
			if (oldFirst != null)
				oldFirst.prev = first;
		}

		N++;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node oldLast = last;

		last = new Node();
		last.item = item;
		last.next = null;
		last.prev = null;

		if (isEmpty())
			first = last;
		else {
			if (oldLast != null)
				oldLast.next = last;
			last.prev = oldLast;
		}

		N++;
	}

	public Item removeFirst() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Item item = first.item;

		if (first.next != null)
			first.next.prev = null;
		first = first.next;
		N--;

		if (isEmpty())
			last = null;
		return item;
	}

	public Item removeLast() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Item item = last.item;

		if (last.prev != null)
			last.prev.next = null;
		last = last.prev;
		N--;

		if (isEmpty())
			first = null;
		return item;
	}

	public Iterator<Item> iterator() {
		return new linkedIterator();
	}

	private class linkedIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private Item getLast() {
		return (last != null) ? last.item : null;
	}

	private void printDeq() {
		for (Item i : this) {
			System.out.print(i + " -> ");
		}
		System.out.println("null");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> deq = new Deque<Integer>();
		deq.addFirst(1);
		deq.addFirst(2);
		deq.addLast(3);
		deq.addLast(4);
		deq.addFirst(5);
		deq.addFirst(6);
		deq.addLast(7);

		System.out.println("Last: " + deq.getLast());
		System.out.println("Size: " + deq.size());
		deq.printDeq();

		System.out.println("Removing first " + deq.removeFirst());
		deq.printDeq();
		System.out.println("Removing last " + deq.removeLast());
		deq.printDeq();
		System.out.println("Removing first " + deq.removeFirst());
		deq.printDeq();
		System.out.println("Removing first " + deq.removeFirst());
		deq.printDeq();
		System.out.println("Removing first " + deq.removeFirst());
		deq.printDeq();
		System.out.println("Removing first " + deq.removeFirst());
		deq.printDeq();

		System.out.println("Last: " + deq.getLast());
		System.out.println("Size: " + deq.size());
		deq.printDeq();

		deq.removeFirst();
		System.out.println("Size: " + deq.size());
		deq.printDeq();

		deq.addFirst(1);
		deq.addFirst(2);
		deq.addLast(3);
		deq.addLast(4);
		deq.addFirst(5);
		deq.addFirst(6);
		deq.addLast(7);

		System.out.println("Last: " + deq.getLast());
		System.out.println("Size: " + deq.size());
		deq.printDeq();
	}

}
