package org.example.jep440;

public record Rhombus(Line shortd, Line longd, Angle[] angles) implements Polygon {

	@Override
	public int area(int width, int... height) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Area is not supported for these arguments");
	}

	@Override
	public Configuration setup() {
		return Boundary.DIAGONAL;
	}
}

