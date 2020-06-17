import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ExportToFlatFile {
    List<String[]> datacache;
    DataDetective dataDetective;
    String outDelimiter = "|";
    String[] headers = null;


    public void init(String exportFilePath, List<String[]> data) {
        int rCount = 0;
        int rCountStop = 0;

        datacache = data;
        dataDetective = new DataDetective();
        headers = datacache.get(0);


        try (FileWriter writer = new FileWriter(exportFilePath)){
                int cCount = headers.length;
                String logColumnMsg = "Header Count: " + cCount;
                DataPrep.LOGGER.log(Level.INFO, logColumnMsg );

                for(int i = 0; i < cCount; i++) {
                    if(i == (cCount-1)) {
                        writer.append(headers[i].toUpperCase().replace(" ", "_"));
                        writer.append('\n'); // Close the column header
                    } else {
                        writer.append(headers[i].toUpperCase().replace(" ", "_"));
                        writer.append(outDelimiter);
                    }
                }


                rCount = datacache.size();
                rCountStop = rCount - 1;
                String logRowMsg = "Row Count: " + rCount;
                DataPrep.LOGGER.log(Level.INFO, logRowMsg);
                StringBuilder line = new StringBuilder();;
            for(int r = 1; r < rCount; r++) {
                String[] lineValue = datacache.get(r);

                int cCountR = lineValue.length;
                if(r == rCountStop) {
                        for(int c = 0; c < cCountR; c++) {
                            if(c == cCountR-1) {
                                line.append(scrubLine(lineValue[c]));
                                line.append('\n'); // Close the row
                            } else {
                                line.append(scrubLine(lineValue[c]));
                                line.append(outDelimiter);
                            }
                        }

                        writer.append(line.toString());
                        line.setLength(0);

                    } else {
                        for(int c = 0; c < cCountR; c++) {
                            if(c == cCountR-1) {
                                line.append(scrubLine(lineValue[c]));
                                line.append('\n'); // Close the row
                            } else {
                                line.append(scrubLine(lineValue[c]));
                                line.append(outDelimiter);
                            }

                        }

                        writer.append(line.toString());
                        line.setLength(0);
                    }
                } // end-for-loop



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String scrubLine(String line) {
        String[] lineSplit = line.split(DataPrep.getDelimiter());
        StringBuilder scrubbed = new StringBuilder();

        for(int x = 0; x < lineSplit.length; x++) {
            boolean isInt = dataDetective.isNumber(lineSplit[x]);
            boolean isDate = dataDetective.isDate(lineSplit[x]);
            String clean = null;
            if(isInt == false && isDate == false) {
                clean = dataDetective.removeNumbers(dataDetective.removePII(lineSplit[x]));
                clean = dataDetective.addDQuotes(clean);

            } else {
                clean = line;
            }
            scrubbed.append(clean);
        }
        return scrubbed.toString();
    }




} // End of ExportToFlatFile class
