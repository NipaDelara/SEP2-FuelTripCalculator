package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class FuelController {

    // UI Labels
    @FXML private Label lblTitle;
    @FXML private Label lblDistance;
    @FXML private Label lblConsumption;
    @FXML private Label lblPrice;
    @FXML private Label lblResult;

    // Input fields
    @FXML private TextField txtDistance;
    @FXML private TextField txtConsumption;
    @FXML private TextField txtPrice;

    // Calculate button
    @FXML private Button btnCalculate;

    // Localization
    private ResourceBundle bundle;

    // Calculation state
    private boolean hasCalculated = false;
    private boolean hasError = false;

    private double totalFuel;
    private double totalCost;

    // Default language: English
    @FXML
    public void initialize() {
        setLanguage(Locale.US);
    }

    // Update UI language
    private void setLanguage(Locale locale) {

        bundle = ResourceBundle.getBundle("messages", locale);

        lblTitle.setText(bundle.getString("title"));
        lblDistance.setText(bundle.getString("distance.label"));
        lblConsumption.setText(bundle.getString("consumption.label"));
        lblPrice.setText(bundle.getString("price.label"));
        btnCalculate.setText(bundle.getString("calculate.button"));

        // Update existing result when language changes
        if (hasCalculated) {
            showResult();
        } else if (hasError) {
            lblResult.setText(bundle.getString("invalid.input"));
        } else {
            lblResult.setText("");
        }
    }

    // English
    @FXML
    private void switchEnglish() {
        setLanguage(Locale.US);
    }

    // French
    @FXML
    private void switchFrench() {
        setLanguage(Locale.FRANCE);
    }

    // Japanese
    @FXML
    private void switchJapanese() {
        setLanguage(Locale.JAPAN);
    }

    // Persian
    @FXML
    private void switchPersian() {
        setLanguage(Locale.forLanguageTag("fa-IR"));
    }

    // Calculate fuel and trip cost
    @FXML
    private void calculate() {

        try {
            double distance =
                    Double.parseDouble(txtDistance.getText().trim());

            double consumption =
                    Double.parseDouble(txtConsumption.getText().trim());

            double price =
                    Double.parseDouble(txtPrice.getText().trim());

            // Validate input
            if (!Double.isFinite(distance)
                    || !Double.isFinite(consumption)
                    || !Double.isFinite(price)
                    || distance <= 0
                    || consumption <= 0
                    || price <= 0) {

                throw new IllegalArgumentException("Invalid input");
            }

            // Use FuelCalculator class
            totalFuel = FuelCalculator.calculateFuel(
                    distance, consumption
            );

            totalCost = FuelCalculator.calculateCost(
                    totalFuel, price
            );

            // Validate calculation results
            if (!Double.isFinite(totalFuel)
                    || !Double.isFinite(totalCost)) {

                throw new IllegalArgumentException(
                        "Invalid calculation result"
                );
            }

            hasCalculated = true;
            hasError = false;

            showResult();

        } catch (IllegalArgumentException e) {

            hasCalculated = false;
            hasError = true;

            lblResult.setText(
                    bundle.getString("invalid.input")
            );
        }
    }

    // Display translated result
    private void showResult() {

        String pattern = bundle.getString("result.label");

        String fuel = String.format(
                Locale.US, "%.2f", totalFuel
        );

        String cost = String.format(
                Locale.US, "%.2f", totalCost
        );

        lblResult.setText(
                MessageFormat.format(pattern, fuel, cost)
        );
    }
}
