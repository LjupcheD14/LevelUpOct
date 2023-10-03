package com.example.springbackend.controller;

import com.example.springbackend.model.FormData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
public class ValidationController {

    @PostMapping("/dateSubmit")
    public ResponseEntity<String> dateSubmit(@RequestBody FormData formData) {
        String dateValue = formData.getDate();
        return validateExpiryDate(dateValue);
    }


    @PostMapping("/cvvSubmit")
    public ResponseEntity<String> cvvSubmit(@RequestBody FormData formData) {
        System.out.println(formData);

        String cvvValue = formData.getCvv();
        String cardNumberValue = formData.getCardNumber();

        return validateCVV(cvvValue, cardNumberValue);
    }

    @PostMapping("/cardNumberSubmit")
    public ResponseEntity<String> cardNumberSubmit(@RequestBody FormData formData) {
        String cardNumberValue = formData.getCardNumber();
        return validateCardNumberLength(cardNumberValue);
    }

    @PostMapping("/luhnSubmit")
    public ResponseEntity<String> luhnSubmit(@RequestBody FormData formData) {
        String cardNumberValue = formData.getCardNumber();
        return validateCardNumberLuhn(cardNumberValue);
    }

    public ResponseEntity<String> validateExpiryDate(String dateValue) {
        if (dateValue == null || dateValue.isEmpty()) {
            return ResponseEntity.ok("The expiry date must be provided.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date expiryDate;

        try {
            expiryDate = sdf.parse(dateValue);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid date format. Use yyyy-MM-dd.");
        }

        if (expiryDate.compareTo(currentDate) <= 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid expiry date. The expiry date must be after the current date.");
        }

        // Expiry date is valid
        return ResponseEntity.status(HttpStatus.OK).body("Valid date");
    }

    public ResponseEntity<String> validateCVV(String cvvValue, String cardNumberValue) {
        if (cvvValue.isEmpty()) {
            System.out.println("The CVV must be provided.");
            return ResponseEntity.status(HttpStatus.OK).body("The CVV must be provided.");
        }

        boolean isAmerican = cardNumberValue.startsWith("34") || cardNumberValue.startsWith("37");
        int cvvLength = isAmerican ? 4 : 3;

        if (cvvValue.length() != cvvLength) {
            if (isAmerican) {
                System.out.println("Invalid CVV length. CVV for American Express cards must be 4 digits long.");
                return ResponseEntity.status(HttpStatus.OK).body("Invalid CVV length. CVV for American Express cards must be 4 digits long.");
            } else {
                System.out.println("Invalid CVV length. CVV for non-American Express cards must be 3 digits long.");
                return ResponseEntity.status(HttpStatus.OK).body("Invalid CVV length. CVV for non-American Express cards must be 3 digits long.");
            }
        }

        // CVV length is valid
        System.out.println("Valid CVV");
        return ResponseEntity.status(HttpStatus.OK).body("Valid CVV");
    }


    public ResponseEntity<String> validateCardNumberLength(String cardNumberValue) {
        if (cardNumberValue == null || cardNumberValue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("The card number must be provided.");
        }

        int cardNumberLength = cardNumberValue.length();

        if (cardNumberLength < 16 || cardNumberLength > 19) {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid card number length. The card number must be between 16 and 19 digits long.");
        }

        // Card number length is valid
        return ResponseEntity.status(HttpStatus.OK).body("Valid card number length");
    }

    public ResponseEntity<String> validateCardNumberLuhn(String cardNumberValue) {
        // Convert the card number string to an array of digits
        char[] cardNumberChars = cardNumberValue.toCharArray();
        int[] cardNumberDigits = new int[cardNumberChars.length];

        for (int i = 0; i < cardNumberChars.length; i++) {
            cardNumberDigits[i] = Character.getNumericValue(cardNumberChars[i]);
        }

        // Reverse the array of digits
        for (int i = 0; i < cardNumberDigits.length / 2; i++) {
            int temp = cardNumberDigits[i];
            cardNumberDigits[i] = cardNumberDigits[cardNumberDigits.length - i - 1];
            cardNumberDigits[cardNumberDigits.length - i - 1] = temp;
        }

        // Apply the Luhn's algorithm to validate the card number
        int sum = 0;
        for (int i = 0; i < cardNumberDigits.length; i++) {
            int digit = cardNumberDigits[i];

            if (i % 2 == 1) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
        }

        if (sum == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(" ");
        }

        // Check if the sum is divisible by 10
        if (sum % 10 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Valid card number according to the Luhn algorithm.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid card number according to the Luhn algorithm. The last digit is incorrect.");
        }
    }
}