import allure.CustomListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import soap.FileStorageService;
import soap.SoapServiceException_Exception;
import soap.Type;
import soap.UserFile;
import utils.parser.CSVParser;

import java.io.File;
import java.util.Iterator;
import java.util.List;
@Listeners({CustomListener.class})
public class AddDeleteApiTest {
    private Logger logger = LogManager.getLogger(AddDeleteApiTest.class);
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
    public void addFilesNegativeRest(String name, String type, String size) throws SoapServiceException_Exception {
        logger.info("....Try to add files with no-acceptable data....");
        UserFile userFile = new UserFile(name.trim(), Type.valueOf(type), Integer.valueOf(size.trim()));
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        int sizeBeforeFileAdd = service.getAllFiles().size();
        String message = null;
        try {
            service.addFile(userFile);
        } catch (RuntimeException e) {
            message = e.getMessage();
            logger.debug(String.format(" File was not added, exception message: %s", message));
        }
        Assert.assertNotNull(message);
        int sizeAfterFileAdd = service.getAllFiles().size();
        Assert.assertEquals(sizeBeforeFileAdd, sizeAfterFileAdd);
        logger.info(String.format("Size of storage before operation: %s = size of storage after operation %s, file with size: %s" +
                " was not added", sizeBeforeFileAdd, sizeAfterFileAdd, userFile.getSize()));
    }

    @Test(dataProvider = "validFilesData")
    public void addFilesPositiveRest(String name, String type, String size) throws SoapServiceException_Exception {
        logger.info("....Add files with acceptable data rest....");
        UserFile userFile = new UserFile(name.trim(), Type.valueOf(type), Integer.valueOf(size.trim()));
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        Assert.assertTrue(service.addFile(userFile));
        logger.info("File: " + userFile + " was added");
        Assert.assertEquals(userFile, service.getFile(userFile.getName()));
        service.removeFile(name);
    }
    @Test
    public void addFileToStorageSoap() throws SoapServiceException_Exception {
        logger.info(".....Add file to storage soap positive test...");
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        UserFile userFile = new UserFile("letter to grandma", Type.WKS, 400);
        List<UserFile> userFiles = service.getAllFiles();
        int sizeOfStorageBeforeFileAdding = userFiles.size();
        logger.info(String.format("Size of storage before file adding %s", sizeOfStorageBeforeFileAdding));
        Assert.assertTrue(service.addFile(userFile));
        logger.info("Added file to storage: " + userFile);
        userFiles = service.getAllFiles();
        int sizeOfStorageAfterFileAdding = userFiles.size();
        logger.info(String.format("Size of storage after file adding %s", sizeOfStorageAfterFileAdding));
        Assert.assertEquals(sizeOfStorageAfterFileAdding - sizeOfStorageBeforeFileAdding, 1);
        Assert.assertTrue(userFiles.contains(userFile));
        service.removeFile(userFile.getName());
    }

    @Test
    public void deleteFileRest() throws SoapServiceException_Exception {
        logger.info(".......Delete file from storage rest-test......");
        FileStorageService serviceRest = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        UserFile userFile = new UserFile("Validation form", Type.ODT, 20);
        Assert.assertTrue(serviceRest.addFile(userFile));
        Assert.assertTrue(serviceRest.removeFile(userFile.getName()));
        logger.info(String.format("Removed file with name: %s", userFile.getName()));
        String message = null;
        try {
            logger.info("Looking for deleted file...");
            Assert.assertNull(serviceRest.getFile(userFile.getName()));
        } catch (RuntimeException e) {
            message = e.getMessage();
            logger.info(String.format("Response: %s, searched file was deleted",message));
        }
        Assert.assertNotNull(message);
    }
    @Test
    public void deleteFileSoap() throws SoapServiceException_Exception {
        logger.info("......Delete file from storage soap-test....");
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        UserFile userFile = new UserFile("Validation form", Type.ODT, 20);
        Assert.assertTrue(serviceSoap.addFile(userFile));
        Assert.assertTrue(serviceSoap.removeFile(userFile.getName()));
        logger.info(String.format("Removed file with name: %s", userFile.getName()));
        String message = null;
        try {
            logger.info("Looking for deleted file...");
            Assert.assertNull(serviceSoap.getFile(userFile.getName()));
        } catch (SoapServiceException_Exception e) {
            message = e.getMessage();
            logger.info(String.format("Response: %s, searched file was deleted",message));
        }
        Assert.assertNotNull(message);
    }


}
