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



package org.wipo.connect.shared.exchange.utils;



import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * The Class IdentifierGenerator.
 */
@Component
public class IdentifierGenerator implements Generator {

    /**
     * The Enum WccIdType.
     */
    public enum WccIdType {

        /** The work. */
        WORK("WO"),

        /** The interested party. */
        INTERESTED_PARTY("IP"),

        /** The license. */
        LICENSE("L1"),

        /** The licensee. */
        LICENSEE("L2"),

        /** The name. */
        NAME("NA"),

        /** The account. */
        ACCOUNT("AC"),

        /** The collection. */
        COLLECTION("CO"),

        /** The distribution. */
        DISTRIBUTION("DI");

        /** The code. */
        private final String code;



        /**
         * Instantiates a new wcc id type.
         *
         * @param code
         *            the code
         */
        WccIdType(String code) {
            this.code = code;
        }



        /**
         * Gets the code.
         *
         * @return the code
         */
        public String getCode() {
            return this.code;
        }
    }

    /** The Constant ID_PADDING. */
    private static final int ID_PADDING = 12;

    /** The Constant PROP_PREFIX. */
    private static final String PROP_PREFIX = "cmo.code";

    /** The wcc shared properties. */
    @Autowired
    private Properties configProperties;




    @Override
	public String generateWccIdentifier(WccIdType type, Long id) {
        StringBuilder identifier = new StringBuilder();

        identifier.append(this.configProperties.getProperty(PROP_PREFIX));
        identifier.append("-");
        identifier.append(type.getCode());
        identifier.append("-");
        identifier.append(StringUtils.leftPad(id.toString(), ID_PADDING, "0"));

        return identifier.toString();
    }

}
