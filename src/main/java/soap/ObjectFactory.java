
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the web.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOneTypeFileListResponse_QNAME = new QName("http://soap.web/", "getOneTypeFileListResponse");
    private final static QName _AddFile_QNAME = new QName("http://soap.web/", "addFile");
    private final static QName _GetFile_QNAME = new QName("http://soap.web/", "getFile");
    private final static QName _AddFileResponse_QNAME = new QName("http://soap.web/", "addFileResponse");
    private final static QName _SoapServiceException_QNAME = new QName("http://soap.web/", "SoapServiceException");
    private final static QName _GetAllFilesResponse_QNAME = new QName("http://soap.web/", "getAllFilesResponse");
    private final static QName _GetAllFiles_QNAME = new QName("http://soap.web/", "getAllFiles");
    private final static QName _GetFileResponse_QNAME = new QName("http://soap.web/", "getFileResponse");
    private final static QName _RemoveFile_QNAME = new QName("http://soap.web/", "removeFile");
    private final static QName _GetOneTypeFileList_QNAME = new QName("http://soap.web/", "getOneTypeFileList");
    private final static QName _RemoveFileResponse_QNAME = new QName("http://soap.web/", "removeFileResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: web.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOneTypeFileList }
     * 
     */
    public GetOneTypeFileList createGetOneTypeFileList() {
        return new GetOneTypeFileList();
    }

    /**
     * Create an instance of {@link RemoveFileResponse }
     * 
     */
    public RemoveFileResponse createRemoveFileResponse() {
        return new RemoveFileResponse();
    }

    /**
     * Create an instance of {@link RemoveFile }
     * 
     */
    public RemoveFile createRemoveFile() {
        return new RemoveFile();
    }

    /**
     * Create an instance of {@link GetFileResponse }
     * 
     */
    public GetFileResponse createGetFileResponse() {
        return new GetFileResponse();
    }

    /**
     * Create an instance of {@link GetAllFiles }
     * 
     */
    public GetAllFiles createGetAllFiles() {
        return new GetAllFiles();
    }

    /**
     * Create an instance of {@link GetAllFilesResponse }
     * 
     */
    public GetAllFilesResponse createGetAllFilesResponse() {
        return new GetAllFilesResponse();
    }

    /**
     * Create an instance of {@link SoapServiceException }
     * 
     */
    public SoapServiceException createSoapServiceException() {
        return new SoapServiceException();
    }

    /**
     * Create an instance of {@link AddFileResponse }
     * 
     */
    public AddFileResponse createAddFileResponse() {
        return new AddFileResponse();
    }

    /**
     * Create an instance of {@link GetFile }
     * 
     */
    public GetFile createGetFile() {
        return new GetFile();
    }

    /**
     * Create an instance of {@link AddFile }
     * 
     */
    public AddFile createAddFile() {
        return new AddFile();
    }

    /**
     * Create an instance of {@link GetOneTypeFileListResponse }
     * 
     */
    public GetOneTypeFileListResponse createGetOneTypeFileListResponse() {
        return new GetOneTypeFileListResponse();
    }

    /**
     * Create an instance of {@link UserFile }
     * 
     */
    public UserFile createUserFile() {
        return new UserFile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOneTypeFileListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "getOneTypeFileListResponseMsg")
    public JAXBElement<GetOneTypeFileListResponse> createGetOneTypeFileListResponse(GetOneTypeFileListResponse value) {
        return new JAXBElement<GetOneTypeFileListResponse>(_GetOneTypeFileListResponse_QNAME, GetOneTypeFileListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "addFileMsg")
    public JAXBElement<AddFile> createAddFile(AddFile value) {
        return new JAXBElement<AddFile>(_AddFile_QNAME, AddFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "getFileMsg")
    public JAXBElement<GetFile> createGetFile(GetFile value) {
        return new JAXBElement<GetFile>(_GetFile_QNAME, GetFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "addFileResponseMsg")
    public JAXBElement<AddFileResponse> createAddFileResponse(AddFileResponse value) {
        return new JAXBElement<AddFileResponse>(_AddFileResponse_QNAME, AddFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "SoapServiceExceptionMsg")
    public JAXBElement<SoapServiceException> createSoapServiceException(SoapServiceException value) {
        return new JAXBElement<SoapServiceException>(_SoapServiceException_QNAME, SoapServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "getAllFilesResponseMsg")
    public JAXBElement<GetAllFilesResponse> createGetAllFilesResponse(GetAllFilesResponse value) {
        return new JAXBElement<GetAllFilesResponse>(_GetAllFilesResponse_QNAME, GetAllFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "getAllFilesMsg")
    public JAXBElement<GetAllFiles> createGetAllFiles(GetAllFiles value) {
        return new JAXBElement<GetAllFiles>(_GetAllFiles_QNAME, GetAllFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "getFileResponseMsg")
    public JAXBElement<GetFileResponse> createGetFileResponse(GetFileResponse value) {
        return new JAXBElement<GetFileResponse>(_GetFileResponse_QNAME, GetFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "removeFileMsg")
    public JAXBElement<RemoveFile> createRemoveFile(RemoveFile value) {
        return new JAXBElement<RemoveFile>(_RemoveFile_QNAME, RemoveFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOneTypeFileList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "getOneTypeFileListMsg")
    public JAXBElement<GetOneTypeFileList> createGetOneTypeFileList(GetOneTypeFileList value) {
        return new JAXBElement<GetOneTypeFileList>(_GetOneTypeFileList_QNAME, GetOneTypeFileList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.web/", name = "removeFileResponseMsg")
    public JAXBElement<RemoveFileResponse> createRemoveFileResponse(RemoveFileResponse value) {
        return new JAXBElement<RemoveFileResponse>(_RemoveFileResponse_QNAME, RemoveFileResponse.class, null, value);
    }

}
