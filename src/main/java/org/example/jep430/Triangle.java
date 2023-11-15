package org.example.jep430;

import static java.util.FormatProcessor.FMT;

public record Triangle(String name, double base, double height) {
	double hypotenuse() {
		return Math.sqrt((base*base)+(height*height));
	}

	public static void main(String[] args) {

		Triangle[] reg = new Triangle[]{
				new Triangle("One", 15.2, 14.4),
				new Triangle("Two", 8.6, 5.2)
		};
		String table = FMT. """
				Description     Width    Height     Hypotenuse
				%-12s\{reg[0].name}  %7.2f\{reg[0].base}  %7.2f\{reg[0].height}     %7.2f\{reg[0].hypotenuse()}
				%-12s\{reg[1].name}  %7.2f\{reg[1].base}  %7.2f\{reg[1].height}     %7.2f\{reg[1].hypotenuse()}
				\{" ".repeat(28)} Total %7.2f\{reg[0].hypotenuse() + reg[1].hypotenuse()}
				""";


		System.out.println(table);

	}
}


