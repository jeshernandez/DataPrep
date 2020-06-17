import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPrep {
    private static String logName = "dataprep.log";
    public final static Logger LOGGER = Logger.getLogger(DataPrep.class.getName());
    public static String incomingDelimiter;
    public static String outputDelimiter;
    static DataPrepSettings dataPrepSettings = new DataPrepSettings();

    public static void main(String[] args)  {
        setLoggingSystem();
        dataPrepSettings.setSettings();
        LOGGER.info("Application Main started");

        if(args.length > 2) {
            startProcessing(args[0], args[1], args[2]);
            LOGGER.log(Level.INFO, "Parameters successfully entered." );

        } else {
            LOGGER.log(Level.INFO, "User did not input all required parameters.");
            System.err.println("Parameters Required: input.txt output.txt delimiter (e.g. \t for tab)");
        }
    }


    private static void setLoggingSystem()  {
        LOGGER.setLevel(Level.INFO);
        try  {
            FileHandler fileHandler = new FileHandler(logName);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startProcessing(String inputFile, String outputFile, String delimiter) {
        DelimitedFile delimitedFile = new DelimitedFile();
        setOutputDelimiter(dataPrepSettings.getOutputDelimiter());
        setIncomingDelimiter(delimiter);
        try {
            delimitedFile.getRecords(inputFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }


    public static String getIncomingDelimiter() {
        return incomingDelimiter;
    }

    public static void setIncomingDelimiter(String incomingDelimiter) {
        DataPrep.incomingDelimiter = incomingDelimiter;
    }

    public static String getOutputDelimiter() {
        return outputDelimiter;
    }

    public static void setOutputDelimiter(String outputDelimiter) {
        DataPrep.outputDelimiter = outputDelimiter;
    }
} // End of DataPrep
