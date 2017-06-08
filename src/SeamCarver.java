import java.awt.Color;

public class SeamCarver {
	Picture naPhoto;
	public static void main(String[] args){
		// do unit testing of this class
	}

	public SeamCarver(Picture picture){
		this.naPhoto = picture;
	}

	public Picture picture(){
		return this.naPhoto;
	}

	public     int width(){
		return this.naPhoto.width();
	}

	public     int height(){
		return this.naPhoto.height();
	}

	public  double energy(int x, int y){
		boolean isEdgePixel = x == 0 || y == 0 || x == width()-1 || y == height()-1;
		if(!isEdgePixel){
			//do normal thing
			Color pic = naPhoto.get(x, y);
			Color picxp1 = naPhoto.get(x+1, y);
			Color picyp1 = naPhoto.get(x, y+1);
			Color picxm1 = naPhoto.get(x-1, y);
			Color picym1 = naPhoto.get(x, y-1);
			double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
			double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
			double xgradblue = Math.abs(picxp1.getBlue()-picxm1.getBlue());
			double ygradblue = Math.abs(picyp1.getBlue()-picym1.getBlue());
			double xgradgreen = Math.abs(picxp1.getGreen()-picxm1.getGreen());
			double ygradgreen = Math.abs(picyp1.getGreen()-picym1.getGreen());
			double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen,2);
			double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
			double energy = xgrad + ygrad;
			return energy;
		}
		else{
			//x = 0 = top
			//y = 0 = left
			//x = border = bottom
			// y = border = right
			if(x == 0){
				Color pic = naPhoto.get(x, y);
				Color picxp1 = naPhoto.get(x+1, y);
				Color picyp1 = naPhoto.get(x, y+1);
				Color picxm1 = naPhoto.get(height()-1, y);
				Color picym1 = naPhoto.get(x, y-1);
				double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
				double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
				double xgradblue = Math.abs(picxp1.getBlue()-picxm1.getBlue());
				double ygradblue = Math.abs(picyp1.getBlue()-picym1.getBlue());
				double xgradgreen = Math.abs(picxp1.getGreen()-picxm1.getGreen());
				double ygradgreen = Math.abs(picyp1.getGreen()-picym1.getGreen());
				double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen,2);
				double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
				double energy = xgrad + ygrad;
				return energy;
			}
			else if(x == height()-1){
				Color pic = naPhoto.get(x, y);
				Color picxp1 = naPhoto.get(0, y);
				Color picyp1 = naPhoto.get(x, y+1);
				Color picxm1 = naPhoto.get(x-1, y);
				Color picym1 = naPhoto.get(x, y-1);
				double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
				double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
				double xgradblue = Math.abs(picxp1.getBlue()-picxm1.getBlue());
				double ygradblue = Math.abs(picyp1.getBlue()-picym1.getBlue());
				double xgradgreen = Math.abs(picxp1.getGreen()-picxm1.getGreen());
				double ygradgreen = Math.abs(picyp1.getGreen()-picym1.getGreen());
				double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen,2);
				double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
				double energy = xgrad + ygrad;
				return energy;
			}
			else if(y == width()-1){
				Color pic = naPhoto.get(x, y);
				Color picxp1 = naPhoto.get(x+1, y);
				Color picyp1 = naPhoto.get(x, 0);
				Color picxm1 = naPhoto.get(x-1, y);
				Color picym1 = naPhoto.get(x, y-1);
				double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
				double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
				double xgradblue = Math.abs(picxp1.getBlue()-picxm1.getBlue());
				double ygradblue = Math.abs(picyp1.getBlue()-picym1.getBlue());
				double xgradgreen = Math.abs(picxp1.getGreen()-picxm1.getGreen());
				double ygradgreen = Math.abs(picyp1.getGreen()-picym1.getGreen());
				double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen,2);
				double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
				double energy = xgrad + ygrad;
				return energy;
			}
			else{
				Color pic = naPhoto.get(x, y);
				Color picxp1 = naPhoto.get(x+1, y);
				Color picyp1 = naPhoto.get(x, y+1);
				Color picxm1 = naPhoto.get(x-1, y);
				Color picym1 = naPhoto.get(x, height()-1);
				double xgradred = Math.abs(picxp1.getRed() - picxm1.getRed());
				double ygradred = Math.abs(picxp1.getRed() - picym1.getRed());
				double xgradblue = Math.abs(picxp1.getBlue()-picxm1.getBlue());
				double ygradblue = Math.abs(picyp1.getBlue()-picym1.getBlue());
				double xgradgreen = Math.abs(picxp1.getGreen()-picxm1.getGreen());
				double ygradgreen = Math.abs(picyp1.getGreen()-picym1.getGreen());
				double xgrad = Math.pow(xgradred, 2) + Math.pow(xgradblue, 2) + Math.pow(xgradgreen,2);
				double ygrad = Math.pow(ygradred, 2) + Math.pow(ygradblue, 2) + Math.pow(ygradgreen, 2);
				double energy = xgrad + ygrad;
				return energy;
			}
		}
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

}