package org.example.jep440;

public class Matchmaking {
	public static void main(String[] args) {
		Polygon rectangle = new Rectangle(10, 5);
		sum(rectangle);

		Line shortd = new Line(new Point(0, 2), new Point(2, 5));
		Line longd = new Line(new Point(0, 10), new Point(10, 40));
		Angle[] angles =  {Angle.ACUTE, Angle.OBTUSE};
		Polygon rhombus = new Rhombus(shortd, longd, angles);
		inspect(rhombus);

		Pair<Polygon> polygonPair = new Pair<>(new Rectangle(10, 5), new Triangle(1, 2, 3));

		switch (polygonPair) {
			case Pair<Polygon>(Triangle t, Rhombus r) -> System.out.println("Pair a triangle and rhombus");
			case Pair<Polygon>(Triangle t, Rectangle r) -> System.out.println("Pair a triangle and rectangle");
			case Pair<Polygon>(Triangle t, Triangle tr) -> System.out.println("Pair 2 triangles");
			case Pair<Polygon>(Rectangle r, Triangle t) -> System.out.println("Pair a rectangle and triangle");
			case Pair<Polygon>(Rectangle r, Rhombus rh) -> System.out.println("Pair a rectangle and rhombus");
			case Pair<Polygon>(Rectangle r, Rectangle rc) -> System.out.println("Pair 2 rectangles");
			case Pair<Polygon>(Rhombus rh, Triangle t) -> System.out.println("Pair a rhombus and triangle");
			case Pair<Polygon>(Rhombus rh, Rectangle r) -> System.out.println("Pair a rhombus and rectangle");
			case Pair<Polygon>(Rhombus rh, Rhombus rho) -> System.out.println("Pair 2 rhombuses");
		}

	}

	private static void sum(Polygon polygon) {
		// As of Java 16
		if (polygon instanceof Rectangle r) {
			int x = r.width();
			int y = r.height();
			System.out.println(x+y);
		}

		// As of Java 21
		if (polygon instanceof Rectangle(int x, int y)) {
			System.out.println(x+y);
		}
	}

	private static void inspect(Polygon polygon) {
		// As of Java 21
		if (polygon instanceof Rhombus(Line shortd, Line longd, Angle[] a)) {
			System.out.println("Starting point of the long diagonal" + longd.start());
		}

		// As of Java 21
		if (polygon instanceof Rhombus(Line(Point start1, Point end1), Line(Point start2, Point end2), Angle[] a)) {
			System.out.println("Starting point of the short diagonal" + start1);
		}

		// As of Java 21
		if (polygon instanceof Rhombus(Line(Point start, Point end), var d, var a)) {
			System.out.println("Ending point of the short diagonal" + start);
		}
	}

}
