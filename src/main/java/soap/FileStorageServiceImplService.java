
package soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FileStorageServiceImplServiceMsg", targetNamespace = "http://soap.web/", wsdlLocation = "http://localhost:8080/RomanaChykaloFileStorageService/storageSOAP?wsdl")
public class FileStorageServiceImplService
    extends Service
{

    private final static URL FILESTORAGESERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException FILESTORAGESERVICEIMPLSERVICE_EXCEPTION;
    private final static QName FILESTORAGESERVICEIMPLSERVICE_QNAME = new QName("http://soap.web/", "FileStorageServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/RomanaChykaloFileStorageService/storageSOAP?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FILESTORAGESERVICEIMPLSERVICE_WSDL_LOCATION = url;
        FILESTORAGESERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public FileStorageServiceImplService() {
        super(__getWsdlLocation(), FILESTORAGESERVICEIMPLSERVICE_QNAME);
    }

    public FileStorageServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FILESTORAGESERVICEIMPLSERVICE_QNAME, features);
    }

    public FileStorageServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, FILESTORAGESERVICEIMPLSERVICE_QNAME);
    }

    public FileStorageServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FILESTORAGESERVICEIMPLSERVICE_QNAME, features);
    }

    public FileStorageServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FileStorageServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns rest.FileStorageService
     */
    @WebEndpoint(name = "FileStorageServiceImplPort")
    public FileStorageService getFileStorageServiceImplPort() {
        return super.getPort(new QName("http://soap.web/", "FileStorageServiceImplPort"), FileStorageService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns rest.FileStorageService
     */
    @WebEndpoint(name = "FileStorageServiceImplPort")
    public FileStorageService getFileStorageServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.web/", "FileStorageServiceImplPort"), FileStorageService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FILESTORAGESERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw FILESTORAGESERVICEIMPLSERVICE_EXCEPTION;
        }
        return FILESTORAGESERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}