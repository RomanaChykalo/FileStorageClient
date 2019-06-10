
package soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOC"/>
 *     &lt;enumeration value="DOCX"/>
 *     &lt;enumeration value="ODT"/>
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="RTF"/>
 *     &lt;enumeration value="TEX"/>
 *     &lt;enumeration value="TXT"/>
 *     &lt;enumeration value="WPS"/>
 *     &lt;enumeration value="WKS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeMsg")
@XmlEnum
public enum Type {

    DOC,
    DOCX,
    ODT,
    PDF,
    RTF,
    TEX,
    TXT,
    WPS,
    WKS;

    public String value() {
        return name();
    }

    public static Type fromValue(String v) {
        return valueOf(v);
    }

}
