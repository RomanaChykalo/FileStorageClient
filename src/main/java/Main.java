import soap.*;

public class Main {
    public static void main(String[] args) {
        FileStorageServiceImplService service = new FileStorageServiceImplService();
        FileStorageService st=service.getFileStorageServiceImplPort();
        try {
            UserFile userFile = new UserFile();
            st.removeFile("resume");
        } catch (SoapServiceException_Exception e) {
            e.printStackTrace();
        }
    }
}
