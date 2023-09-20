package org.example.jep446;


import java.util.Collection;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.StructuredTaskScope;

import static org.example.jep446.Provider.OFFER_STATE;


public record Subscription(String provider, int channelCount) {

    private static final Random random = new Random();

    public Subscription {
        if (!OFFER_STATE.isBound()) {
            throw new IllegalStateException("The offer state is not bound");
        } else if (!OFFER_STATE.get().equals("Eligible")) {
            throw new IllegalStateException("Offer state is " + OFFER_STATE.get());
        }
    }


    public static class SubscriptionScope extends StructuredTaskScope<Subscription> {

        private final Collection<Subscription> cableProviders = new ConcurrentLinkedQueue<>();

        @Override
        protected void handleComplete(Subtask<? extends Subscription> subtask) {
            switch (subtask.state()) {
                case UNAVAILABLE -> throw new IllegalStateException("Subscription details pending...");
                case SUCCESS -> this.cableProviders.add(subtask.get());
                case FAILED -> subtask.exception();
            }
        }

        public Subscription findOffer() {
            return this.cableProviders.stream()
                    .min(Comparator.comparing(Subscription::channelCount))
                    .orElseThrow(IllegalStateException::new);
        }
    }

    public static Subscription findOffer() {

        try (var scope = new SubscriptionScope()) {

            scope.fork(Subscription::readOffer1);
            scope.fork(Subscription::readOffer2);
            scope.fork(Subscription::readOffer3);
            scope.fork(Subscription::readOffer4);

            scope.join();

            return scope.findOffer();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer1() {
        int channelCount = random.nextInt(10, 23);
        try {
            Thread.sleep(channelCount);
            return new Subscription("First provider ", channelCount);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer2() {
        int channelCount = random.nextInt(10, 45);
        try {
            Thread.sleep(channelCount);
            return new Subscription("Second provider ", channelCount );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer3() {
        int channelCount = random.nextInt(10, 34);
        try {
            Thread.sleep(channelCount);
            return new Subscription("Third Provider", channelCount );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer4() {
        int channelCount = random.nextInt(10, 50);
        try {
            Thread.sleep(channelCount);
            return new Subscription("Fourth Provider", channelCount );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}