
package LP1.Assignment_2_Assembler_pass1;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static String output(String ele, List<String> regs) {
        String soln = "";
        if (isNumeric(ele)) {
            soln += "(C, " + ele + ")\t";

        } else if (regs.contains(ele)) {
            // Register
            soln += "(" + (regs.indexOf(ele) + 1) + ")\t";

        } else {
            // Symtable
            soln += ("__\t");
        }
        return soln;
    }

    public static String analyse(String cmd, StringTokenizer rest, Map<String, List<String>> OpTab, List<String> regs) {
        List<String> progList = new ArrayList<String>();
        rest.asIterator().forEachRemaining(element -> {
            progList.add((String) element);
        });

        String soln = "";
        for (int i = 0; i < progList.size(); i++) {
            soln += output(progList.get(i), regs);
        }

        return OpTab.get(cmd) + "\t" + soln;
    }

    public static void main(String[] args) {
        List<String> commands = FileHandler.readProgram(1);
        Map<String, List<String>> OpTab = FileHandler.createOptab();
        Map<String, Integer> SymTab = new HashMap<>();
        // Map<String, Integer> LitTab = new HashMap<>();
        List<String> regs = new ArrayList<String>() {
            {
                add("AREG");
                add("BREG");
                add("CREG");
                add("DREG");
            }
        };
        StringTokenizer temp = new StringTokenizer("AREG    BREG");
        StringTokenizer t2 = new StringTokenizer("3");

        // print(analyse("ADD", temp, OpTab, regs));
        // analyse("DS", t2, OpTab, regs);

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
                    print(s + "<-");
                    // analyse(cmd, token);
                } else {
                    // analyse(cmd, token, OpTab);
                    lc++;
                    print(s+"\t"+lc+"\t"+analyse(cmd, token, OpTab, regs));
                }
            } else {
                lc++;
                SymTab.put(cmd, lc);
                print(s+"\t"+lc+"\t"+analyse(token.nextToken(), token, OpTab, regs));
            }
        }
        print("\n" + SymTab.toString());
    }
}
