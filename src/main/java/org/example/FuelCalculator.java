
package org.example;

public class FuelCalculator {

    public static double calculateFuel(
            double distance, double consumption) {

        if (!Double.isFinite(distance)
                || !Double.isFinite(consumption)
                || distance <= 0
                || consumption <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        return (distance * consumption) / 100;
    }

    public static double calculateCost(
            double fuel, double price) {

        if (!Double.isFinite(fuel)
                || !Double.isFinite(price)
                || fuel <= 0
                || price <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        return fuel * price;
    }
}
