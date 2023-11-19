package com.commercial.gestion.utility;

public  class Utility {
    public static String padWithZeros(int number, String format) {
        // Convert the number to a formatted string with leading zeros
        String formattedNumber = String.format("%0" + format.length() + "d", number);

        // If the formatted number is longer than the specified format, return the original number as a string
        if (formattedNumber.length() > format.length()) {
            return Integer.toString(number);
        }

        return formattedNumber;
    }
}
