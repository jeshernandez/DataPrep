import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPrep {
    private static String logName = "dataprep.log";
    public final static Logger LOGGER = Logger.getLogger(DataPrep.class.getName());
    public static String delimiter;

    public static void main(String[] args) throws IOException {
        LOGGER.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler(logName);
        LOGGER.addHandler(fileHandler);
        LOGGER.info("Application Main started");

        if(args.length > 2) {
            startProcessing(args[0], args[1], args[2]);
            LOGGER.log(Level.INFO, "Parameters successfully entered." );

        } else {
            LOGGER.log(Level.INFO, "User did not input all required parameters.");
            System.err.println("Parameters Required: input.txt output.txt delimiter (e.g. \t for tab)");
        }
    }


    public static void startProcessing(String inputFile, String outputFile, String delimiter) {
        DelimitedFile delimitedFile = new DelimitedFile();
        setDelimiter(delimiter);
        try {
            delimitedFile.getRecords(inputFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    public static String getDelimiter() {
        return delimiter;
    }

    public static void setDelimiter(String delimiter) {
        DataPrep.delimiter = delimiter;
    }

} // End of DataPrep
