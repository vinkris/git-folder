public class Percolation {

	private boolean[][] sites;
	private final int N;
	private WeightedQuickUnionUF wq;
	private WeightedQuickUnionUF wqN;

	public Percolation(int N) {
		if (N <= 0)
			throw new java.lang.IllegalArgumentException("Invalid N value");
		sites = new boolean[N][N];
		wq = new WeightedQuickUnionUF(N * N + 2);
		wqN = new WeightedQuickUnionUF(N * N + 1);
		this.N = N;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sites[i][j] = false;
			}
		}
	}

	private int getTopIdx() {
		return N * N;
	}

	private int getBottomIdx() {
		return N * N + 1;
	}

	private void fill(int x, int y) {

		if ((x - 1 >= 1 && isOpen(x - 1, y))) {
			wq.union(pointToIdx(x - 1, y - 1), pointToIdx(x - 2, y - 1));
			wqN.union(pointToIdx(x - 1, y - 1), pointToIdx(x - 2, y - 1));
		}

		if ((x + 1 <= N && isOpen(x + 1, y))) {
			wq.union(pointToIdx(x - 1, y - 1), pointToIdx(x, y - 1));
			wqN.union(pointToIdx(x - 1, y - 1), pointToIdx(x, y - 1));
		}

		if ((y - 1 >= 1 && isOpen(x, y - 1))) {
			wq.union(pointToIdx(x - 1, y - 1), pointToIdx(x - 1, y - 2));
			wqN.union(pointToIdx(x - 1, y - 1), pointToIdx(x - 1, y - 2));
		}

		if ((y + 1 <= N && isOpen(x, y + 1))) {
			wq.union(pointToIdx(x - 1, y - 1), pointToIdx(x - 1, y));
			wqN.union(pointToIdx(x - 1, y - 1), pointToIdx(x - 1, y));
		}

	}

	public void open(int x, int y) {

		if (isOpen(x, y))
			return;

		sites[x - 1][y - 1] = true;

		if (x == N) {
			wq.union(pointToIdx(x - 1, y - 1), getBottomIdx());
		}

		if (x == 1) {
			wq.union(pointToIdx(x - 1, y - 1), getTopIdx());
			wqN.union(pointToIdx(x - 1, y - 1), getTopIdx());
		}

		if ((x - 1 >= 1 && isFull(x - 1, y))
				|| (x + 1 <= N && isFull(x + 1, y))
				|| (y - 1 >= 1 && isFull(x, y - 1))
				|| (y + 1 <= N && isFull(x, y + 1))) {
			wq.union(pointToIdx(x - 1, y - 1), getTopIdx());
			wqN.union(pointToIdx(x - 1, y -1), getTopIdx());
		}

		fill(x, y);
	}

	private int pointToIdx(int i, int j) {
		return i * N + j;
	}

	public boolean isOpen(int x, int y) {
		if (x < 1 || x > N || y < 1 || y > N) {
			throw new java.lang.IndexOutOfBoundsException();
		}

		return sites[x - 1][y - 1];
	}

	public boolean isFull(int x, int y) {
		if (x < 1 || x > N || y < 1 || y > N) {
			throw new java.lang.IndexOutOfBoundsException();
		}

		return wq.connected(pointToIdx(x - 1, y - 1), getTopIdx()) 
				&& wqN.connected(pointToIdx(x - 1, y - 1), getTopIdx());
	}

	public boolean percolates() {
		return wq.connected(getTopIdx(), getBottomIdx());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Percolation perc = new Percolation(4);
		perc.open(2, 1);
		perc.open(1, 1);
		perc.open(3, 1);
		perc.open(3, 3);
		perc.open(4, 4);
		perc.open(4, 3);
		perc.open(3, 2);

		System.out
				.println("Percolates? :" + (perc.percolates() ? "Yes" : "No"));

	}

}
