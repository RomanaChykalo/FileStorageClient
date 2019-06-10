
package soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userFile complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="userFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{http://soap.web/}type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userFileMsg", propOrder = {
    "name",
    "size",
    "type"
})
public class UserFile {

    protected String name;
    protected int size;
    @XmlSchemaType(name = "string")
    protected Type type;
    public UserFile(String name, Type type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public UserFile() {
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the size property.
     *
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     *
     */
    public void setSize(int value) {
        this.size = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link Type }
     *
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link Type }
     *
     */
    public void setType(Type value) {
        this.type = value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        UserFile userFile = (UserFile) object;

        if (size != userFile.size) return false;
        if (!name.equals(userFile.name)) return false;
        return type == userFile.type;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + size;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
