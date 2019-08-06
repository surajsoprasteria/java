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
package org.wipo.connect.common.i18n;

import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.utils.WccUtils;

public class ImportMessagesDecoder {

    private static final String KEY_ERROR_TEMPLATE = "error-message-template";
    private static final String KEY_INSERTED = "inserted-message";
    private static final String KEY_UPDATED = "updated-message";
    private static final String KEY_DELETED = "deleted-message";

    private static final String KEY_WARNING_MESSAGE_ITEM = "warning-message-item";
    private static final String KEY_WARNING_MESSAGE_TEMPLATE = "warning-message-template";

    private final String prefix;
    private final Properties messages;
    private final String errorMessageTemplate;
    private final String warningMessage;

    public ImportMessagesDecoder(String prefix, Properties messages) {
	super();
	this.prefix = prefix;
	this.messages = messages;
	this.errorMessageTemplate = messages.getProperty(KEY_ERROR_TEMPLATE, "Error {0} - [{1}]");
	this.warningMessage = messages.getProperty(KEY_WARNING_MESSAGE_TEMPLATE);
    }

    public String getErrorMessage(String code) {
	String key = prefix + code;
	String description = messages.getProperty(key, "");
	String message = MessageFormat.format(errorMessageTemplate, code, description);
	return message;
    }

    public String getWarningMessage(String code) {
	String templateMessage = StringUtils.EMPTY;
	String description = "";
	if (code != null) {
	    String key = prefix + code;
	    description = messages.getProperty(key, "");
	    templateMessage = MessageFormat.format(messages.getProperty(KEY_WARNING_MESSAGE_ITEM), code, description);
	}
	String message = MessageFormat.format(warningMessage, templateMessage);
	return message;
    }

    public String getSuccessMessage(TransactionTypeEnum transactionTypeEnum) {
	String key;

	switch (transactionTypeEnum) {
	case INSERT:
	    key = KEY_INSERTED;
	    break;
	case UPDATE:
	    key = KEY_UPDATED;
	    break;
	case DELETE:
	    key = KEY_DELETED;
	    break;
	default:
	    throw new IllegalStateException("Error in " + WccUtils.getMethodName() + " invalid transaction type: " + transactionTypeEnum);
	}

	return messages.getProperty(key, "");
    }

}
