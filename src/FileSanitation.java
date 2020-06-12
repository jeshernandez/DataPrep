public class FileSanitation {
    /**
     * Sanitizes FileName and removes special chars.
     * NOTE: The string MUST NOT HAVE THE FULL PATH.
     * Full paths such as C://DATA will be sanitized to c//data
     * @param fileName un-sanitized
     * @return sanitized file name.
     */
    public static String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[-+$^:,!@#%&*()+]","")
                .trim()
                .replaceAll(" ", "_")
                .toLowerCase();
    }

    /**
     * Sanitizes a roster header.
     * Ex: "First_name $" -> "first name"
     * @param str un-sanitized string
     * @return sanitized string
     */
    public static String sanitizeHeaders(String str) {
        return str.replaceAll("#", " number")
                .replaceAll("1", " one")
                .replaceAll("2", " two")
                .replaceAll("3", " three")
                .replaceAll("[-+$^:,!@%&*()+]","")
                .replace("_", " ")
                .toLowerCase()
                .trim()
                .replaceAll("( )+", " ");//Remove extra spaces
    }

}