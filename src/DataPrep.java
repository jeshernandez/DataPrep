import java.io.IOException;

public class DataPrep {
    public static void main(String[] args) {

        if(args.length > 2) {
            startProcessing(args[0], args[1], args[2]);
        } else {
            System.out.println("Requires input file, output file, delimiter.");
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
