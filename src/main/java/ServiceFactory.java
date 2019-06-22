import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.FileStorageRestClient;
import soap.FileStorageService;
import soap.FileStorageServiceImplService;
import soap.UserFile;

import static soap.Type.PDF;

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
        return service;
    }
    enum ServiceType {
        SOAP, REST;
    }

    public static void main(String[] args) {
        FileStorageRestClient fileStorageRestClient = new FileStorageRestClient();
        fileStorageRestClient.addFile(new UserFile("Hello", PDF,100));
    }
}
