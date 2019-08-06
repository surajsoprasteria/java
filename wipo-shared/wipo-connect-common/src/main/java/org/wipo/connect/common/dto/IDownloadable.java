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
package org.wipo.connect.common.dto;

/**
 * The Interface IDownloadable.
 */
public interface IDownloadable {

    /**
     * Gets the document.
     *
     * @return the document
     */
    byte[] getDocument();
    
    /**
     * Gets the file name.
     *
     * @return the file name
     */
    String getFileName();
    
    /**
     * Gets the file size.
     *
     * @return the file size
     */
    Long getFileSize();
    
    /**
     * Gets the content type.
     *
     * @return the content type
     */
    String getContentType();

}
