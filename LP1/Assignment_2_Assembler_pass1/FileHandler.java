package Assignment_2_Assembler_pass1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    FileHandler() {
        System.out.println("File handler created");
    }

    public static List<String> readProgram(){
        List<String> commands = new ArrayList<String>();
        try{
            File file = new File("Assignment_2_Assembler_pass1/program.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
            commands.add(st);
            br.close();
            return commands;
        } catch(Exception e){
            System.out.println("File not opening");
        } 
        return commands;
    }

    public static List<String> readOptab(){
        List<String> ops = new ArrayList<String>();
        try{
            File file = new File("Assignment_2_Assembler_pass1/OPTAB.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            
            while ((st = br.readLine()) != null)
            ops.add(st);
            br.close();
            return ops;
        } catch(Exception e){
            System.out.println("File not opening");
        } 
        return ops;
    }

    public static void createOptab(){}
}
