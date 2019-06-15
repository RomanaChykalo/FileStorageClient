
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import soap.FileStorageService;
import soap.SoapServiceException_Exception;
import soap.Type;
import soap.UserFile;
import utils.parser.CSVParser;

import java.io.File;
import java.util.Iterator;

import static org.testng.Assert.assertThrows;


public class RestFileStorageServiceTest {
    private Logger logger = LogManager.getLogger(RestFileStorageServiceTest.class);
    private static final String nonValidFilesData = "src/test/resources/files/nonValidFilesData.csv";
    private static final String validFilesData = "src/test/resources/files/validFilesData.csv";

    @DataProvider
    private Iterator<Object[]> nonValidFilesData() {
        return CSVParser.parseCSVFile(new File(nonValidFilesData)).iterator();
    }

    @DataProvider
    private Iterator<Object[]> validFilesData() {
        return CSVParser.parseCSVFile(new File(validFilesData)).iterator();
    }

    @Test(dataProvider = "nonValidFilesData")
    public void addFilesNegativeTest(String name, String type, String size) throws SoapServiceException_Exception {
        logger.info("Add files with no-acceptable data rest test");
        UserFile userFile = new UserFile(name.trim(), Type.valueOf(type), Integer.valueOf(size.trim()));
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        String message = null;
        try {
            int sizeBeforeFileAdd = service.getAllFiles().size();
            service.addFile(userFile);
            Assert.assertNull(service.getFile(name));
            int sizeAfterFileAdd = service.getAllFiles().size();
            Assert.assertEquals(sizeBeforeFileAdd, sizeAfterFileAdd);
        } catch (RuntimeException e) {
            message = e.getMessage();
            logger.warn(message);
        }
        Assert.assertNotNull(message);
    }

    @Test(dataProvider = "validFilesData")
    public void addFilesPositiveTest(String name, String type, String size) throws SoapServiceException_Exception {
        logger.info("Add files rest test");
        UserFile userFile = new UserFile(name.trim(), Type.valueOf(type), Integer.valueOf(size.trim()));
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        Assert.assertTrue(service.addFile(userFile));
        Assert.assertEquals(userFile, service.getFile(userFile.getName()));
        Assert.assertTrue(service.removeFile(name));
        String message = null;
        try {
            Assert.assertNull(service.getFile(name));
        } catch (RuntimeException e) {
            message = e.getMessage();
            System.out.println("Response: " + e.getMessage() + ", searched file was deleted");
        }
        Assert.assertNotNull(message);
    }

    @Test
    public void searchByNameSoapAndRest() throws SoapServiceException_Exception {
        FileStorageService serviceRest = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        UserFile userFile = new UserFile("Validation form", Type.ODT, 20);
        Assert.assertTrue(serviceRest.addFile(userFile));
        logger.info("Add file: " + userFile + " for testing rest search methods");
        Assert.assertEquals(serviceSoap.getFile(userFile.getName()), userFile);
        Assert.assertEquals(serviceRest.getFile(userFile.getName()), userFile);
        serviceRest.removeFile(userFile.getName());
    }

    @Test
    public void searchNonPresentFileShouldThrowExcRest() {
        FileStorageService serviceRest = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        assertThrows(RuntimeException.class,
                () -> {
                    serviceRest.getFile("Vesna Precrasna");
                });
    }

    @Test(expectedExceptions = SoapServiceException_Exception.class)
    public void searchNonPresentFileShouldThrowExcSoap() throws SoapServiceException_Exception {
        logger.info("Search non present File in storage");
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        Assert.assertNull(serviceSoap.getFile("Vesna Precrasna"));
    }


}
