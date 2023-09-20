package org.example.jep430;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.StringTemplate.STR;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonConverterTest {

	@Test
	public void testJsonParsing() {
		// Sample formatted tabbed multiline JSON string
		String json = "{\n" +
				"    \"name\": \"John\",\n" +
				"    \"age\": 30,\n" +
				"    \"address\": {\n" +
				"        \"street\": \"123 Main St\",\n" +
				"        \"city\": \"New York\"\n" +
				"    },\n" +
				"    \"hobbies\": [\n" +
				"        \"Reading\",\n" +
				"        \"Gardening\"\n" +
				"    ]\n" +
				"}";

		JsonConverter converter = new JsonConverter();
		Person person = converter.convertToEntityAttribute(json);

		assertNotNull(person);
	}

	@Test
	public void testJsonParsingTextBlock() {
		Address address = new Address("123 Main St", "New York");
		Person person = new Person("John", 30, address, List.of("Reading", "Gardening") );
		// Sample formatted tabbed multiline JSON string
		String json = """
				{
				    "name": "%s",
				    "age": "%s",
				    "address": {
				        "street": "%s",
				        "city": "%s"
				    },
				    "hobbies": [
				        "%s",
				        "%s"
				    ]
				}
				""";
		String formatted = json.formatted(person.name(), person.age(),
				person.address().street(), person.address().city() ,
				person.hobbies().getFirst(), person.hobbies().getLast());

		JsonConverter converter = new JsonConverter();
		Person result = converter.convertToEntityAttribute(formatted);

		assertNotNull(result);
	}

	@Test
	public void testJsonParsingWithSTR() {
		Address address = new Address("123 Main St", "New York");
		Person person = new Person("John", 30, address, List.of("Reading", "Gardening") );

		String jsonTemplate = STR."""
        		{
				    "name": "\{person.name()}",
				    "age": "\{person.age()}",
				    "address": {
				        "street": "\{person.address().street()}",
				        "city": "\{person.address().city()}"
				    },
				    "hobbies": ["\{person.hobbies().getFirst()}", "\{person.hobbies().getLast()}"]
				}
				""";
		JsonConverter converter = new JsonConverter();
		Person result = converter.convertToEntityAttribute(jsonTemplate);

		assertNotNull(result);

	}
}