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



package org.wipo.connect.shared.web.utils;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;



/**
 * The Class DocumentContainer.
 */
@SuppressWarnings({ "squid:S1948" })
public class DocumentContainer implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1369739922857180457L;

    /** The id list. */
    private List<Long> idList;

    /** The file list. */
    private List<MultipartFile> fileList;

    /** The description list. */
    private List<String> descriptionList;

    /** The exec delete list. */
    private List<Boolean> execDeleteList;



    /**
     * Gets the description list.
     *
     * @return the description list
     */
    public List<String> getDescriptionList() {
        if (this.descriptionList == null) {
            this.descriptionList = new ArrayList<>();
        }
        return this.descriptionList;
    }



    /**
     * Gets the exec delete list.
     *
     * @return the exec delete list
     */
    public List<Boolean> getExecDeleteList() {
        if (this.execDeleteList == null) {
            this.execDeleteList = new ArrayList<>();
        }
        return this.execDeleteList;
    }



    /**
     * Gets the file list.
     *
     * @return the file list
     */
    public List<MultipartFile> getFileList() {
        if (this.fileList == null) {
            this.fileList = new ArrayList<>();
        }
        return this.fileList;
    }



    /**
     * Gets the id list.
     *
     * @return the id list
     */
    public List<Long> getIdList() {
        if (this.idList == null) {
            this.idList = new ArrayList<>();
        }
        return this.idList;
    }








    /**
     * Sets the description list.
     *
     * @param descriptionList
     *            the new description list
     */
    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }



    /**
     * Sets the exec delete list.
     *
     * @param execDeleteList
     *            the new exec delete list
     */
    public void setExecDeleteList(List<Boolean> execDeleteList) {
        this.execDeleteList = execDeleteList;
    }



    /**
     * Sets the file list.
     *
     * @param fileList
     *            the new file list
     */
    public void setFileList(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }



    /**
     * Sets the id list.
     *
     * @param idList
     *            the new id list
     */
    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }





}
