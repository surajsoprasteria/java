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
package org.wipo.connect.common.import_writer;

import java.io.IOException;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;

public interface IpWriter<T> {

    final static String SUFFIX_LOCAL = "out";
    final static String SUFFIX_INTER = "ip.out";

    void writeRows(T ipRows) throws WccException;

    String getFileName();

    ImportFileTypeEnum getImportFileTypeEnum();

    void closeStream() throws IOException;

    void flush() throws IOException;

}
