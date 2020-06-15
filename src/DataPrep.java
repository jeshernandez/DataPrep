import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPrep {
    private final static Logger LOGGER = Logger.getLogger(DataPrep.class.getName());
    private static String LogFileName = "dataprep.log";
    public static void main(String[] args) throws IOException {
        LOGGER.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler(LogFileName);
        LOGGER.addHandler(fileHandler);
        LOGGER.info("Application Main started");

        if(args.length > 2) {
            startProcessing(args[0], args[1], args[2]);
            LOGGER.info("Parameters: " + args[0] +", " + args[1] + ", " + args[2]);
        } else {
            System.out.println("Requires input file, output file, delimiter.");
            LOGGER.info("User did not input all required parameters.");
        }
    }


    public static void startProcessing(String inputFile, String outputFile, String delimiter) {
        DelimitedFile delimitedFile = new DelimitedFile();
        try {
            delimitedFile.getRecords(inputFile, outputFile, delimiter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


} // End of DataPrep
