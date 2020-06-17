import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ExportToFlatFile {
    List<String[]> datacache;
    DataDetective dataDetective;
    String outDelimiter = DataPrep.getOutputDelimiter();
    String[] headers = null;


    public void init(String exportFilePath, List<String[]> data) {
        int rCount = 0;
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
                String logRowMsg = "Row Count: " + rCount;
                DataPrep.LOGGER.log(Level.INFO, logRowMsg);
                StringBuilder line = new StringBuilder();
            for(int r = 1; r < rCount; r++) {
                String[] lineValue = datacache.get(r);

                int cCountR = lineValue.length;

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
                } // end-for-loop



        } catch (IOException e) {
            DataPrep.LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    private String scrubLine(String line) {
        String[] lineSplit = line.split(DataPrep.getIncomingDelimiter());
        StringBuilder scrubbed = new StringBuilder();

        for(int x = 0; x < lineSplit.length; x++) {
            boolean isInt = dataDetective.isNumber(lineSplit[x]);
            boolean isDate = dataDetective.isDate(lineSplit[x]);
            String clean = null;

            if(!isInt && !isDate) {
                if(DataPrep.isScrubPII()) {
                    clean = dataDetective.removePII(lineSplit[x]);
                } else if(DataPrep.isScrubNumbersFromText()) {
                    clean = dataDetective.removeNumbers(lineSplit[x]);
                } else if(DataPrep.isScrubPII() && DataPrep.isScrubPII()) {
                    clean = dataDetective.removeNumbers(dataDetective.removePII(lineSplit[x]));
                } else {
                    clean = dataDetective.addDQuotes(lineSplit[x]);
                }
            } else {
                clean = line;
            }
            scrubbed.append(clean);
        }
        return scrubbed.toString();
    }




} // End of ExportToFlatFile class
