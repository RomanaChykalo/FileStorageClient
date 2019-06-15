import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.FileStorageRestClient;
import soap.FileStorageService;
import soap.FileStorageServiceImplService;

public class ServiceFactory {
    private static Logger LOGGER = LogManager.getLogger(ServiceFactory.class);

    public static FileStorageService getFileStorageService(ServiceType serviceType) {
        FileStorageService service = null;
        switch (serviceType){
            case SOAP:
                LOGGER.info("Create SOAP client");
                service = new FileStorageServiceImplService().getFileStorageServiceImplPort();
                break;
            case REST:
                LOGGER.info("Create REST client");
                service = new FileStorageRestClient();
                break;
        }
        /*if (serviceType.equals(ServiceType.REST)) {
            LOGGER.info("Creating REST service client");
            service = new FileStorageRestClient();
        } else if (serviceType.equals(ServiceType.SOAP)) {
            LOGGER.info("Creating SOAP service client");
            service = new FileStorageServiceImplService().getFileStorageServiceImplPort();
        } else {
            LOGGER.info("Wrong input");
            throw new RuntimeException();
        }
        return service;*/
        return service;
    }
    enum ServiceType {
        SOAP, REST;
    }
}
