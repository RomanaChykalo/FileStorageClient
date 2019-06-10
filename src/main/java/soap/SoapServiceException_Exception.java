
package soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SoapServiceException", targetNamespace = "http://soap.web/")
public class SoapServiceException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SoapServiceException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public SoapServiceException_Exception(String message, SoapServiceException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public SoapServiceException_Exception(String message, SoapServiceException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: web.soap.SoapServiceException
     */
    public SoapServiceException getFaultInfo() {
        return faultInfo;
    }

}
