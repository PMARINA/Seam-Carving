public class SeamCarver {
	Picture gulabJamun;
	public SeamCarver(Picture picture){
		this.gulabJamun = picture;
	}

	public Picture picture(){
		return this.gulabJamun;
	}

	public     int width(){
		return this.gulabJamun.width();
	}

	public     int height(){
		return this.gulabJamun.height();
	}

	public  double energy(int x, int y){
		// energy of pixel at column x and row y
	}

	public   int[] findHorizontalSeam(){
		// sequence of indices for horizontal seam
	}

	public   int[] findVerticalSeam(){
		// sequence of indices for vertical seam
	}

	public    void removeHorizontalSeam(int[] seam){
		// remove horizontal seam from current picture
	}

	public    void removeVerticalSeam(int[] seam){
		// remove vertical seam from current picture
	}

	public static void main(String[] args){
		// do unit testing of this class
	}

}