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



package org.wipo.connect.work.workservice;



import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;




/**
 * The Class WorkService.
 */
@WebServiceClient(name = "WorkService", wsdlLocation = "/META-INF/wsdl/work/workService.wsdl", targetNamespace = "http://workService.work.connect.wipo.org")
public class WorkService extends Service {

    
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
            "http://workService.work.connect.wipo.org", "WorkService");

    
    /**
     * The Constant WorkServicePortBinding.
     */
    public final static QName WorkServicePortBinding = new QName(
            "http://workService.work.connect.wipo.org", "WorkServicePortBinding");

    static {
        WSDL_LOCATION = org.wipo.connect.interestedparty.interestedpartyservice.InterestedPartyService.class
                .getResource("/META-INF/wsdl/work/workService.wsdl");
        WebServiceException e = null;
        if (WSDL_LOCATION == null) {
            e = new WebServiceException(
                    "Cannot find '/META-INF/wsdl/work/workService.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        webServiceException = e;
    }



    
    /**
     * Instantiates a new work service.
     */
    public WorkService() {
        super(WSDL_LOCATION, SERVICE);
    }



    
    /**
     * Instantiates a new work service.
     *
     * @param wsdlLocation the wsdl location
     */
    public WorkService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }



    
    /**
     * Instantiates a new work service.
     *
     * @param wsdlLocation the wsdl location
     * @param serviceName the service name
     */
    public WorkService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }



    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    
    /**
     * Instantiates a new work service.
     *
     * @param wsdlLocation the wsdl location
     * @param serviceName the service name
     * @param features the features
     */
    public WorkService(URL wsdlLocation, QName serviceName,
            WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }



    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    
    /**
     * Instantiates a new work service.
     *
     * @param wsdlLocation the wsdl location
     * @param features the features
     */
    public WorkService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }



    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    
    /**
     * Instantiates a new work service.
     *
     * @param features the features
     */
    public WorkService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }



    
    /**
     * Gets the work service port binding.
     *
     * @return the work service port binding
     */
    @WebEndpoint(name = "WorkServicePortBinding")
    public WorkServicePort getWorkServicePortBinding() {
        return super.getPort(WorkServicePortBinding, WorkServicePort.class);
    }



    
    /**
     * Gets the work service port binding.
     *
     * @param features the features
     * @return the work service port binding
     */
    @WebEndpoint(name = "WorkServicePortBinding")
    public WorkServicePort getWorkServicePortBinding(
            WebServiceFeature... features) {
        return super.getPort(WorkServicePortBinding, WorkServicePort.class,
                features);
    }

}
