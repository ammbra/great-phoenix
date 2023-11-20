package org.example.jep441;

import org.example.jep440.*;

import java.util.Arrays;

public class PolygonMatcher {
	public static void main(String[] args) {
		Configuration setup = Boundary.DIAGONAL;

		Polygon polygon = switch (setup) {
			case Boundary.DIAGONAL -> {
				Line shortd = new Line(new Point(0, 2), new Point(2, 5));
				Line shortl = new Line(new Point(0, 10), new Point(10, 40));
				Angle[] angles =  {Angle.ACUTE, Angle.OBTUSE};
				yield  new Rhombus(shortd, shortl, angles);
			};
			case Measure.AREA -> new Rectangle(10, 5);
			case Measure.PERIMETER -> new Triangle(1, 2, 3);
		};

		String detail = switch (polygon) {
			case null -> "Nothing to do";
			case Rectangle r -> String.format("Area for a rectangle is %d", r.area(r.width(), r.height()));
			case Rhombus r -> String.format("Angles of a Rhombus are %s", Arrays.toString(r.angles()));
			case Triangle t -> String.format("Perimeter of a triangle is %d", t.perimeter(t.side1(), t.side2(), t.side3()));
		};

		System.out.println(detail);


		String detailRefined = switch (polygon) {
			case null -> "Nothing to do";
			case Rhombus r
					when (r.angles().length > 1) -> String.format("Angles of Rhombus are %s", Arrays.stream(r.angles()).toList());
			case Rhombus r
					when (r.angles().length < 1) -> "Angles were not defined for rhombus";
			case Rhombus r -> String.format("Angles of a Rhombus are %s", Arrays.stream(r.angles()).toList());
			case Rectangle r -> String.format("Area for a rectangle is %d", r.area(r.width(), r.height()));
			case Triangle t -> String.format("Perimeter of start triangle is %d", t.perimeter(t.side1(), t.side2(), t.side3()));
		};

		System.out.println(detailRefined);

	}
}


