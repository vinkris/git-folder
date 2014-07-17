public class PercolationStats {
	private double[] x;
	private final int iterations;

	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(final int N, final int iter) {
		
		if (N <= 0 || iter <= 0) {
			throw new java.lang.IllegalArgumentException("Invalid N or T values");
		}
		
		this.iterations = iter;
		x = new double[iterations];

		for (int i = 0; i < iterations; i++) {

			Percolation perc = new Percolation(N);

			while (!perc.percolates()) {
				int xx = StdRandom.uniform(N) + 1;
				int yy = StdRandom.uniform(N) + 1;
				if (!perc.isOpen(xx, yy)) {
					perc.open(xx, yy);
				}
			}

			int opened = 0;
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= N; col++) {
					if (perc.isOpen(row, col)) {
						opened++;
					}
				}
			}
			x[i] = (double) opened / (N * N);
			// System.out.println("Count: " + count + " N: " + N + " x: " +
			// x[i]);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		double sum = 0;
		for (int i = 0; i < iterations; i++) {
			sum += x[i];
		}

		return sum / iterations;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		double mean = mean();
		double sum = 0;
		for (int i = 0; i < iterations; i++) {
			sum += (x[i] - mean) * (x[i] - mean);
		}

		return Math.sqrt(sum / (iterations - 1));
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		double mean = mean();
		double stddev = stddev();
		return (mean - ((1.96 * stddev) / Math.sqrt(iterations)));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		double mean = mean();
		double stddev = stddev();
		return (mean + ((1.96 * stddev) / Math.sqrt(iterations)));
	}

	// test client, described below
	public static void main(final String[] args) {
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		PercolationStats ps = new PercolationStats(N, T);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = " + ps.confidenceLo()
				+ ", " + ps.confidenceHi());
	}
}
