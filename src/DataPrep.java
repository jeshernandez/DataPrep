import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPrep {
    static DataPrepSettings dataPrepSettings = new DataPrepSettings();
    public static String logName = "dataprep.log";
    public static String nameFile = "name_list.txt";
    public static String piiFile = "piiwords.txt";
    public final static Logger LOGGER = Logger.getLogger(DataPrep.class.getName());
    public static String incomingDelimiter;
    public static String outputDelimiter;
    public static boolean scrubPII;
    public static boolean scrubNumbersFromText;
    public static boolean scrubEmailFromText;

    public static void main(String[] args)  {
        setLoggingSystem();
        dataPrepSettings.setSettings();
        setOutputDelimiter(dataPrepSettings.getOutputDelimiter());
        setScrubPII(dataPrepSettings.isScrubPII());
        setScrubNumbersFromText(dataPrepSettings.isScrubNumberFromText());
        setScrubEmailFromText(dataPrepSettings.isScrubEmailFromText());
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
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    public static void startProcessing(String inputFile, String outputFile, String delimiter) {
        DelimitedFile delimitedFile = new DelimitedFile();
        setIncomingDelimiter(delimiter);
        try {
            delimitedFile.getRecords(inputFile, outputFile);
        } catch (IOException e) {
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

    public static boolean isScrubPII() {
        return scrubPII;
    }

    public static void setScrubPII(boolean scrubPII) {
        DataPrep.scrubPII = scrubPII;
    }

    public static boolean isScrubNumbersFromText() {
        return scrubNumbersFromText;
    }

    public static void setScrubNumbersFromText(boolean scrubNumbersFromText) {
        DataPrep.scrubNumbersFromText = scrubNumbersFromText;
    }

    public static boolean isScrubEmailFromText() {
        return scrubEmailFromText;
    }

    public static void setScrubEmailFromText(boolean scrubEmailFromText) {
        DataPrep.scrubEmailFromText = scrubEmailFromText;
    }
} // End of DataPrep
