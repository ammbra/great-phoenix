package org.example.jep430;

import java.util.List;

public record Person(String name, int age, Address address, List<String> hobbies) {}
record Address(String street, String city) { }
