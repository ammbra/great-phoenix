package org.example.jep430;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

enum Option {
	SINGLE,
	MULTI,
	FORMAT,
	BUILDER,
	TEMPLATE;
}
public class BirthdayGreeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your birth day, month and year (format YYYY-MM-DD): ");
		LocalDate birthDate = LocalDate.parse(scanner.nextLine());
		LocalDate currentDate = LocalDate.now();

		System.out.print("Enter an option to print (SINGLE, MULTI, BUILDER or FORMAT): ");
		Option option = Option.valueOf(scanner.nextLine());

		int age = Period.between(birthDate, currentDate).getYears();

		String greeting = switch (option) {
			case SINGLE -> singleLine(firstName, lastName, age);
			case MULTI -> multiLine(firstName, lastName, birthDate, age);
			case BUILDER -> sbMerge(firstName, lastName, birthDate, age);
			case FORMAT -> getFormattedMessage(firstName, lastName, birthDate, age);
			case TEMPLATE -> singleLineWithTemplate(firstName, lastName, age);
		};

		System.out.println(greeting);
        scanner.close();
    }

	private static String getFormattedMessage(String firstName, String lastName, LocalDate birthDate, int age) {
		String message = "Hello, {0} {1}!\nYou were born on {2}/{3}/{4}.\nYou are {5} years old.";
		String formattedMessage = MessageFormat.format(message, firstName, lastName, birthDate.getMonth(),
				birthDate.getDayOfMonth(), birthDate.getYear(), age);


		return formattedMessage;
	}

	private static String sbMerge(String firstName, String lastName, LocalDate birthDate, int age) {

		String message = new StringBuilder().append("Hello, ")
				.append(firstName).append(" ").append(lastName).append("!\n")
				.append("You were born on ")
				.append(birthDate.getMonth()).append("/").append(birthDate.getDayOfMonth()).append("/")
				.append(birthDate.getYear()).append(".\n")
				.append("You are ").append(age).append(" years old.\n").toString();

		return message;
	}

	private static String multiLine(String firstName, String lastName, LocalDate birthDate, int age) {

		String message = "Hello, " +
				firstName + " " + lastName + "!\n" +
				"You were born on "
				+ birthDate.getMonth() + "/" +  birthDate.getDayOfMonth() + "/" + birthDate.getYear() + ".\n" +
				"You are " + age + " years old." ;
		return message;

	}

	private static String singleLine(String firstName, String lastName, int age) {
		String message = "Hello, " + firstName + " " + lastName + "! You are " + age + " years old.";
		return message;
	}

	private static String singleLineWithTemplate(String firstName, String lastName, int age) {
		String message = STR. "Hello, \{firstName} \{lastName}! You are \{age} years old.";
		return message;
	}

}