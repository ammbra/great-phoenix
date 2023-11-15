package org.example.jep431;

import java.util.*;

public class Intersection {

	public List<Integer> extractCommonElementsOld(List<Integer> firstSeries, List<Integer> secondSeries) {
		if (firstSeries.isEmpty() || secondSeries.isEmpty()) {
			throw new UnsupportedOperationException("Please provide two not empty lists");
		}

		Set<Integer> intersection = new HashSet<>(firstSeries);
		intersection.retainAll(secondSeries);

        if (firstSeries.get(0).equals(secondSeries.get(0))) {
            intersection.add(firstSeries.get(0));
        }
        if (firstSeries.get(firstSeries.size() - 1).equals(secondSeries.get(secondSeries.size() - 1))) {
            intersection.add(firstSeries.get(firstSeries.size() - 1));
        }

        return intersection.stream().toList();
	}

	public List<Integer> extractCommonElementsNew(List<Integer> firstSeries, List<Integer> secondSeries) {
		if (firstSeries.isEmpty() || secondSeries.isEmpty()) {
			throw new UnsupportedOperationException("Please provide two not empty lists");
		}

		Set<Integer> intersection = new HashSet<>(firstSeries);
		intersection.retainAll(secondSeries);

        if (firstSeries.getFirst().equals(secondSeries.getFirst())) {
            intersection.add(firstSeries.getFirst());
        }
        if (firstSeries.getLast().equals(secondSeries.getLast())) {
            intersection.add(firstSeries.getLast());
        }

        return intersection.stream().toList();
	}
}