package org.example.jep430;
import org.json.JSONObject;
import static java.util.Collections.emptyList;

public class JSONProcessing {
	public static void main(String[] args) {
		var JSON = StringTemplate.Processor.of(
				(StringTemplate st) -> new JSONObject(st.interpolate())
		);
		Person person = new Person("Joan Doe", 29,
				new Address("1 Maple Drive", "Any"), emptyList());
		JSONObject doc = JSON. """
				{
				    "name":    "\{person.name()}",
				    "address": "\{person.address()}"
				};
				""";

		System.out.println(doc);
	}

}
