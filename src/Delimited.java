abstract class Delimited implements FileReader {

    public String cleanFileName(String fileName) {
        return FileSanitation.sanitizeFileName(fileName);
    }

    public String cleanHeaders(String keyword) {
        return FileSanitation.sanitizeHeaders(keyword);
    }


    public String getCleanDelimiter(String delimiter) {
        if(delimiter.equals("|")) return "\\|";
        if(delimiter.equals("*")) return "\\*";
        return delimiter;
    }


} // End of Delimited class