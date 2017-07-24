package org.identityconnectors.flatfileconnector.test.operation;

import java.util.List;

import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.Uid;

public class TestUpdate extends Test {
    public TestUpdate() {
        super();
    }

    private void update() {
        //get a list of ConnectorInfo from ConnectorInfoManager
        List<ConnectorInfo> cInfos =
            this.getCInfoManager().getConnectorInfos();
        //iterate through the list
        for (ConnectorInfo cInfo : cInfos) {
            //get the APIconfig
            APIConfiguration apiConfig = cInfo.createDefaultAPIConfiguration();
            //set pool configurations
            setPoolConfigurations(apiConfig);
            //get the configProps i.e reference to Configuration on SPI side
            ConfigurationProperties configProps =
                apiConfig.getConfigurationProperties();
            //set up config props
            this.setUpConfigurationProperties(configProps);
            //get a reference to ConnectorFacadeFactory
            ConnectorFacadeFactory facadeFactory =
                ConnectorFacadeFactory.getInstance();
            //create the connector facade (nothing but reference to our Connector on SPI side)
            ConnectorFacade connectorFacade =
                facadeFactory.newInstance(apiConfig);

            //lets call the update operation on the facade... i.e update the user with account ID 1 to 10
            connectorFacade.update(ObjectClass.ACCOUNT, new Uid("1"),
                                   updateAttribute("10", "FirstName"), null);
            //lets enable account ID 1
            connectorFacade.update(ObjectClass.ACCOUNT, new Uid("1"),
                                   enable("Enable", "true"), null);
            //lets disable account ID 2
            connectorFacade.update(ObjectClass.ACCOUNT, new Uid("2"),
                                   enable("Enable", "false"), null);
        }
    }

    public static void main(String[] args) {
        new TestUpdate().update();
    }
}
