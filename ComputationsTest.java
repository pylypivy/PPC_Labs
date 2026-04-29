package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComputationsTest {
    @Test
    void fiboNegativeTest(){
        assertThrows(IllegalArgumentException.class,() -> Computations.fibonacci(-5),
                "n cannot be negative!");
    }
    @Test
    void fiboZeroTest(){
        assertEquals(0, Computations.fibonacci(0), "Fibonacci 0 should return 0");
    }
    @Test
    void fiboOneTest(){
        assertEquals(1, Computations.fibonacci(1), "Fibonacci 1 should return 1");
    }
    @Test
    void fiboTest(){
        assertEquals(8, Computations.fibonacci(6), "Fibonacci 6 should return 8");
    }
    @Test
    void testNonPrimes() {
        assertAll("Non-prime edge cases",
                () -> assertFalse(Computations.isPrime(-6), "-6 should not be prime"),
                () -> assertFalse(Computations.isPrime(0), "0 should not be prime"),
                () -> assertFalse(Computations.isPrime(1), "1 should not be prime")
        );
    }
    @Test
    void isPrimeTest(){
        assertAll(() -> assertFalse(Computations.isPrime(8),"8 is not a prime number"),
                  () -> assertTrue(Computations.isPrime(13), "13 is a prime number")
        );
    }
    @Test
    void isEvenTest(){
        assertAll(
                () -> assertFalse(Computations.isEven(7), "7 is not an even number"),
                () -> assertTrue(Computations.isEven(4), "4 is an even number")
        );
    }
    @Test
    void isOddTest(){
        assertAll(
                () -> assertTrue(Computations.isOdd(7), "7 is an odd number"),
                () -> assertFalse(Computations.isOdd(4), "4 is not an odd number")
        );
    }
    @Test
    void toCelsiusTest(){
        assertAll(
                () -> assertEquals(0.0,Computations.toCelsius(32.0), "32.0 F should be 0.0 C"),
                () -> assertEquals(100.0, Computations.toCelsius(212.0),"212.0 F should be 100.0 C" )
        );
    }
    @Test
    void toFahrenheitTest(){
        assertAll(
                () -> assertEquals(32.0,Computations.toFahrenheit(0.0), "0.0 C should be 32.0 F"),
                () -> assertEquals(212.0, Computations.toFahrenheit(100.0),"100.0 C should be 212.0 F" )
        );
    }
}
