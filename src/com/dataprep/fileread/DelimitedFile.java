package com.dataprep.fileread;

import com.dataprep.fileoutput.ExportToFlatFile;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class DelimitedFile extends Delimited {
    private Vector<String[]> datatmp;
    private String delimiter;


    // ---------------------------------------------------
    public HashMap<Integer, String> getHeaders(String inputFile, String delimiter) throws IOException {
        setDelimeter(delimiter);
        String[] headers = null;

        HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

        try (FileInputStream fs = new FileInputStream(inputFile)) {

            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            headers = br.readLine().split(delimiter);

            for (int i = 0; i < headers.length; i++) {
                docHeaders.put(i, cleanHeaders(headers[i]));
                //System.out.println("Header>>: " + headers[i]);
            }

            int lines = 1;
            while (br.readLine() != null) lines++;
            fs.getChannel().position(0);
            br = new BufferedReader(new InputStreamReader(fs));

            fs.close();
            br.close();
        }

        return docHeaders;

    } // End of getHeaders


    public void getRecords(String inputFile, String ouputFile, String delimiter) throws IOException {
        String[] headers = null;
        setDelimeter(delimiter);
        datatmp = new Vector<>();
        try (FileInputStream fs = new FileInputStream(inputFile)) {

            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            headers = br.readLine().split(delimiter);

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                datatmp.add(i, values);
                i++;
            }

            ExportToFlatFile exportToFlatFile = new ExportToFlatFile();
            exportToFlatFile.init(ouputFile, datatmp, headers);

            fs.close();
            br.close();

        }
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimeter(String delimiter) {
        this.delimiter = delimiter;
    }


} // End of DelimitedFile class