package com.dataprep.fileread;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public interface FileReader {
    enum types {XLSX, XLS, DELIMITED}

    HashMap<Integer, String> getHeaders(String fileName, String delimiter) throws IOException;


}