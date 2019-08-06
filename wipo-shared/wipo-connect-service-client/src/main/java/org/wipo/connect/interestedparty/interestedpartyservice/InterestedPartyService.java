/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */



package org.wipo.connect.interestedparty.interestedpartyservice;



import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;




/**
 * The Class InterestedPartyService.
 */
@WebServiceClient(name = "InterestedPartyService", wsdlLocation = "/META-INF/wsdl/interested_party/interestedPartyService.wsdl", targetNamespace = "http://interestedPartyService.interestedParty.connect.wipo.org")
public class InterestedPartyService extends Service {

    
    /**
     * The Constant WSDL_LOCATION.
     */
    public final static URL WSDL_LOCATION;

    
    /**
     * The Constant webServiceException.
     */
    public final static WebServiceException webServiceException;

    
    /**
     * The Constant SERVICE.
     */
    public final static QName SERVICE = new QName(
            "http://interestedPartyService.interestedParty.connect.wipo.org",
            "InterestedPartyService");

    
    /**
     * The Constant InterestedPartyServicePortBinding.
     */
    public final static QName InterestedPartyServicePortBinding = new QName(
            "http://interestedPartyService.interestedParty.connect.wipo.org",
            "InterestedPartyServicePortBinding");

    static {
        WSDL_LOCATION = org.wipo.connect.interestedparty.interestedpartyservice.InterestedPartyService.class
                .getResource("/META-INF/wsdl/interested_party/interestedPartyService.wsdl");
        WebServiceException e = null;
        if (WSDL_LOCATION == null) {
            e = new WebServiceException(
                    "Cannot find '/META-INF/wsdl/interested_party/interestedPartyService.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        webServiceException = e;
    }



    
    /**
     * Instantiates a new interested party service.
     */
    public InterestedPartyService() {
        super(WSDL_LOCATION, SERVICE);
    }



    
    /**
     * Instantiates a new interested party service.
     *
     * @param wsdlLocation the wsdl location
     */
    public InterestedPartyService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }



    
    /**
     * Instantiates a new interested party service.
     *
     * @param wsdlLocation the wsdl location
     * @param serviceName the service name
     */
    public InterestedPartyService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }



    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    
    /**
     * Instantiates a new interested party service.
     *
     * @param wsdlLocation the wsdl location
     * @param serviceName the service name
     * @param features the features
     */
    public InterestedPartyService(URL wsdlLocation, QName serviceName,
            WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }



    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    
    /**
     * Instantiates a new interested party service.
     *
     * @param wsdlLocation the wsdl location
     * @param features the features
     */
    public InterestedPartyService(URL wsdlLocation,
            WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }



    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    
    /**
     * Instantiates a new interested party service.
     *
     * @param features the features
     */
    public InterestedPartyService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }



    
    /**
     * Gets the interested party service port binding.
     *
     * @return the interested party service port binding
     */
    @WebEndpoint(name = "InterestedPartyServicePortBinding")
    public InterestedPartyServicePort getInterestedPartyServicePortBinding() {
        return super.getPort(InterestedPartyServicePortBinding,
                InterestedPartyServicePort.class);
    }



    
    /**
     * Gets the interested party service port binding.
     *
     * @param features the features
     * @return the interested party service port binding
     */
    @WebEndpoint(name = "InterestedPartyServicePortBinding")
    public InterestedPartyServicePort getInterestedPartyServicePortBinding(
            WebServiceFeature... features) {
        return super.getPort(InterestedPartyServicePortBinding,
                InterestedPartyServicePort.class, features);
    }

}
