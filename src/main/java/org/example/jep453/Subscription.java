package org.example.jep453;


import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;


public record Subscription(String provider, int channelCount) {

    private static Random random = new Random();

    public static Subscription findOffer() {

        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Subscription>()) {

            var firstOffer = scope.fork(Subscription::readOffer1);
            var secondOffer = scope.fork(Subscription::readOffer2);
            var thirdOffer = scope.fork(Subscription::readOffer3);
            var fourthOffer = scope.fork(Subscription::readOffer4);

            scope.join();

            Subscription bestCableProvider = scope.result();

            System.out.println("firstOffer = " + firstOffer.state());
            System.out.println("secondOffer = " + secondOffer.state());
            System.out.println("thirdOffer = " + thirdOffer.state());
            System.out.println("fourthOffer = " + fourthOffer.state());

            return bestCableProvider;

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer1() {
        int channelCount = 23;
        try {
            Thread.sleep(random.nextInt(10, channelCount));
            return new Subscription("First provider ", channelCount);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer2() {
        int channelCount = 45;
        try {
            Thread.sleep(random.nextInt(10, channelCount));
            return new Subscription("Second provider ", channelCount );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer3() {
        int channelCount = 34;
        try {
            Thread.sleep(random.nextInt(10, channelCount));
            return new Subscription("Third provider ", channelCount );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscription readOffer4() {
        int channelCount = 50;
        try {
            Thread.sleep(random.nextInt(10, channelCount));
            return new Subscription("Fourth provider ", channelCount );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}