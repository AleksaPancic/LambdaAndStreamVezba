package aleksa;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;


public class MainClass {
	
	private static final Logger logger = 
			Logger.getLogger(MainClass.class.getName());
	
	public static Boolean IorIII(Point p) {
		if(p.getX() > 0 && p.getY() > 0) return true;
		if(p.getX() < 0 && p.getY() < 0) return true;
		return false;
	}
	public static Boolean IIorIV(Point p) {
		if(p.getX() > 0 && p.getY() < 0) return true;
		if(p.getX() < 0 && p.getY() > 0) return true;
		return false;
	}
	public static Boolean Osa(Point p) {
		if(p.getX() == 0 || p.getY() == 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Handler handler1 = new ConsoleHandler();
		
		logger.addHandler(handler1);
		
		logger.setUseParentHandlers(false); //iskljucuje parent handler
		handler1.setFormatter(new MyFormatter());
		Distance EuclidDist = (int x1, int x2, int y1, int y2) -> {
			return (int) Math.sqrt((x1 + x2) * (x1 + x2) - (y1 + y2) * (y1 + y2));
		};
		
		Distance ManhattanDist = (int x1, int x2, int y1, int y2) -> {
			return Math.abs(x1 - x2) + Math.abs(y1 - y2);
		};
		
		ArrayList<Point> niz = new ArrayList<Point>();
		Point p1 = new Point(40,30);
		Point p2 = new Point(-2,3);
		Point p3 = new Point(-4,5);
		Point p4 = new Point(10,40);
		Point p5 = new Point(0, 10);
		niz.add(p1);
		niz.add(p2);
		niz.add(p3);
		niz.add(p4);
		niz.add(p5);
		
		niz.parallelStream()
		.filter(MainClass::IorIII)
		.forEach(v -> System.out.println("Euck" + EuclidDist.calc(v.getX(), v.getY(), 0, 0)));
		
		niz.parallelStream()
		.filter(MainClass::IIorIV)
		.forEach(v ->  System.out.println("Manthat" + ManhattanDist.calc(v.getX(), v.getY(), 0, 0)));
		
		niz.parallelStream()
		.filter(MainClass::Osa)
		.forEach(v -> logger.info("Na koordinatnoj osi: (" + v.getX() + "," + v.getY() + ")"));
	}

}
