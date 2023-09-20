package org.example.jep446;


import java.util.UUID;

public class Provider {

	public static ScopedValue<String> OFFER_STATE = ScopedValue.newInstance();

	public static void main(String[] args) throws Exception {

		ScopedValue<String> scopedValue = ScopedValue.newInstance();
		Runnable task = () -> System.out.println(scopedValue.isBound() ? scopedValue.get() : "UNBOUND");

		task.run();
		ScopedValue.where(scopedValue, UUID.randomUUID().toString()).run(task);
		task.run();


		Subscription offer = ScopedValue.where(OFFER_STATE, "Eligible")
						.call(Subscription::findOffer);

		System.out.println("The best subscription is " + offer);
	}

}
