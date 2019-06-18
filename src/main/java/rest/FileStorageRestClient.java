package rest;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import io.qameta.allure.Step;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.web.client.RestTemplate;
import soap.*;
import soap.Type;

public class FileStorageRestClient implements FileStorageService {
    private static HttpClient httpClient;
    private static RestTemplate restTemplate;
    private static ClientConfig clientConfig;
    private static Client client;
    private static final String ENDPOINT = "http://localhost:8080/RomanaChykaloFileStorageService/storageREST";
    private static final String GET_ALL_FILES_REQUEST = "/files";
    private static final String GET_FILE_BY_NAME_REQUEST = "/files/%s";
    private static final String CREATE_FILE_REQUEST = "/files";
    private static final String DELETE_FILE_REQUEST = "/files/%s";
    private static final String GET_FILES_BY_TYPE_REQUEST = "/files/type/%s";



    static {
        httpClient = HttpClientBuilder.create().build();
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

    @Step("Rest service get all files from storage ...")
    @Override
    public List<UserFile> getAllFiles() {
        List userFilesRestTempl = restTemplate.getForObject(ENDPOINT + GET_ALL_FILES_REQUEST, List.class);
        ObjectMapper mapper = new ObjectMapper();
        List<UserFile> pojos = mapper.convertValue(
                userFilesRestTempl,
                new TypeReference<List<UserFile>>() {
                });
        return pojos;
    }

    @Step("Get file with name: {name} using rest service ...")
    @Override
    public UserFile getFile(String name) {
        UserFile restObject = restTemplate.getForObject(String.format(ENDPOINT + GET_FILE_BY_NAME_REQUEST, name), UserFile.class);
        if (Objects.isNull(restObject)) {
            throw new RuntimeException("File with name: " + name + " not found");
        }
        UserFile file = new UserFile(restObject.getName(), restObject.getType(), restObject.getSize());
        return file;
    }

    @Step("Add file to storage using rest service ...")
    @Override
    public boolean addFile(UserFile file) {
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
            throw new RuntimeException(String.format("HTTP response code %s instead of 200", response.getStatus()));
        }
        return true;
    }

    @Step("Remove file with name: {name} using rest service ...")
    @Override
    public boolean removeFile(String name) {
        HttpDelete request = new HttpDelete(String.format(ENDPOINT + DELETE_FILE_REQUEST, name).replace(" ", "%20"));
        isResponseCodeOK(request);
        return true;
    }

    @Step("Get files with type: {type} using rest service ...")
    @Override
    public List<UserFile> getOneTypeFileList(Type type) {
        HttpGet request = new HttpGet(String.format(ENDPOINT + GET_FILES_BY_TYPE_REQUEST, type));
        isResponseCodeOK(request);
        List userFilesRestTempl = restTemplate.getForObject(String.format(ENDPOINT + GET_FILES_BY_TYPE_REQUEST, type), List.class);
        ObjectMapper mapper = new ObjectMapper();
        List<UserFile> pojos = mapper.convertValue(
                userFilesRestTempl,
                new TypeReference<List<UserFile>>() {
                });
        return pojos;
    }

    private boolean isResponseCodeOK(HttpRequestBase httpRequest) {
        HttpResponse clientResponse = null;
        try {
            clientResponse = httpClient.execute(httpRequest);
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