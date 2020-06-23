import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DelimitedFile {

    public void getRecords(String inputFile, String ouputFile) throws IOException {
        List<String[]> datatmp;
        datatmp = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream(inputFile)) {

            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(DataPrep.getIncomingDelimiter());
                datatmp.add(i, values);

                i++;
            }

            ExportToFlatFile exportToFlatFile = new ExportToFlatFile();
            exportToFlatFile.init(ouputFile, datatmp);

        }
    }

} // End of DelimitedFile class