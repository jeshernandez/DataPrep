import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class DelimitedFile {
    private Vector<String[]> datatmp;
    private String delimiter;

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