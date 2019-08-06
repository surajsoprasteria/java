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

package org.wipo.connect.work.request;

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
     * @return the internationally register request
     */
    public InternationallyRegisterRequest createInternationallyRegisterRequest() {
	return new InternationallyRegisterRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work list request
     */
    public WorkListRequest createWorkListRequest() {
	return new WorkListRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work detail request
     */
    public WorkDetailRequest createWorkDetailRequest() {
	return new WorkDetailRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the task item request
     */
    public InternationallyRegisterRequest.TaskItemRequest createInternationallyRegisterRequestTaskItemRequest() {
	return new InternationallyRegisterRequest.TaskItemRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the IR work elaboration request
     */
    public IRWorkElaborationRequest createIRWorkElaborationRequest() {
	return new IRWorkElaborationRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work request
     */
    public WorkListRequest.WorkRequest createWorkListRequestWorkRequest() {
	return new WorkListRequest.WorkRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the work request
     */
    public WorkDetailRequest.WorkRequest createWorkDetailRequestWorkRequest() {
	return new WorkDetailRequest.WorkRequest();
    }

    /**
     * Creates a new Object object.
     *
     * @return the right owner type
     */
    public RightOwnerType createRightOwnerType() {
	return new RightOwnerType();
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
     * @return the flat type
     */
    public FlatType createFlatType() {
	return new FlatType();
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

    /**
     * Creates a new Object object.
     *
     * @return the work task detail
     */
    public InternationallyRegisterRequest.TaskItemRequest.WorkTaskDetail createInternationallyRegisterRequestTaskItemRequestWorkTaskDetail() {
	return new InternationallyRegisterRequest.TaskItemRequest.WorkTaskDetail();
    }

}
