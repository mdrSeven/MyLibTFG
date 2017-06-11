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
}
