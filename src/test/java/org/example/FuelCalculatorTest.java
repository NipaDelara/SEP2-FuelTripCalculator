
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuelCalculatorTest {

    @Test
    void testFuelCalculation() {
        double result =
                FuelCalculator.calculateFuel(180, 6.5);

        assertEquals(11.7, result, 0.001);
    }

    @Test
    void testCostCalculation() {
        double result =
                FuelCalculator.calculateCost(5.4, 3.33);

        assertEquals(17.982, result, 0.001);
    }

    @Test
    void testNegativeDistance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> FuelCalculator.calculateFuel(-10, 6.5)
        );
    }

    @Test
    void testZeroPrice() {
        assertThrows(
                IllegalArgumentException.class,
                () -> FuelCalculator.calculateCost(5.4, 0)
        );
    }
}
