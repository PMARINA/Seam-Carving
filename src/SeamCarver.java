import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeamCarver {
	Picture naPhoto;

	public SeamCarver(Picture picture) {
		this.naPhoto = picture;
	}

	public Picture picture() {
		return this.naPhoto;
	}

	public int width() {
		return this.naPhoto.width();
	}

	public int height() {
		return this.naPhoto.height();
	}

	public double energy(int x, int y) {
		boolean isEdgePixel = x == 0 || y == 0 || x == height() - 1 || y == width() - 1;
		if (!isEdgePixel) {
			// do normal thing
			Color pic = naPhoto.get(x, y);
			Color picxp1 = naPhoto.get(x + 1, y);
			Color picyp1 = naPhoto.get(x, y + 1);
			Color picxm1 = naPhoto.get(x - 1, y);
			Color picym1 = naPhoto.get(x, y - 1);
			double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
			double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
			double xgradblue = Math.abs(picxp1.getBlue() - picxm1.getBlue());
			double ygradblue = Math.abs(picyp1.getBlue() - picym1.getBlue());
			double xgradgreen = Math.abs(picxp1.getGreen() - picxm1.getGreen());
			double ygradgreen = Math.abs(picyp1.getGreen() - picym1.getGreen());
			double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen, 2);
			double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
			double energy = xgrad + ygrad;
			return energy;
		} else {
			int xp1 = 0, xm1 = 0, yp1 = 0, ym1 = 0;
			if (x == 0)
				xm1 = height() - 1;
			if (x == height() - 1)
				xp1 = 0;
			if (y == 0) {
				ym1 = width() - 1;
				System.out.println(ym1);
			}
			if (y == width() - 1)
				yp1 = 0;
			Color picxp1 = naPhoto.get(xp1, y);
			Color picyp1 = naPhoto.get(x, yp1);
			Color picxm1 = naPhoto.get(xm1, y);
			Color picym1 = naPhoto.get(x, ym1);
			double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
			double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
			double xgradblue = Math.abs(picxp1.getBlue() - picxm1.getBlue());
			double ygradblue = Math.abs(picyp1.getBlue() - picym1.getBlue());
			double xgradgreen = Math.abs(picxp1.getGreen() - picxm1.getGreen());
			double ygradgreen = Math.abs(picyp1.getGreen() - picym1.getGreen());
			double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen, 2);
			double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
			double energy = xgrad + ygrad;
			return energy;
		}
	}

	public int[] findVerticalSeam() {
		EdgeWeightedDigraph ed = new EdgeWeightedDigraph(height() * width() + 2);
		for (int i = 0; i < width(); i++) {
			ae(ed, 0, i);
		}
		for (int i = 1; i <= width() * height() + 1 - height(); i++) {
			if ((i - 1) % width() == 0 || i % width() == 0) {
				if (i2c(i)[0] == 0) {
					ae(ed, i, i + width() + 1);
					ae(ed, i, i + width() * 2 - 1);
					ae(ed, i, i + width());
				} else {
					ae(ed, i, i + width());
					ae(ed, i, i + 1);
					ae(ed, i, i + width() - 1);
				}
			} else {
				ae(ed, i, i + width() + 1);
				ae(ed, i, i + width() - 1);
				ae(ed, i, i + width());
			}
		}
		for (int i = 0; i < width(); i++) {
			ae(ed, ed.V() - width() - 1, ed.V() - 1);
		}
		// DONE ADDING ALL PIXELS TO THE EWD
		DijkstraSP dsp = new DijkstraSP(ed, 0);
		Iterable<DirectedEdge> pat = dsp.pathTo(ed.V() - 1);
		List<DirectedEdge> list = new ArrayList<DirectedEdge>();
		pat.iterator().forEachRemaining(list::add);
		int[] alexa = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			alexa[i] = list.get(i).to();
		return alexa;
	}

	private int[] i2c(int i) {
		int ycoord = (i - 1) % height();
		int xcoord = (i - 1) / width();
		if (ycoord < 0)
			ycoord = 0;
		if (xcoord < 0)
			xcoord = 0;
		if (xcoord > height() - 1)
			xcoord = height() - 1;
		if (ycoord > width() - 1)
			ycoord = width() - 1;
		if (xcoord < 0 || ycoord < 0)
			System.out.println("poop: " + xcoord + " " + ycoord);
		return new int[] { xcoord, ycoord };
	}

	private int c2i(int c, int c1) {
		return c * width() + c1 + 1;
	}

	private void ae(EdgeWeightedDigraph ed, int x, int y) {
		int[] i2c2 = i2c(y);
		ed.addEdge(new DirectedEdge(x, y, energy(i2c2[0], i2c2[1])));
	}

	public int[] findHorizontalSeam() {
		EdgeWeightedDigraph ed = new EdgeWeightedDigraph(width() * height() + 2);
		for (int i = 1; i < height(); i++) {
			bae(ed, 0, i);
		}
		for (int i = 1; i <= ed.V() - 1 - height(); i++) {
			bae(ed, i, i + height());
			if ((i - 1) % height() == 0 || i % height() == 0) {
				if ((i - 1) % height() == 0) {
					bae(ed, i, i + 2 * height() - 1);
					bae(ed, i, i + height() + 1);
				} else {
					bae(ed, i, i + height() - 1);
					bae(ed, i, i + 1);
				}
			}
		}
		for (int i = ed.V() - height() - 1; i < ed.V() - 2; i++) {
			bae(ed, i, ed.V() - 1);
		}
		DijkstraSP dsp = new DijkstraSP(ed, 0);
		Iterable<DirectedEdge> pat = dsp.pathTo(ed.V() - 1);
		List<DirectedEdge> list = new ArrayList<DirectedEdge>();
		pat.iterator().forEachRemaining(list::add);
		Integer[] bob = list.toArray(new Integer[0]);
		int[] alexa = new int[bob.length];
		for (int i = 0; i < bob.length; i++)
			alexa[i] = bob[i];
		return alexa;
	}

	public void bae(EdgeWeightedDigraph ed, int x, int y) {
		int[] i2b2 = bi2c2(y);
		ed.addEdge(new DirectedEdge(x, y, energy(i2b2[0], i2b2[1])));
	}

	public int[] bi2c2(int x) {
		int xcoord = (x - 1 % height());
		int ycoord = (x - 1) / height();
		return new int[] { xcoord, ycoord };
	}

	public void removeHorizontalSeam(int[] seam) {
		boolean[][] arr = new boolean[height()][width()];
		for (int i = 0; i < arr[0].length; i++) {
			arr[seam[i]][i] = true;
		}
		Picture np = new Picture(arr.length, arr[0].length);
		for (int i = 0; i < arr.length - 1; i++) {
			int shiznit = 0;
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j]) {
					shiznit = -1;
				} else {
					np.set(i, j + shiznit, naPhoto.get(i, j));
				}
			}
			naPhoto = np;
		}
	}

	public void removeVerticalSeam(int[] seam) {
		Picture np = new Picture(height(), width() - 1);
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				if (i2c(seam[i])[1] <= j || j == 0)
					np.set(i, j, naPhoto.get(i, j));
				else
					np.set(i, j - 1, naPhoto.get(i, j));
			}
		}
		naPhoto = np;
	}

	public void drawVerticalSeam(int[] seam) {
		boolean[][] arr = new boolean[height()][width()];
		for (int i = 0; i < arr.length; i++) {
			arr[i2c(seam[i])[1]][i2c(seam[i])[0]] = true;
		}
		Picture np = new Picture(arr.length, arr[0].length);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j])
					np.set(i, j, Color.CYAN);
				else
					np.set(i, j, naPhoto.get(i, j));
			}
		}
		naPhoto = np;
	}

	public static void main(String[] args) {
		SeamCarver sc = new SeamCarver(new Picture(new File("C:\\Users\\bluer\\Downloads\\HJoceanSmall.jpg")));
		System.out.println(sc.height());
		System.out.println(sc.width());
		// sc.drawVerticalSeam(sc.findVerticalSeam());

		for (int i = 0; i < 10; i++) {
			int[] vert = sc.findVerticalSeam();
			sc.drawVerticalSeam(vert);
			System.out.println(i + "%");
		}

		sc.naPhoto.save("C:\\Users\\bluer\\Downloads\\downloade.jpg");
	}
}