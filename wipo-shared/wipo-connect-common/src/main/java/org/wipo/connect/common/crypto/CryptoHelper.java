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

package org.wipo.connect.common.crypto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class CryptoHelper.
 */
@SuppressWarnings("unchecked")
public class CryptoHelper {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CryptoHelper.class);

    /**
     * The encryptor.
     */
    TextEncryptor encryptor;

    /**
     * Instantiates a new crypto helper.
     *
     * @param encryptor
     *            the encryptor
     */
    public CryptoHelper(TextEncryptor encryptor) {
	this.encryptor = encryptor;
    }

    /**
     * Decrypt.
     *
     * @param <T>
     *            the generic type
     * @param <C>
     *            the generic type
     * @param input
     *            the input
     * @return the c
     */
    public <T extends Serializable, C extends Collection<T>> C decrypt(C input) {
	return (C) execute(input, false);
    }

    /**
     * Encrypt.
     *
     * @param <T>
     *            the generic type
     * @param <C>
     *            the generic type
     * @param input
     *            the input
     * @return the c
     */
    public <T extends Serializable, C extends Collection<T>> C encrypt(C input) {
	return (C) execute(input, true);
    }

    /**
     * Decrypt.
     *
     * @param <T>
     *            the generic type
     * @param input
     *            the input
     * @return the t
     */
    public <T extends Serializable> T decrypt(T input) {
	return (T) execute(input, false);
    }

    /**
     * Encrypt.
     *
     * @param <T>
     *            the generic type
     * @param input
     *            the input
     * @return the t
     */
    public <T extends Serializable> T encrypt(T input) {
	return (T) execute(input, true);
    }

    private Object execute(Object input, boolean crypt) {
	if (input == null) {
	    return null;
	}

	if (!(input instanceof Serializable)) {
	    throw new IllegalArgumentException("Input object must be serializable");
	}

	Object obj = SerializationUtils.clone((Serializable) input);

	// if the object is a String
	if (obj instanceof String) {
	    obj = processString(crypt, obj);
	} else if (obj instanceof Collection) {
	    // itereates and invoke valication for each item
	    obj = processCollections(crypt, obj);

	} else {
	    // loops on found fields
	    processFields(crypt, obj);
	}

	return obj;

    }

    private Object processCollections(boolean crypt, Object obj) {
	List<Object> tmpList = new ArrayList<>();
	for (Object item : (Collection<Object>) obj) {
	    tmpList.add(execute(item, crypt));
	}

	try {
	    ((Collection<Object>) obj).clear();
	    ((Collection<Object>) obj).addAll(tmpList);
	} catch (UnsupportedOperationException e) {
	    LOGGER.error("CyptoHelper - this list is Immutable, converting it to ArrayList", e);
	    obj = tmpList;
	}
	return obj;
    }

    private Object processString(boolean crypt, Object obj) {
	String originalValue = (String) obj;
	String convertedValue;
	if (crypt) {
	    convertedValue = encryptor.encrypt(originalValue);
	} else {
	    convertedValue = encryptor.decrypt(originalValue);
	}
	obj = convertedValue;
	return obj;
    }

    private void processFields(boolean crypt, Object obj) {
	// gets all the fields annotated with @CustomValidatedField
	List<Field> fields = FieldUtils.getFieldsListWithAnnotation(obj.getClass(), CryptedField.class);

	for (Field f : fields) {
	    String fieldName = obj.getClass().getSimpleName() + "." + f.getName();
	    LOGGER.debug("CyptoHelper - processing field:  " + fieldName);

	    Serializable fieldValue;
	    try {
		fieldValue = (Serializable) FieldUtils.readField(f, obj, true);
	    } catch (IllegalAccessException e) {
		throw new IllegalStateException("Error reading value of " + fieldName, e);
	    }

	    try {
		FieldUtils.writeField(f, obj, execute(fieldValue, crypt), true);
	    } catch (IllegalAccessException e) {
		throw new IllegalStateException("Error writing value of " + fieldName, e);
	    }
	}
    }

}
