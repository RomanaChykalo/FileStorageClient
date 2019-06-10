import org.testng.Assert;
import org.testng.annotations.*;
import soap.FileStorageService;
import soap.SoapServiceException_Exception;
import soap.Type;
import soap.UserFile;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertThrows;

public class SoapFileStorageServiceTest {
    public Logger logger = org.apache.log4j.Logger.getLogger(SoapFileStorageServiceTest.class);

    private List<UserFile> createUserFilesList() {
        List<UserFile> userFileList = new ArrayList<>();
        userFileList.add(new UserFile("laptop configuration", Type.WKS, 500));
        userFileList.add(new UserFile("my birthday party menu", Type.ODT, 766));
        userFileList.add(new UserFile("salary report", Type.ODT, 89));
        userFileList.add(new UserFile("my lonely txt", Type.TXT, 500));
        userFileList.add(new UserFile("fitness schedule", Type.DOC, 250));
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
    public void getAllFilesFromStorage() {
        logger.info("get all files from storage test");
        FileStorageService service = ServiceFactory.getFileStorageService(ServiceFactory.ServiceType.SOAP);
        List<UserFile> allFiles = null;
        try {
            allFiles = service.getAllFiles();
        } catch (SoapServiceException_Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(allFiles);
        Assert.assertTrue(allFiles.contains(new UserFile("laptop configuration", Type.WKS, 500)));
       Assert.assertEquals(allFiles.get(allFiles.size() - 1), new UserFile("fitness schedule", Type.DOC, 250));
        Assert.assertTrue(allFiles.size() >= 5);
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
            int sizeOfStorageAfterFileAdding = userFiles.size();
            Assert.assertEquals(sizeOfStorageAfterFileAdding, sizeOfStorageBeforeFileAdding);
            Assert.assertTrue(userFiles.contains(userFile));
        } else {
            try {
                service.addFile(userFile);
            } catch (SoapServiceException_Exception e) {
                e.printStackTrace();
            }
            int sizeOfStorageAfterFileAdding = userFiles.size();
            Assert.assertNotEquals(sizeOfStorageAfterFileAdding, sizeOfStorageBeforeFileAdding);
            Assert.assertTrue(userFiles.contains(userFile));
            try {
                service.removeFile(userFile.getName());
            } catch (SoapServiceException_Exception e) {
                e.printStackTrace();
            }
        }
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
