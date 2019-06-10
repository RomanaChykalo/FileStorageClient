package rest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.client.RestTemplate;
import soap.SoapServiceException_Exception;
import soap.Type;
import soap.UserFile;

public class FileStorageRestClient {
   /* public static final String endpoint = "http://localhost:8080/RomanaChykaloFileStorageService/storageREST";
    public static Logger logger = LogManager.getLogger(FileStorageRestClient.class);
    private final String GET_ALL_FILES_REQUEST = "/files";
    private final String GET_FILE_BY_NAME_REQUEST = "/files/%s";
    private final String CREATE_FILE_REQUEST = "/files";
    private final String DELETE_FILE_REQUEST = "/files/%s";
    private final String GET_FILES_BY_TYPE_REQUEST = "/files/type/%s";
    private FileStorageRestClient restClient;
    public FileStorageRestClient() {
        restClient = new FileStorageRestClient();
    }
   private RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

   *//* public FileStorageRestClient() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
    }*//*

    @Override
    public List<UserFile> getAllFiles() throws SoapServiceException_Exception {
        List<UserFile> userFiles = restTemplate.getForObject(endpoint+"/files", List.class);
        logger.debug("Returning all files: " + userFiles);
        return userFiles;
    }

    @Override
    public UserFile getFile(String name) throws SoapServiceException_Exception {
        logger.debug("getFileByName({})", name);
        return restTemplate.getForObject(endpoint + "/files/" + name, UserFile.class);
    }

    @Override
    public boolean addFile(UserFile file) throws SoapServiceException_Exception {
        ResponseEntity<UserFile> entity = restTemplate.postForEntity(endpoint+"/files", file, UserFile.class);
        logger.debug("Creating file: " + file);
        return entity.hasBody();
    }

    @Override
    public boolean removeFile(String name) throws SoapServiceException_Exception {
        logger.debug("Deleting file with name: " + name);
        restTemplate.delete(endpoint +"/files/"+ name,boolean.class);
        return true;
    }

    @Override
    public List<UserFile> getOneTypeFileList(Type type) throws SoapServiceException_Exception {
        List<UserFile> userFiles = restTemplate.getForObject(endpoint + "/files/type/"+type, List.class);
        logger.debug("Returning files by type: " + type);
        return userFiles;
    }*/
}
