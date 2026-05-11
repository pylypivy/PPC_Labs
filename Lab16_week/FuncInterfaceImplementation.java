package org.example;

interface DoNotGiveUp<T> {
    T execute();
}
public class FuncInterfaceImplementation {
    public static void main(String[] args) {
        // 1. TryThreeTimes implementation
        DoNotGiveUp<String> tryThreeTimes = () -> {
            for (int i = 0; i < 3; i++) {
                int randomInt = (int) (Math.random() * 100) + 1;
                if (randomInt > 50) return "You succeeded";
            }
            return "Failed";
        };

        // 2. TryForEver implementation (capped at 1000)
        DoNotGiveUp<String> tryForEver = () -> {
            for (int i = 0; i < 1000; i++) {
                if (Math.random() < 0.4) return "You succeeded";
            }
            return "Failed";
        };

        System.out.println("Result 1: " + tryThreeTimes.execute());
        System.out.println("Result 2: " + tryForEver.execute());
    }
}
