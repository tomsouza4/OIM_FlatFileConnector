package org.identityconnectors.flatfileconnector.test.operation;

import java.util.List;
import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.Uid;

public class TestDelete extends Test {
    public TestDelete() {
        super();
    }

    private void delete() {
        //get a list of ConnectorInfo from ConnectorInfoManager
        List<ConnectorInfo> cInfos = this.getCInfoManager().getConnectorInfos();
        //iterate through the list
        for (ConnectorInfo cInfo : cInfos) {
            //get the APIconfig
            APIConfiguration apiConfig = cInfo.createDefaultAPIConfiguration();
            //set pool configurations
            setPoolConfigurations(apiConfig);
            //get the configProps i.e reference to Configuration on SPI side
            ConfigurationProperties configProps = apiConfig.getConfigurationProperties();
            //set up config props
            this.setUpConfigurationProperties(configProps);
            //get a reference to ConnectorFacadeFactory
            ConnectorFacadeFactory facadeFactory = ConnectorFacadeFactory.getInstance();
            //create the connector facade (nothing but reference to our Connector on SPI side)
            ConnectorFacade connectorFacade = facadeFactory.newInstance(apiConfig);
            //lets delete the account with ID 3
            connectorFacade.delete(ObjectClass.ACCOUNT, new Uid("3"), null);
        }
    }

    public static void main(String[] args) {
        new TestDelete().delete();
    }
}
