import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDetective {
    List<String> names = getNameList();


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
        List<String> piiList = new ArrayList<>();
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
        if(sb.length()>0) {
            sb.insert(0, "\"");
            sb.insert(s.length()+1, "\"");
        }

        return sb.toString();
    }

    public String removeNumbers(String s) {
        return s.replaceAll("[0-9]", "*");
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

    public String isName(String s) {
        String clean = s;
        StringBuilder compiledLine = new StringBuilder();

        String[] lineSplit = clean.split(" ");

        for(int l = 0; l < lineSplit.length; l++) {
            for (int n = 0; n < names.size(); n++) {
                if(lineSplit[l].toLowerCase().matches(names.get(n).toLowerCase())) {
                    lineSplit[l] = "*";
                    compiledLine.append( lineSplit[l]);
                }
            }
            compiledLine.append(lineSplit[l]);
            compiledLine.append(" ");
        }
        return compiledLine.toString();
    }

    public List<String> getNameList() {
        List<String> nameList = new ArrayList<>();

        try (FileInputStream fs = new FileInputStream(DataPrep.nameFile)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String line;

            while ((line = br.readLine()) != null) {
                nameList.add(line);
            }
            fs.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nameList;
    }

} // End of DataDetective
