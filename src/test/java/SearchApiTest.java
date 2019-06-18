import allure.CustomListener;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import soap.FileStorageService;
import soap.SoapServiceException_Exception;
import soap.Type;
import soap.UserFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertThrows;
@Listeners({CustomListener.class})
public class SearchApiTest {
    public Logger logger = LogManager.getLogger(SearchApiTest.class);

    private static List<UserFile> createUserFilesList() {
        List<UserFile> userFileList = new ArrayList<>();
        userFileList.add(new UserFile("laptop configuration", Type.ODT, 500));
        userFileList.add(new UserFile("my birthday party menu", Type.ODT, 766));
        userFileList.add(new UserFile("salary report", Type.ODT, 89));
        return userFileList;
    }

    @BeforeClass
    public void setUP() {
        List<UserFile> userFileList = createUserFilesList();
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        for (UserFile userFile : userFileList) {
            try {
                service.addFile(userFile);
            } catch (SoapServiceException_Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Description("Test Description: Soap test get all files from storage.")
    public void getAllFilesFromStorageSoap() throws SoapServiceException_Exception {
        logger.info("....Get all files from storage soap test.....");
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        List<UserFile> allFiles = service.getAllFiles();
        Assert.assertNotNull(allFiles);
        logger.info("Get all files from storage: " + allFiles);
        Assert.assertTrue(allFiles.containsAll(SearchApiTest.createUserFilesList()));/////////////////////////
    }

    @Test
    @Description("Test Description: Rest test get all files from storage.")
    public void getAllFilesFromStorageRest() throws SoapServiceException_Exception {
        logger.info("....Get all files from storage rest test.....");
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        List<UserFile> allFiles = service.getAllFiles();
        Assert.assertNotNull(allFiles);
        int filesAmount = allFiles.size();
        logger.info("Got all files from storage: " + allFiles);
        Assert.assertTrue(allFiles.containsAll(SearchApiTest.createUserFilesList()));////////////////////////
        Assert.assertTrue(allFiles.size() >= 3);
        List<UserFile> validSizeFileList = allFiles.stream().filter(f -> f.getSize() <= 1000).collect(Collectors.toList());
        Assert.assertEquals(filesAmount,validSizeFileList.size());

    }

    @Test
    @Description("Test Description: Soap test search by type files in storage.")
    public void searchByTypeSoap() throws SoapServiceException_Exception {
        logger.info("....Search files by type soap test...");
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        List<UserFile> odtTypeList = serviceSoap.getOneTypeFileList(Type.valueOf("ODT"));
        Assert.assertNotNull(odtTypeList);
        int odtListSize = odtTypeList.size();
        Assert.assertTrue(odtListSize >= 3);
        logger.info(String.format("Find: %s files with ODT type", odtListSize));
        Set<Type> odtTypeSet = odtTypeList.stream().map(UserFile::getType).collect(Collectors.toSet());
        Assert.assertEquals(odtTypeSet.size(), 1);
    }

    @Test
    @Description("Test Description: Rest test search by type files in storage.")
    public void searchByTypeRest() throws SoapServiceException_Exception {
        logger.info("....Search files by type rest test....");
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        List<UserFile> odtTypeList = serviceSoap.getOneTypeFileList(Type.valueOf("ODT"));
        Assert.assertNotNull(odtTypeList);
        int odtListSize = odtTypeList.size();
        Assert.assertTrue(odtListSize >= 3);
        logger.info(String.format("Find: %s files with ODT type", odtListSize));
        for (UserFile userFile : odtTypeList) {
            Assert.assertEquals(userFile.getType(), Type.ODT);
        }
    }

    @Test
    @Description("Test Description: Rest and Soap test search by name file in storage.")
    public void searchByNameSoapAndRest() throws SoapServiceException_Exception {
        logger.info("....Search file by name test....");
        FileStorageService serviceRest = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        UserFile userFile = new UserFile("Validation form", Type.ODT, 20);
        Assert.assertTrue(serviceRest.addFile(userFile));
        logger.info("Add file: " + userFile + " for testing rest search methods");
        Assert.assertEquals(serviceSoap.getFile(userFile.getName()), userFile);
        Assert.assertEquals(serviceRest.getFile(userFile.getName()), userFile);
        logger.info("File is found");
        serviceRest.removeFile(userFile.getName());
    }

    @Test
    @Description("Test Description: Rest test search by name absent file in storage should throw Exception.")
    public void searchNonPresentFileShouldThrowExcRest() {
        FileStorageService serviceRest = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);
        assertThrows(RuntimeException.class,
                () -> {
                    serviceRest.getFile("Vesna Precrasna");
                });
    }

    @Test(expectedExceptions = SoapServiceException_Exception.class)
    @Description("Test Description: Soap test search by name absent file in storage should throw Exception.")
    public void searchNonPresentFileShouldThrowExcSoap() throws SoapServiceException_Exception {
        logger.info("....Search non present file in storage.......");
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        Assert.assertNull(serviceSoap.getFile("Vesna Precrasna"));
    }


    @Test
    @Description("Test Description: Soap test add file to storage with unacceptable parameters should throw Exception.")
    public void addFileToStorageShouldThrowExcSoap() {
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        logger.info("....Add file to storage must throw SoapServiceException test.....");

        assertThrows(SoapServiceException_Exception.class,
                () -> {
                    List<UserFile> allFiles = service.getAllFiles();
                    UserFile userFilePresentOnList = allFiles.get(allFiles.size() - 1);
                    service.addFile(userFilePresentOnList);
                });
        assertThrows(SoapServiceException_Exception.class,
                () -> {
                    service.addFile(new UserFile("Picasso", Type.DOC, 1500));
                });
        assertThrows(SoapServiceException_Exception.class,
                () -> {
                    service.addFile(new UserFile("laptop configuration", Type.DOC, 455));
                });
    }

    @AfterClass
    public void after() {
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        List<UserFile> userFileList = createUserFilesList();
        for (UserFile userFile : userFileList) {
            try {
                service.removeFile(userFile.getName());
            } catch (SoapServiceException_Exception e) {
                e.printStackTrace();
            }
        }
    }
}
