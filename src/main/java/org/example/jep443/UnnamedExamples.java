package org.example.jep443;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
			e.printStackTrace();
		}

		List<String> fruits = List.of("apple", "banana", "orange", "");
		Map<String, String> upperCaseMap = fruits.stream()
				.filter(f  -> f.isEmpty())
				.collect(Collectors.toMap(String::toUpperCase, _ -> "EMPTY"));
	}
}

