package org.example.jep443;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static java.util.stream.Collectors.toMap;


public class UnnamedExamples {

	public static void main(String[] args) {
		List<String> persons = List.of("Jane", "Joe", "Jennifer");
		int noHobbies = 0;
		for (String _: persons) {
			if (noHobbies < persons.size())
				noHobbies++;
		}

		try(FileWriter _ = new FileWriter("example.txt")) {
			System.out.println("File opened successfully");
		} catch (IOException e) {
			throw new RuntimeException("Could not open file!");
		}

		List<String> fruits = List.of("apple", "banana", "orange", "");
		fruits.stream()
				.filter(String::isEmpty)
				.collect(toMap(String::toUpperCase, _ -> "EMPTY"));
	}
}

