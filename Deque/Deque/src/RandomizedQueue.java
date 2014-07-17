import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int last;
	private int N;

	public RandomizedQueue() {
		last = 0;
		items = (Item[]) new Object[2];
		N = 0;
	}

	private void resize(int n) {
		Item[] copy = (Item[]) new Object[n];

		for (int i = 0, j = 0; i < last; i++) {
			Item item = items[i];
			if (item != null)
				copy[j++] = item;
		}

		items = copy;
		last = N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();

		if (last == items.length)
			resize(2 * items.length);

		items[last++] = item;
		N++;
	}

	private int returnItem() {

		if (isEmpty() && items.length < 1) {
			return 0;
		}
		
		int randValue = StdRandom.uniform(items.length);
		while (items[randValue] == null) {
			randValue = StdRandom.uniform(items.length);
		}

		return randValue;
	}

	public Item sample() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();

		return items[returnItem()];
	}

	public Item dequeue() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();

		Item retItem;
		int randIndex = returnItem();

		retItem = items[randIndex];
		items[randIndex] = null;
		N--;

		if (N > 0 && N == (items.length / 4))
			resize(items.length / 2);

		if (isEmpty()) {
			last = 0;
		}

		return retItem;
	}

	private int itemsLen() {
		return items.length;
	}
	
	private int getLast() {
		return last;
	}

	public Iterator<Item> iterator() {
		return new QIterator();
	}

	private class QIterator implements Iterator<Item> {
		private Item[] currElems;
		private int currIdx = 0;

		public QIterator() {
			int i, j;
			currElems = (Item[]) new Object[N];
			for (i = 0, j = 0; j < last; j++) {
				if (items[j] != null) {
					currElems[i++] = items[j];
				}
			}

			assert (i == N - 1);

			StdRandom.shuffle(currElems);
		}

		public boolean hasNext() {
			return currIdx < N;
		}

		public Item next() {
			if (hasNext())
				return currElems[currIdx++];
			else
				throw new java.util.NoSuchElementException();
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

		for (int j = 0; j < 3; j++) {
			System.out.println("***** Enqueuing ******");
			for (int i = 0; i < 10; i++) {
				System.out.println("*** Iter " + i + " ***");
				rq.enqueue(i);
				System.out.println("N: " + rq.size());
				System.out.println("Items length: " + rq.itemsLen());
				for (Integer d : rq) {
					System.out.print(d + ", ");
				}
				System.out.println("END");
				System.out.println("Last: " + rq.getLast());
			}

			System.out.println("****** Dequeing ******");
			for (int i = 0; i < 5; i++) {

				System.out.println("*** Iter " + i + " ***");
				System.out.println("Dequed element " + rq.dequeue());
				System.out.println("N: " + rq.size());
				System.out.println("Items length: " + rq.itemsLen());

				System.out.println("Iterating");
				for (Integer d : rq) {
					System.out.print(d + ", ");
				}

				System.out.println("END");
				System.out.println("Last: " + rq.getLast());

			}
			
			System.out.println("***** Enqueuing ******");
			for (int i = 0; i < 2; i++) {
				System.out.println("*** Iter " + i + " ***");
				rq.enqueue(i);
				System.out.println("N: " + rq.size());
				System.out.println("Items length: " + rq.itemsLen());
				for (Integer d : rq) {
					System.out.print(d + ", ");
				}
				System.out.println("END");
				System.out.println("Last: " + rq.getLast());
			}

		}
	}

}
