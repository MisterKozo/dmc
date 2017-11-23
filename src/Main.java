import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Main {
	public static Mat rotate(Mat srcMat, double angle) {
		Mat dstMat = new Mat();
		
		Point center = new Point(srcMat.width()/2,srcMat.height()/2);
		double scale = 1.0;

		Mat mapMatrix = Imgproc.getRotationMatrix2D(center, angle, scale);
		Imgproc.warpAffine(srcMat, dstMat, mapMatrix, srcMat.size());
		return dstMat;
	}
	
   public static void main( String[] args ) {
       System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
       VideoCapture cap  = new VideoCapture(0);
       cap.set(15, -8.0);
       List<Mat> hsvSep = new ArrayList<Mat>();
       Mat bgr = Imgcodecs.imread("C:\\Users\\adhoms\\Desktop\\Before.jpg");
       Mat hsv = new Mat();
       Imgproc.cvtColor(bgr, hsv, Imgproc.COLOR_RGB2HSV);
       Mat pic = new Mat(); 
       cap.read(pic);
       //pic = rotate(pic, 90);
       pic = bgr;
       Core.split(pic, hsvSep);
       //Imgcodecs.imwrite("C:\\Users\\adhoms\\Desktop\\Before.jpg", pic);
       
       //Core.split(pic, hsvSep);
      // Core.inRange(pic, new Scalar(0,0,0), new Scalar(10,10,10), pic);
       //bitwise_not(dst, dst);
       Imgcodecs.imwrite("C:\\Users\\adhoms\\Desktop\\After.jpg", hsvSep.get(0));
       cap.release();
   }
}