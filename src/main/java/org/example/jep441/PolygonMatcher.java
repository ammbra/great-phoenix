package org.example.jep441;

import org.example.jep440.*;

import java.util.Arrays;
import java.util.List;

public class PolygonMatcher {
	public static void main(String[] args) {
		Polygon polygon = new Rectangle(10, 20);
		String detail = switch (polygon) {
				case null -> "Nothing to do";
			    case Rectangle r -> String.format("Area for a rectangle is %d", r.area(r.width(), r.height()));
				case Rhombus r -> String.format("Angle of a Rhombus is %s", Arrays.toString(r.angle()));
				case Triangle t -> String.format("Perimeter of a triangle is %d", t.perimeter(t.side1(), t.side2(), t.side3()));
			};

		System.out.println(detail);

		Line shortd = new Line(new Point(0, 2), new Point(2, 5));
		Line shortl = new Line(new Point(0, 10), new Point(10, 40));
		Angle[] angles =  {Angle.ACUTE, Angle.OBTUSE};
		polygon = new Rhombus(shortd, shortl, angles);

		String detailRefined = switch (polygon) {
			case null -> "Nothing to do";
			case Rhombus r when (r.angle().length > 1) -> String.format("Angles of start Rhombus is %s", Arrays.stream(r.angle()).toList());
			case Rhombus r when (r.angle().length < 1) -> "Angles were not defined for rhombus";
			case Rhombus r -> String.format("Angles of a Rhombus is %s", Arrays.stream(r.angle()).toList());
			case Rectangle r -> String.format("Area for a rectangle is %d", r.area(r.width(), r.height()));
			case Triangle t -> String.format("Perimeter of start triangle is %d", t.perimeter(t.side1(), t.side2(), t.side3()));
		};

		System.out.println(detailRefined);

		String enumDetailRefined = switch (polygon) {
			case null -> "Nothing to do";
			case Rhombus r
					when (List.of(Angle.ACUTE, Angle.OBTUSE).contains(Arrays.stream(r.angle()).toList())) ->
					"Rhombus has ACUTE and OBTUSE angles";
			case Rhombus r
					when (Arrays.stream(r.angle()).toList().contains(Angle.RIGHT)) ->
					"I am actually a square";
			case Rhombus r -> String.format("Angles of a Rhombus are %s", Arrays.stream(r.angle()).toList());
			case Rectangle r -> String.format("Area for a rectangle is %d", r.area(r.width(), r.height()));
			case Triangle t -> String.format("Perimeter is %d", t.perimeter(t.side1(), t.side2(), t.side3()));
		};

		System.out.println(enumDetailRefined);
	}
}
