package org.example.jep440;

public sealed interface Polygon permits Shape, Rectangle, Rhombus {
	int area(int width, int... height) throws UnsupportedOperationException;
}