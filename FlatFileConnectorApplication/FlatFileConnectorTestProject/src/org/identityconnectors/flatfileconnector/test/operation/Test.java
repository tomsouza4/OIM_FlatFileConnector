package org.identityconnectors.flatfileconnector.test.operation;

import java.io.File;

import java.util.HashSet;
import java.util.Set;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.flatfileconnector.test.common.BundleProperties;
import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConnectorInfoManager;
import org.identityconnectors.framework.api.ConnectorInfoManagerFactory;
import org.identityconnectors.framework.api.RemoteFrameworkConnectionInfo;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;

public abstract class Test {
    ConnectorInfoManager cInfoManager = null;

    public Test() {
        this.setupConnectorServer();
    }

    public void setupConnectorServer() {
        ConnectorInfoManagerFactory cInfoManagerFactory =
            ConnectorInfoManagerFactory.getInstance();
        //Connector server information
        String connectorServerhost = "localhost";
        int connectorServerPort = 8759;
        GuardedString connectorServerKey =
            new GuardedString("12345".toCharArray());
        RemoteFrameworkConnectionInfo remoteConnectionInfo =
            new RemoteFrameworkConnectionInfo(connectorServerhost,
                                              connectorServerPort,
                                              connectorServerKey, false, null,
                                              0);
        cInfoManager = cInfoManagerFactory.getRemoteManager(remoteConnectionInfo);
        if (cInfoManager == null) {
            System.out.println("this should never happen!!");
            return;
        }
    }
    /* public void setupConnectorServer(){
BundleProperties bProps = new BundleProperties();
ConnectorInfoManagerFactory cInfoManagerFactory = ConnectorInfoManagerFactory.getInstance();
ConnectorInfoManager cInfoManager = cInfoManagerFactory.getLocalManager(bProps.getUrl());
} */

    public ConnectorInfoManager getCInfoManager() {
        return this.cInfoManager;
    }

    public void setUpConfigurationProperties(ConfigurationProperties configProps) {
        //now set values for configProps on SPI side
        configProps.setPropertyValue("targetFile", new File("/app/connector_files/output_1.txt"));
        configProps.setPropertyValue("uniqueAttribute", "AccountId");
        configProps.setPropertyValue("hostName", "identity.oracleads.com");
        configProps.setPropertyValue("userName", "oracle");
        configProps.setPropertyValue("password", new GuardedString("Oracle123".toCharArray()));
        configProps.setPropertyValue("lookupReconFile", new File("/app/connector_files/roles.txt"));
        configProps.setPropertyValue("customScriptForProvisioning", new File("/app/connector_files/createUser.sh"));
        configProps.setPropertyValue("customScriptForRecon", new File("/app/connector_files/getAllUsers.sh"));
    }

    public void setPoolConfigurations(APIConfiguration apiConfig) {
        apiConfig.getConnectorPoolConfiguration().setMaxIdle(10);
        apiConfig.getConnectorPoolConfiguration().setMaxObjects(10);
        apiConfig.getConnectorPoolConfiguration().setMaxWait(150 * 1000);
        apiConfig.getConnectorPoolConfiguration().setMinEvictableIdleTimeMillis(120 * 1000);
        apiConfig.getConnectorPoolConfiguration().setMinIdle(1);
    }

    public Set<Attribute> updateAttribute(String accountID,
                                          String updateField) {
        Set<Attribute> attributes = new HashSet<Attribute>();
        //Attribute id = AttributeBuilder.build("AccountId", accountID);
        Attribute updatedAttr =
            AttributeBuilder.build(updateField, updateField + accountID);
        //attributes.add(id);
        attributes.add(updatedAttr);
        return attributes;
    }

    public Set<Attribute> enable(String updateField, String flag) {
        Set<Attribute> attributes = new HashSet<Attribute>();
        //Attribute id = AttributeBuilder.build("AccountId", accountID);
        Attribute updatedAttr = AttributeBuilder.build(updateField, flag);
        //attributes.add(id);
        attributes.add(updatedAttr);
        return attributes;
    }
    //utility method to create the attributes.. these will be provided from OIM

    /*public Set<Attribute> createAttributes(String accountID) {
        Set<Attribute> attributes = new HashSet<Attribute>();
        Attribute id = AttributeBuilder.build("AccountId", accountID);
        Attribute firstName = AttributeBuilder.build("FirstName", "Shekar");
        Attribute lastName =
            AttributeBuilder.build("lastName", "Nagaraja" + accountID);
        Attribute email =
            AttributeBuilder.build("email", "Shekar.Nagaraja@gmail.com");
        attributes.add(id);
        attributes.add(firstName);
        attributes.add(lastName);
        attributes.add(email);
        return attributes;
    }*/
    public Set<Attribute> createAttributes(String accountID){
    Set<Attribute> attributes = new HashSet<Attribute>();
        Attribute id = AttributeBuilder.build("AccountId", accountID);
        Attribute firstName = AttributeBuilder.build("FirstName", "Robert");
        Attribute lastName = AttributeBuilder.build("lastName", "De Niro" + accountID);
        Attribute email = AttributeBuilder.build("email", "Robert.Deniro@gmail.com");
        attributes.add(id);
        attributes.add(firstName);
        attributes.add(lastName);
        attributes.add(email);
        return attributes;
    }
}
