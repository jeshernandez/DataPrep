import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDetective {

    public boolean isNumber(String s)
    {
        String x = s.stripTrailing().stripLeading();
        try
        {
            Long.parseLong(x);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public String removePII(String s) {
        List<String> piiList = new ArrayList<String>();
        String clean = s;
        piiList.add("ssn");
        piiList.add("number");
        piiList.add("dod");
        piiList.add("dod");
        piiList.add("pvt");
        piiList.add("pv2");
        piiList.add("pfc");
        piiList.add("spc");
        piiList.add("cpl");
        piiList.add("sgt");
        piiList.add("ssg");
        piiList.add("sfc");
        piiList.add("msg");
        piiList.add("1sg");
        piiList.add("sgm");
        piiList.add("csm");
        piiList.add("2lt");
        piiList.add("1lt");
        piiList.add("cpt");
        piiList.add("maj");
        piiList.add("ltc");
        piiList.add("col");
        piiList.add("bg");
        piiList.add("mg");
        piiList.add("ltg");
        piiList.add("gen");
        piiList.add("goa");
        piiList.add("w01");
        piiList.add("cw2");
        piiList.add("cw3");
        piiList.add("cw4");
        piiList.add("cw5");

        for (int i = 0; i < piiList.size(); i++) {
            String tmp = null;
            tmp = clean.toLowerCase().replace(piiList.get(i), "***");
            clean = tmp;
        }

        return clean;
    }

    public String addDQuotes(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(0, "\"");
        sb.insert(s.length()+1, "\"");
        return sb.toString();
    }

    public String removeNumbers(String s) {
        String clean = s.replaceAll("[0-9]", "*");
        return clean;
    }

    public boolean isDate(String s) {
        String[] patterns = {"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:SS"
                , "yyyy-MM-dd kk:mm:ss", "MM/dd/yyyy", "dd-MM-yy", "dd-MM-yyyy"};
        String date = s;
        // 2020-05-20 18:45:51
        boolean check = Arrays.asList(patterns).stream()
                .anyMatch(pattern -> {
                    try {
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                });
        return check;
    }

} // End of DataDetective
