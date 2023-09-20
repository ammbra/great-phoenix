package org.example.jep440;

public sealed interface Shape extends Polygon permits Triangle {
	int perimeter(int side1, int side2, int side3);
}
