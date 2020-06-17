import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;

public class ExportToFlatFile {
    Vector<String[]> datacache;
    DataDetective dataDetective;
    String outDelimiter = "|";

    public void init(String exportFilePath, Vector<String[]> data, String[] headers) {
        int cCount = 0;
        int rCount = 0;
        int cCountStop = 0;
        int rCountStop = 0;

        datacache = data;
        dataDetective = new DataDetective();

        try (FileWriter writer = new FileWriter(exportFilePath)){
                cCount = headers.length;
                cCountStop = cCount - 1;
                String logColumnMsg = "Header Count: " + cCount;
                DataPrep.LOGGER.log(Level.INFO, logColumnMsg );

                for(int i = 0; i < cCount; i++) {
                    if(i == cCountStop) {
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
            for(int r = 0; r < rCount; r++) {
                    if(r == rCountStop) {
                        for(int c = 0; c < cCount; c++) {

                            if(c == cCountStop) {
                                line.append(scrubLine(getValueAt(r, c).toString()));
                                line.append('\n'); // Close the row
                            } else {
                                line.append(scrubLine(getValueAt(r, c).toString()));
                                line.append(outDelimiter);
                            }

                        }

                        writer.append(line.toString());
                        line.setLength(0);
                    } else {
                        for(int c = 0; c < cCount; c++) {

                            if(c == cCountStop) {
                                line.append(scrubLine(getValueAt(r, c).toString()));
                                line.append('\n'); // Close the row
                            } else {
                                line.append(scrubLine(getValueAt(r, c).toString()));
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

    // --------------------------------------------------------------
    public Object getValueAt(int row, int col) {
        if(datacache.isEmpty()) {
            return null;
        } else {
            return ((Object[]) datacache.elementAt(row))[col];
        }
    }


} // End of ExportToFlatFile class
