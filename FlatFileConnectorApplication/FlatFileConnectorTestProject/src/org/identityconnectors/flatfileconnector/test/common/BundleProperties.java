package org.identityconnectors.flatfileconnector.test.common;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.identityconnectors.common.IOUtil;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
public class BundleProperties {
final String dirLocation = "/app/software/Connector_Server_111200/connector_server_java-1.4.0/bundles";
final String jarName = "org.identityconnectors.flatfile-1.0";
final String jarRelativeUrl = jarName + ".jar";
final String connectorClassName = "org.identityconnectors.flatfileconnector.FlatFileConnector";
File dir = new File(dirLocation);
URL url;
public BundleProperties() {
try {
url = IOUtil.makeURL(dir, jarRelativeUrl);
} catch (final IOException ioe) {
System.out.println("URL not framed properly");
ioe.printStackTrace();
throw ConnectorException.wrap(ioe);
}
}
public String getDirLocation() {
return dirLocation;
}
public String getJarName() {
return jarName;
}
public String getJarRelativeUrl() {
return jarRelativeUrl;
}
public String getConnectorClassName() {
return connectorClassName;
}
public File getDir() {
return dir;
}
public URL getUrl() {
return url;

}
}