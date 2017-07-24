package org.identityconnectors.flatfile.extension;

import java.util.HashMap;

public class FlatFileValidator {
    public boolean validate(HashMap hmUserDetails, HashMap hmEntitlementDetails, String sField) {
        boolean output = false;
        System.out.println("Inside validate method of FlatFileValidator...");
        String smail = (String)hmUserDetails.get("Email");
        for (int i = 0; i < smail.length(); i++) {
            if (smail.charAt(i) == '@') {
                output = true;
                break;
            }
        }
        System.out.println("Value of output is-->" + output);
        return output;
    }
}
