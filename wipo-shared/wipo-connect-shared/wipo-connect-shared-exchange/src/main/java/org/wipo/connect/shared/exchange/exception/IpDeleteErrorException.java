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
package org.wipo.connect.shared.exchange.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class IpDeleteError.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpDeleteErrorException extends WccException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3013931278069453813L;
	/** The message. */
    private List<String> messages;
	
    /**
     * Instantiates a new ip delete error exception.
     *
     * @param code the code
     * @param message the message
     * @param data the data
     * @param uuid the uuid
     * @param messages the messages
     */
    @JsonCreator
    protected IpDeleteErrorException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code,
            @JsonProperty(value = "message", required = false) String message,
            @JsonProperty(value = "data", required = false) Map<String, Object> data,
            @JsonProperty(value = "uuid", required = true) String uuid,
            @JsonProperty(value = "messages", required = true) List<String> messages) {
        super(code, message, data, uuid);
        this.messages = messages;
    }


	/**
	 * Instantiates a new ip delete error exception.
	 *
	 * @param message the message
	 * @param messages the messages
	 */
	public IpDeleteErrorException(String message,List<String> messages) {
		super(WccExceptionCodeEnum.APPLICATION_ERROR, message);
		this.messages = messages;
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}



	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(List<String> messages) {
		if (messages==null){
			messages = new ArrayList<String>();
		}
		this.messages = messages;
	}

}
