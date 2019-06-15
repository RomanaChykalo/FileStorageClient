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

public class SoapFileStorageServiceTest {
    public Logger logger = LogManager.getLogger(SoapFileStorageServiceTest.class);

    private List<UserFile> createUserFilesList() {
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
    public void getAllFilesFromStorage() throws SoapServiceException_Exception {
        logger.info("get all files from storage test");
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        List<UserFile> allFiles = service.getAllFiles();

        Assert.assertNotNull(allFiles);
        Assert.assertTrue(allFiles.contains(new UserFile("laptop configuration", Type.ODT, 500)));
        Assert.assertEquals(allFiles.get(allFiles.size() - 1), new UserFile("salary report", Type.ODT, 89));
        Assert.assertTrue(allFiles.size() >= 3);
    }

    @Test
    public void addFileToStorage() {
        logger.info("add file to storage positive test");
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        UserFile userFile = new UserFile("letter to grandma", Type.WKS, 400);
        List<UserFile> userFiles = null;
        try {
            userFiles = service.getAllFiles();
        } catch (SoapServiceException_Exception e) {
            e.printStackTrace();
        }
        int sizeOfStorageBeforeFileAdding = userFiles.size();
        if (userFiles.contains(userFile)) {
            try {
                service.addFile(userFile);
                userFiles = service.getAllFiles();
            } catch (SoapServiceException_Exception e) {
                e.printStackTrace();
            }
            int sizeOfStorageAfterFileAdding = userFiles.size();
            Assert.assertEquals(sizeOfStorageAfterFileAdding, sizeOfStorageBeforeFileAdding);
            Assert.assertTrue(userFiles.contains(userFile));
        } else {
            try {
                service.addFile(userFile);
                userFiles = service.getAllFiles();
            } catch (SoapServiceException_Exception e) {
                e.printStackTrace();
            }
            int sizeOfStorageAfterFileAdding = userFiles.size();
            Assert.assertNotEquals(sizeOfStorageAfterFileAdding, sizeOfStorageBeforeFileAdding);
            Assert.assertTrue(userFiles.contains(userFile));
        }
        try {
            service.removeFile(userFile.getName());
        } catch (SoapServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchByTypeSoap() throws SoapServiceException_Exception {
        FileStorageService serviceSoap = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        List<UserFile> odtTypeList = serviceSoap.getOneTypeFileList(Type.valueOf("ODT"));
        Assert.assertNotNull(odtTypeList);
        Assert.assertTrue(odtTypeList.size() >= 3);
        Set<Type> odtTypeSet = odtTypeList.stream().map(UserFile::getType).collect(Collectors.toSet());
        Assert.assertEquals(odtTypeSet.size(), 1);
    }

    @Test
    public void searchByTypeRest() throws SoapServiceException_Exception {
        FileStorageService serviceRest = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.REST);

        List<UserFile> odtTypeList = serviceRest.getOneTypeFileList(Type.valueOf("ODT"));
        Assert.assertNotNull(odtTypeList);
        Assert.assertTrue(odtTypeList.size() >= 3);
        Set<Type> odtTypeSet = odtTypeList.stream().map(UserFile::getType).collect(Collectors.toSet());
        Assert.assertEquals(odtTypeSet.size(), 1);
    }

    @Test
    public void addFileToStorageShouldThrowExc() {
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        logger.info("add file to storage must throw SoapServiceException test");

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
