package org.example.jep448;

import jdk.incubator.vector.*;

//java --enable-preview --source 21 --add-modules jdk.incubator.vector src/main/java/org/example/jep448/Operations.java
public class Operations {
	static VectorSpecies<Integer> species = IntVector.SPECIES_MAX;

	public static void main(String[] args) {
		int[] a = {4, 5};
		int[] b = {6, 7};

		System.out.println(dotProduct(a,b));
		System.out.println(dotProductVectorized(a, b));

	}

	private static int dotProduct(int[] a, int[] b) {
		int prod = 0;
		for (int i = 0; i<a.length; i++) {
			prod=prod+a[i]*b[i];
		}
		return prod;
	}

	private static int dotProductVectorized(int[] a, int[] b) {

		VectorSpecies<Integer> species = IntVector.SPECIES_MAX;

		int prod = 0;
		IntVector vectorA, vectorB;

		for(int i=0; i<a.length; i+=species.length()) {
			VectorMask<Integer> mask = species.indexInRange(i, a.length);
			vectorA = IntVector.fromArray(species, a, i, mask);
			vectorB = IntVector.fromArray(species, b, i, mask);
			prod += vectorA.mul(vectorB).reduceLanes(VectorOperators.ADD);
		}

		return prod;
	}
}

