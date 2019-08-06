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
package org.wipo.connect.common.import_queries_reader;

import java.io.IOException;
import java.util.List;

public interface QueryReader {

    void closeReader() throws IOException;

    void initializeReader(String fileName) throws Exception;

    void initializeReader(byte[] file) throws Exception;

    List<QueryRow> loadQueryList() throws Exception;
}
