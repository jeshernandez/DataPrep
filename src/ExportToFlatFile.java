import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ExportToFlatFile {
    Vector<String[]> datacache;

    public void init(String exportFilePath, Vector<String[]> data, String[] headers) {
        int cCount = 0;
        int rCount = 0;
        int cCountStop = 0;
        int rCountStop = 0;
        String delimiter = "|";
        datacache = data;


        try {
            FileWriter writer = new FileWriter(exportFilePath);

                // Columns
                cCount = headers.length;
                cCountStop = cCount - 1;

                for(int i = 0; i < cCount; i++) {
                    if(i == cCountStop) {
                        writer.append(headers[i].toString().toUpperCase().replaceAll(" ", "_"));
                        writer.append('\n'); // Close the column header
                    } else {
                        writer.append(headers[i].toString().toUpperCase().replaceAll(" ", "_"));
                        writer.append(delimiter);
                    }
                }


                rCount = datacache.size();
                rCountStop = rCount - 1;
                //System.out.println("Row Count: " + rCountStop);
                StringBuilder line = new StringBuilder();;
            for(int r = 0; r < rCount; r++) {
                    if(r == rCountStop) {
                        for(int c = 0; c < cCount; c++) {

                            if(c == cCountStop) {
                                line.append(getValueAt(r, c));
                                line.append('\n'); // Close the row
                            } else {
                                line.append(getValueAt(r, c));
                                line.append(delimiter);
                            }

                        }

                        //System.out.println("Line: " + line);
                        writer.append(line.toString());
                        line.setLength(0);
                    } else {
                        for(int c = 0; c < cCount; c++) {

                            if(c == cCountStop) {
                                line.append(getValueAt(r, c));
                                line.append('\n'); // Close the row
                            } else {
                                line.append(getValueAt(r, c));
                                line.append(delimiter);
                            }

                        }

                        //System.out.println("Line: " + line);
                        writer.append(line.toString());
                        line.setLength(0);
                    }
                } // end-for-loop



            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
