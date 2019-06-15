package rest;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.client.RestTemplate;
import soap.*;
import soap.Type;

public class FileStorageRestClient implements FileStorageService {
    public static Logger logger = LogManager.getLogger(FileStorageRestClient.class);
    private static HttpClient CLIENT;
    private static RestTemplate restTemplate;
    public static final String ENDPOINT = "http://localhost:8080/RomanaChykaloFileStorageService/storageREST";
    private static final String GET_ALL_FILES_REQUEST = "/files";
    private static final String GET_FILE_BY_NAME_REQUEST = "/files/%s";
    private static final String CREATE_FILE_REQUEST = "/files";
    private static final String DELETE_FILE_REQUEST = "/files/%s";
    private static final String GET_FILES_BY_TYPE_REQUEST = "/files/type/%s";
    private static ClientConfig clientConfig;
    private static Client client;


    static {
        CLIENT = HttpClientBuilder.create().build();
        restTemplate = new RestTemplate();
        clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);

    }

    public FileStorageRestClient() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
    }

    @Override
    public List<UserFile> getAllFiles() {
        List userFilesRestTempl = restTemplate.getForObject(ENDPOINT + GET_ALL_FILES_REQUEST, List.class);
        ObjectMapper mapper = new ObjectMapper();
        List<UserFile> pojos = mapper.convertValue(
                userFilesRestTempl,
                new TypeReference<List<UserFile>>() {
                });
        logger.debug("Returning all files: " + userFilesRestTempl);
        return pojos;
    }


    @Override
    public UserFile getFile(String name) {
        logger.debug("Find file by name({})", name);
        /*HttpGet httpRequest = new HttpGet(String.format(ENDPOINT + GET_FILE_BY_NAME_REQUEST, name).replace(" ", "%20"));
        isResponseCodeOK(httpRequest);*/
        UserFile restObject = restTemplate.getForObject(String.format(ENDPOINT + GET_FILE_BY_NAME_REQUEST, name), UserFile.class);
        logger.info("URL is: " + String.format(ENDPOINT + GET_FILE_BY_NAME_REQUEST, name));
        if (Objects.isNull(restObject)) {
            throw new RuntimeException("File with name: " + name + " not found");
        }
        UserFile file = new UserFile(restObject.getName(), restObject.getType(), restObject.getSize());
        logger.info("File with name: " + name + " is found : " + file);
        return file;
    }

    @Override
    public boolean addFile(UserFile file) {
       /* HttpPost post = new HttpPost(ENDPOINT + CREATE_FILE_REQUEST);
        isResponseCodeOK(post);
        //return true;
        ResponseEntity<Boolean> entity = restTemplate.postForEntity(ENDPOINT + CREATE_FILE_REQUEST, file, Boolean.class);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("HTTP response code is not 200");
        }
        logger.debug("Creating file: " + file);
        return true;*/
        ObjectMapper mapper = new ObjectMapper();
        String uri = ENDPOINT + CREATE_FILE_REQUEST;
        WebResource webResource = client.resource(uri);
        ClientResponse response = null;
        try {
            response = webResource.accept("application/json;encoding=UTF-8").type("application/json")
                    .post(ClientResponse.class, mapper.writeValueAsString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getStatus() != 200) {
            throw new RuntimeException(String.format("HTTP response code %s instead of 200",response.getStatus()));
        }
        return true;

           /* ResponseEntity<Boolean> entity = restTemplate.postForEntity(ENDPOINT + "/files", file, boolean.class);
            HttpPost httpRequest = new HttpPost(ENDPOINT + "/files");
            isResponseCodeOK(httpRequest);
            logger.debug("Creating file: " + file);
            return true;*/

    }

    @Override
    public boolean removeFile(String name) {
        HttpDelete request = new HttpDelete(String.format(ENDPOINT + DELETE_FILE_REQUEST, name).replace(" ", "%20"));
        isResponseCodeOK(request);
        logger.debug("Removed file with name: " + name);
        return true;
    }

    @Override
    public List<UserFile> getOneTypeFileList(Type type) {
        logger.debug("Returning files by type: " + type);
        HttpGet request = new HttpGet(String.format(ENDPOINT + GET_FILES_BY_TYPE_REQUEST, type));
        isResponseCodeOK(request);
        List userFilesRestTempl = restTemplate.getForObject(String.format(ENDPOINT + GET_FILES_BY_TYPE_REQUEST, type), List.class);
        ObjectMapper mapper = new ObjectMapper();
        List<UserFile> pojos = mapper.convertValue(
                userFilesRestTempl,
                new TypeReference<List<UserFile>>() {
                });
        logger.debug("Returning all files: " + userFilesRestTempl);
        return pojos;
    }

    private boolean isResponseCodeOK(HttpRequestBase httpRequest) {
        HttpResponse clientResponse = null;
        try {
            clientResponse = CLIENT.execute(httpRequest);
            int responseCode = clientResponse.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                throw new RuntimeException(String.format("HTTP response code %s instead of 200", responseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}