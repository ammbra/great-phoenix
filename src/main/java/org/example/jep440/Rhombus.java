package org.example.jep440;

public record Rhombus(Line shortd, Line shortl, Angle[] angle) implements Polygon {

	@Override
	public int area(int width, int... height) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Area is not supported for these arguments");
	}
}

