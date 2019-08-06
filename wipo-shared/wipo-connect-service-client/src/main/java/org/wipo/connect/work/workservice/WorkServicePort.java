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

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * The Interface WorkServicePort.
 */
@HandlerChain(file = "/handlers.xml")
@WebService(targetNamespace = "http://workService.work.connect.wipo.org", name = "WorkServicePort")
@XmlSeeAlso({org.wipo.connect.work.request.ObjectFactory.class, org.wipo.connect.work.response.ObjectFactory.class, org.wipo.connect.common.input.ObjectFactory.class, org.wipo.connect.common.output.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WorkServicePort {

    /**
     * Register internationally.
     *
     * @param internationallyRegisterRequest the internationally register request
     * @return the org.wipo.connect.work.response. internationally register response
     */
    @WebResult(name = "InternationallyRegisterResponse", targetNamespace = "http://response.work.connect.wipo.org", partName = "internationallyRegisterResponse")
    @WebMethod(action = "http://workService.connect.wipo.org/WorkService/registerInternationally")
    public org.wipo.connect.work.response.InternationallyRegisterResponse registerInternationally(
        @WebParam(partName = "internationallyRegisterRequest", name = "InternationallyRegisterRequest", targetNamespace = "http://request.work.connect.wipo.org")
        org.wipo.connect.work.request.InternationallyRegisterRequest internationallyRegisterRequest
    );

    /**
     * Gets the elaboration result.
     *
     * @param irElaborationResultRequest the ir elaboration result request
     * @return the elaboration result
     */
    @WebResult(name = "IRWorkElaborationResponse", targetNamespace = "http://response.work.connect.wipo.org", partName = "IRElaborationResponse")
    @WebMethod(action = "http://workService.connect.wipo.org/WorkService/getElaborationResult")
    public org.wipo.connect.work.response.IRWorkElaborationResponse getElaborationResult(
        @WebParam(partName = "IRElaborationResultRequest", name = "IRWorkElaborationRequest", targetNamespace = "http://request.work.connect.wipo.org")
        org.wipo.connect.work.request.IRWorkElaborationRequest irElaborationResultRequest
    );

    /**
     * Gets the work detail.
     *
     * @param workDetailRequest the work detail request
     * @return the work detail
     */
    @WebResult(name = "WorkDetailResponse", targetNamespace = "http://response.work.connect.wipo.org", partName = "WorkDetailResponse")
    @WebMethod(action = "http://workService.connect.wipo.org/WorkService/getWorkDetail")
    public org.wipo.connect.work.response.WorkDetailResponse getWorkDetail(
        @WebParam(partName = "WorkDetailRequest", name = "WorkDetailRequest", targetNamespace = "http://request.work.connect.wipo.org")
        org.wipo.connect.work.request.WorkDetailRequest workDetailRequest
    );

    /**
     * Gets the work list.
     *
     * @param workListRequest the work list request
     * @return the work list
     */
    @WebResult(name = "WorkListResponse", targetNamespace = "http://response.work.connect.wipo.org", partName = "WorkListResponse")
    @WebMethod(action = "http://workService.connect.wipo.org/WorkService/getWorkList")
    public org.wipo.connect.work.response.WorkListResponse getWorkList(
        @WebParam(partName = "WorkListRequest", name = "WorkListRequest", targetNamespace = "http://request.work.connect.wipo.org")
        org.wipo.connect.work.request.WorkListRequest workListRequest
    );
}
