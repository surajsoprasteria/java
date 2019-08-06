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
package org.wipo.connect.common.import_reader;

import java.io.IOException;
import java.util.List;

import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.IpRow;

public interface IpReader {

    boolean hasNextBlock() throws IOException;

    List<IpRow> getBlockEntity() throws IOException;

    void initializeReader(String fileName, EnvironmentEnum env, Integer ip_import_page_size) throws Exception;

    void closeReader() throws IOException;
}
