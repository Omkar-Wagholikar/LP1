// package LP1.Assignment_2_Assembler_pass1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FileHandler {
    FileHandler() {
        System.out.println("File handler created");
    }

    public static List<String> readProgram(int caseNumber) {
        List<String> commands = new ArrayList<String>();
        try {
            File file = new File("LP1/Assignment_2_Assembler_pass1/Assignment_2_Assembler_pass1/test-cases/testCase" + caseNumber + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
                commands.add(st);
            br.close();
            return commands;
        } catch (Exception e) {
            System.out.println("File not opening");
        }
        return commands;
    }

    protected static List<String> readOptab() {
        List<String> ops = new ArrayList<String>();
        try {
            File file = new File("LP1/Assignment_2_Assembler_pass1/Assignment_2_Assembler_pass1/OPTAB.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            while ((st = br.readLine()) != null)
                ops.add(st);
            br.close();
            return ops;
        } catch (Exception e) {
            System.out.println("File not opening");
        }
        return ops;
    }

    public static Map<String, List<String>> createOptab() {
        List<String> ops = FileHandler.readOptab();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : ops) {
            StringTokenizer token = new StringTokenizer(s);
            String command = token.nextToken();
            String type = token.nextToken();
            String sno = token.nextToken();
            List<String> inpLst = new ArrayList<String>();
            inpLst.add(type);
            inpLst.add(sno);
            map.put(command, inpLst);
        }
        return map;
    }

    public static void writeOutput(int caseNumber, String code){
     try {
       FileWriter output = new FileWriter("outputs/output" + caseNumber + ".txt", true);
       output.write(code);
       output.close();
     }
     catch (Exception e) {
        System.out.println("Problem in writing into: " + "outputs/output" + caseNumber + ".txt -> " + code);
       e.getStackTrace();
     }
    }
}
