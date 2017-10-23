package com.miskevich.txttosqlconverter.writer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class SQLFileWriter {

    public void writeSQLFile(String fileName, String context){
        try {
            FileUtils.writeStringToFile(new File(fileName), context, "UTF-8", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
