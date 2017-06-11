/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import org.apache.commons.validator.routines.*;

public class Validators {

    public static Boolean validateDNI(String dni) {
        String characters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int dniNumber = Integer.parseInt(dni.substring(0, 8));
        char dniLetter = dni.charAt(dni.length() - 1);

        System.out.println(dniLetter);
        System.out.println(dniNumber);
        System.out.println(characters.charAt((int) (dniNumber % 23)));
        if (dni.length() != 9) {
            return false;
        }

        return dniLetter == characters.charAt((int) (dniNumber % 23));
    }

    public static Boolean validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static Boolean validateDouble(String doubleToValidate) {
        Boolean result;

        try {
            int i = Integer.parseInt(doubleToValidate);
            result = true;
        } catch (NumberFormatException e) {
            if (doubleToValidate.matches("-?\\d+(\\.\\d+)?")) {
                double d = Double.parseDouble(doubleToValidate);
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }

    public static Boolean validateIsbn13(String isbn) {
        if (isbn == null) {
            return false;
        }

        isbn = isbn.replaceAll("-", "");

        if (isbn.length() != 13) {
            return false;
        }

        try {
            int tot = 0;
            
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }
            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }
            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
