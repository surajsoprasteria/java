/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.web.viewresolver;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;
import org.wipo.connect.common.dto.IDownloadable;

/**
 * The Class DownloadViewResolver.
 */
public class DownloadViewResolver extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

	try {
	    IDownloadable objToDownload = (IDownloadable) model.get("download");
	    response.reset(); // needed for HTTPS

	    response.setContentLength(objToDownload.getFileSize().intValue());
	    response.setDateHeader("Expires", 0);
	    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	    response.setHeader("Pragma", "public");

	    response.setContentType(objToDownload.getContentType());
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + objToDownload.getFileName() + "\"");

	    Cookie downloadCookie = new Cookie("fileDownload", "true");
	    downloadCookie.setPath("/");
	    response.addCookie(downloadCookie);

	    response.getOutputStream().write(objToDownload.getDocument());
	} finally {
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}

    }

}
