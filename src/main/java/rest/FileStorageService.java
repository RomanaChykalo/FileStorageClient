package rest;

import soap.SoapServiceException_Exception;
import soap.Type;
import soap.UserFile;

import java.util.List;

public interface FileStorageService {
    List<UserFile> getAllFiles() throws SoapServiceException_Exception;

    UserFile getFile(String name) throws SoapServiceException_Exception;

    boolean addFile(UserFile file) throws SoapServiceException_Exception;

    boolean removeFile(String name) throws SoapServiceException_Exception;

    List<UserFile> getOneTypeFileList(Type type) throws SoapServiceException_Exception;
}
