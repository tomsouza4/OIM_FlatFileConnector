package org.identityconnectors.flatfile.extension;

import java.util.HashMap;

public class FlatFileTransformation {
    public Object transform(HashMap hmUserDetails, HashMap hmEntitlementDetails, String sField) {
        System.out.println("Inside flat file recon transformation");
        String sFirstName = (String)hmUserDetails.get("FirstName");
        String sLastName = (String)hmUserDetails.get("LastName");
        String smail = sFirstName + "." + sLastName + "@myorg.com";
        System.out.println("Mail is" + smail);
        return smail;
    }
}
