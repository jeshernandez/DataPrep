import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class DataPrepSettings {
    private final String fileName = "settings.xml";

    public void setSettings() {
        try {
            File tempFile = new File(fileName);
            boolean exists = tempFile.exists();

            if(!exists) {
                Properties saveProps = new Properties();
                saveProps.setProperty("output_delimiter", "\t");
                saveProps.storeToXML(new FileOutputStream(fileName), "");
            }
        } catch (IOException e) {
            DataPrep.LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    public String getOutputDelimiter() {
        try {
            Properties loadProps = new Properties();
            loadProps.loadFromXML(new FileInputStream(fileName));
            return  loadProps.getProperty("output_delimiter");
        } catch (IOException e) {
            return "|";
        }
    }



} // End of DataPrepSettings
