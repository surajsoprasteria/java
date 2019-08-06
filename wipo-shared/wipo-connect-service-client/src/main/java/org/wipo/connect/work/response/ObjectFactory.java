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

package org.wipo.connect.work.response;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * A factory for creating Object objects.
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Instantiates a new object factory.
     */
    public ObjectFactory() {
    }

    /**
     * Creates a new Object object.
     *
     * @return the IR work elaboration response
     */
    public IRWorkElaborationResponse createIRWorkElaborationResponse() {
	return new IRWorkElaborationResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the internationally register response
     */
    public InternationallyRegisterResponse createInternationallyRegisterResponse() {
	return new InternationallyRegisterResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work detail response
     */
    public WorkDetailResponse createWorkDetailResponse() {
	return new WorkDetailResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work list response
     */
    public WorkListResponse createWorkListResponse() {
	return new WorkListResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the elaboration response
     */
    public IRWorkElaborationResponse.ElaborationResponse createIRWorkElaborationResponseElaborationResponse() {
	return new IRWorkElaborationResponse.ElaborationResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work response
     */
    public InternationallyRegisterResponse.WorkResponse createInternationallyRegisterResponseWorkResponse() {
	return new InternationallyRegisterResponse.WorkResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work response
     */
    public WorkDetailResponse.WorkResponse createWorkDetailResponseWorkResponse() {
	return new WorkDetailResponse.WorkResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work response
     */
    public WorkListResponse.WorkResponse createWorkListResponseWorkResponse() {
	return new WorkListResponse.WorkResponse();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work performer
     */
    public WorkPerformer createWorkPerformer() {
	return new WorkPerformer();
    }

    /**
     * Creates a new Object object.
     *
     * @return the derived view type
     */
    public DerivedViewType createDerivedViewType() {
	return new DerivedViewType();
    }

    /**
     * Creates a new Object object.
     *
     * @return the name type
     */
    public NameType createNameType() {
	return new NameType();
    }

    /**
     * Creates a new Object object.
     *
     * @return the derived work
     */
    public DerivedWork createDerivedWork() {
	return new DerivedWork();
    }

    /**
     * Creates a new Object object.
     *
     * @return the flat type
     */
    public FlatType createFlatType() {
	return new FlatType();
    }

    /**
     * Creates a new Object object.
     *
     * @return the title type
     */
    public TitleType createTitleType() {
	return new TitleType();
    }

    /**
     * Creates a new Object object.
     *
     * @return the derived view name list
     */
    public DerivedViewNameList createDerivedViewNameList() {
	return new DerivedViewNameList();
    }

    /**
     * Creates a new Object object.
     *
     * @return the derived view name share
     */
    public DerivedViewNameShare createDerivedViewNameShare() {
	return new DerivedViewNameShare();
    }

}
