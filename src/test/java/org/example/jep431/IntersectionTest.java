package org.example.jep431;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {

	private Intersection intersection;

	@BeforeEach
	void setUp() {
		intersection = new Intersection();
	}

	@Test
	void extractCommonElementsOld() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(10);
		list1.add(22);
		list1.add(31);
		list1.add(42);


		List<Integer> list2 = new ArrayList<>();
		list2.add(36);
		list2.add(45);
		list2.add(22);
		list2.add(42);

		List<Integer> elements = intersection.extractCommonElementsOld(list1, list2);
		assertEquals(2, elements.size());
		assertEquals(22, elements.getFirst());
		assertEquals(42, elements.getLast());

	}

	@Test
	void extractCommonElementsOldWithEmptyList() {
		List<Integer> list2 = new ArrayList<>();
		list2.add(36);
		list2.add(45);
		list2.add(22);
		list2.add(42);
	    assertThrows(UnsupportedOperationException.class, () -> intersection.extractCommonElementsOld(Collections.EMPTY_LIST, list2));
	}

	@Test
	void extractCommonElementsNewWithEmptyList() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(36);
		list1.add(45);
		list1.add(22);
		list1.add(42);
		assertThrows(UnsupportedOperationException.class, () -> intersection.extractCommonElementsOld(list1, Collections.EMPTY_LIST));
	}

	@Test
	void extractCommonElementsNew() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(10);
		list1.add(22);
		list1.add(31);
		list1.add(42);


		List<Integer> list2 = new ArrayList<>();
		list2.add(36);
		list2.add(45);
		list2.add(22);
		list2.add(42);

		List<Integer> elements = intersection.extractCommonElementsNew(list1, list2);
		assertEquals(2, elements.size());
		assertEquals(22, elements.getFirst());
		assertEquals(42, elements.getLast());

	}
}