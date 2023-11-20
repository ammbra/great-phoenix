package org.example.jep440;

public record Triangle(int side1, int side2, int side3) implements Shape {

	@Override
	public int perimeter(int side1, int side2, int side3) {
		return side1+side2+side3;
	}

	@Override
	public Configuration setup() {
		return Measure.PERIMETER;
	}

	@Override
	public int area(int width, int... height) {
		throw new UnsupportedOperationException("Area is not supported for these arguments");
	}
}
