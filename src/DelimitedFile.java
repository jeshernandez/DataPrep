import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

public class DelimitedFile {

    public void getRecords(String inputFile, String ouputFile) throws IOException {
        List<String[]> datatmp;
        datatmp = new Vector<>();
        try (FileInputStream fs = new FileInputStream(inputFile)) {

            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(DataPrep.getDelimiter());
                datatmp.add(i, values);

                i++;
            }

            ExportToFlatFile exportToFlatFile = new ExportToFlatFile();
            exportToFlatFile.init(ouputFile, datatmp);

        }
    }

} // End of DelimitedFile class