public class Subset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 0;
		RandomizedQueue<String> rqs = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);

		if (k > 0) {

			while (!StdIn.isEmpty() && N < k) {
				rqs.enqueue(StdIn.readString());
				N++;
			}

			while (!StdIn.isEmpty()) {
				rqs.dequeue();
				rqs.enqueue(StdIn.readString());
				N++;
			}

			if (k <= N) {
				for (String s : rqs) {
					System.out.println(s);
					k--;
					if (k == 0)
						break;
				}
			}

		}
	}

}
