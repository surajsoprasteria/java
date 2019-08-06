package org.wipo.connect.common.import_writer;

import java.io.IOException;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;

public interface WorkWriter<T> {

    final static String SUFFIX_LOCAL = "out";
    final static String SUFFIX_INTER = "wrk.out";

    void writeRows(T workRows) throws WccException;

    ImportFileTypeEnum getImportFileTypeEnum();

    String getFileName();

    void closeStream() throws IOException;

    void flush() throws IOException;

}
