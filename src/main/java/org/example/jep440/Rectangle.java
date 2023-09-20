package org.example.jep440;

public record Rectangle(int width, int height) implements Polygon {

	@Override
	public int area(int width, int... height) {

		return this.width* this.height;
	}
}
