package org.identityconnectors.flatfileconnector.test.operation;

import java.util.Set;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.ConnectorObject;
import org.identityconnectors.framework.common.objects.ResultsHandler;

public class FlatFileResultHandler implements ResultsHandler {
    public FlatFileResultHandler() {
        super();
    }

    public boolean handle(ConnectorObject connectorObject) {
        Set<Attribute> attributes = connectorObject.getAttributes();
        for (Attribute attribute : attributes) {
            if (!attribute.getName().equals("__UID__") && !attribute.getName().equals("__NAME__")) {
                System.out.println("Attr name: " + attribute.getName());
                System.out.println("Attr value: " + attribute.getValue().get(0).toString());
            }
        }
        return true;
    }
}
