package org.example.jep431;

import java.util.*;

public class Intersection {

	public List<Integer> extractCommonElementsOld(List<Integer> list1, List<Integer> list2) {
		if (list1.isEmpty() || list2.isEmpty()) {
			throw new UnsupportedOperationException("Please provide two not empty lists");
		}

		Set<Integer> intersection = new HashSet<>(list1);

		intersection.retainAll(list2);
        if (list1.get(0).equals(list2.get(0))) {
            intersection.add(list1.get(0));
        }
        if (list1.get(list1.size() - 1).equals(list2.get(list2.size() - 1))) {
            intersection.add(list1.get(list1.size() - 1));
        }

        return intersection.stream().toList();
	}

	public List<Integer> extractCommonElementsNew(List<Integer> list1, List<Integer> list2) {
		if (list1.isEmpty() || list2.isEmpty()) {
			throw new UnsupportedOperationException("Please provide two not empty lists");
		}
		Set<Integer> intersection = new HashSet<>(list1);

		intersection.retainAll(list2);

        if (list1.getFirst().equals(list2.getFirst())) {
            intersection.add(list1.getFirst());
        }
        if (list1.getLast().equals(list2.getLast())) {
            intersection.add(list1.getLast());
        }

        return intersection.stream().toList();
	}
}