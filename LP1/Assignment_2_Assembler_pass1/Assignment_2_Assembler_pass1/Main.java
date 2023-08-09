
// package LP1.Assignment_2_Assembler_pass1;

import java.util.ArrayList;
// import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void print(Object param) {
        System.out.println(param);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String output(String ele, List<String> regs, Map<String, Integer> SymTab) {
        String soln = "";
        if (isNumeric(ele)) {
            soln += "(C, " + ele + ")\t";

        } else if (regs.contains(ele)) {
            // Register
            soln += "(" + (regs.indexOf(ele) + 1) + ")\t";

        } else {
            // Symtable
            // soln += ("__\t");
            if(SymTab.containsKey(ele)){
                soln += SymTab.toString();
            }
            else if(ele.contains("+") ||  ele.contains("-") || ele.contains("*") ||  ele.contains("/")){
                soln += "<><>";
            }
            else{
                SymTab.put(ele, -1);
                List<String> keys = new ArrayList<>(SymTab.keySet());
                soln += "(S, " + (keys.indexOf(ele) + 1) + ")";
            }
        }
        return soln;
    }

    public static String analyse(String cmd, StringTokenizer rest, Map<String, List<String>> OpTab, List<String> regs, Map<String, Integer> SymTab) {
        List<String> progList = new ArrayList<String>();
        while( rest.hasMoreTokens()){
            progList.add(rest.nextToken());
        }

        String soln = "";
        for (int i = 0; i < progList.size(); i++) {
            soln += output(progList.get(i), regs, SymTab);
        }

        return OpTab.get(cmd) + "\t" + soln;
    }

    public static void main(String[] args) {
        int caseNumber =1;
        List<String> commands = FileHandler.readProgram(caseNumber);
        Map<String, List<String>> OpTab = FileHandler.createOptab();
        Map<String, Integer> SymTab = new LinkedHashMap<>();
        // Map<String, Integer> LitTab = new HashMap<>();
        List<String> regs = new ArrayList<String>() {
            {
                add("AREG");
                add("BREG");
                add("CREG");
                add("DREG");
            }
        };

        StringTokenizer token = new StringTokenizer(commands.get(0));
        int lc = 0;
        if (token.countTokens() > 1) {
            token.nextToken();
            lc = Integer.parseInt(token.nextToken());
        }
        if (lc > 0)
            lc = lc - 1;
        for (String s : commands) {
            token = new StringTokenizer(s);

            String cmd = token.nextToken();
            // print(s);
            if (OpTab.containsKey(cmd)) {
                if (OpTab.get(cmd).get(0).equals("AD")) {
                    // print(s + "\t" + OpTab.get(cmd).toString());
                    print(s+"\t"+lc+"\t"+analyse(cmd, token, OpTab, regs, SymTab));
                } else {
                    lc++;
                    print(s+"\t"+lc+"\t"+analyse(cmd, token, OpTab, regs, SymTab));
                    // FileHandler.writeOutput(caseNumber, s+"\t"+lc+"\t"+analyse(cmd, token, OpTab, regs,SymTab).toString()+"\n");
                }
            } else {
                lc++;
                SymTab.put(cmd, lc);
                print(s+"\t"+lc+"\t"+analyse(token.nextToken(), token, OpTab, regs, SymTab));
            }
        }
        print("\n" + SymTab.toString());
    }
}
