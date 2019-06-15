import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.FileStorageRestClient;
import soap.*;

import java.util.List;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        /*FileStorageRestClient service = new FileStorageRestClient();
        UserFile userFile = new UserFile("Italiano-vero",Type.DOC,555);
        List<UserFile> allFiles = service.getAllFiles();
        logger.info(allFiles);*/
        FileStorageServiceImplService implService = new FileStorageServiceImplService();
        FileStorageService service=implService.getFileStorageServiceImplPort();
        boolean weekend = false;
        try {
            weekend = service.removeFile("My planner");
        } catch (SoapServiceException_Exception e) {
            e.printStackTrace();
        }
        System.out.println(weekend);
    }

}
