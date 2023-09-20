package org.example.jep453;


public class Provider {

    public static void main(String[] args) {
        Subscription cableProvider = Subscription.findOffer();
        System.out.println("The fastest subscription is from " + cableProvider);
    }
}
