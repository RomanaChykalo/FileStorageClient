import org.apache.log4j.Logger;
import soap.FileStorageService;
import soap.FileStorageServiceImplService;

public class ServiceFactory {
    private static Logger LOGGER = Logger.getLogger(ServiceFactory.class);

    public static FileStorageService getFileStorageService(ServiceType serviceType) {
        FileStorageService service = null;
        if (serviceType.equals(ServiceType.REST)) {
            LOGGER.info("Creating REST service client");
          //  service = new FileStorageRestClient();
        } else if (serviceType.equals(ServiceType.SOAP)) {
            LOGGER.info("Creating SOAP service client");
            service = new FileStorageServiceImplService().getFileStorageServiceImplPort();
        } else {
            LOGGER.info("Wrong input");
            throw new RuntimeException();
        }
        return service;
    }
    enum ServiceType {
        SOAP, REST;
    }
}
