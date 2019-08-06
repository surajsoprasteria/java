
package org.wipo.connect.echoservice.types;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.wipo.connect.echoservice.types package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.wipo.connect.echoservice.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link EchoRequest }
     * 
     */
    public EchoRequest createEchoRequest() {
        return new EchoRequest();
    }

    /**
     * Create an instance of {@link EchoResponse.Response }
     * 
     */
    public EchoResponse.Response createEchoResponseResponse() {
        return new EchoResponse.Response();
    }

    /**
     * Create an instance of {@link EchoRequest.Request }
     * 
     */
    public EchoRequest.Request createEchoRequestRequest() {
        return new EchoRequest.Request();
    }

}
