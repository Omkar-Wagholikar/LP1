import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;

class file {
    Vector<Vector<String>> optable = new Vector<Vector<String>>();
    HashMap<String, String> imperative = new HashMap<String, String>();
    HashMap<String, String> assembler = new HashMap<String, String>();
    HashMap<String, String> declaration = new HashMap<String, String>();
    HashMap<String, String> register = new HashMap<String, String>();
    HashMap<String, String> comparison = new HashMap<String, String>();
    HashMap<String, Integer> symbol_table = new HashMap<String, Integer>();
    HashMap<String, Integer> literal_table = new HashMap<String, Integer>();
    HashMap<String, Integer> symbol_index = new HashMap<String, Integer>();
    HashMap<String, Integer> literal_index = new HashMap<String, Integer>();
    HashMap<Integer, Integer> symbol_value = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> literal_value = new HashMap<Integer, Integer>();
    Vector<Integer> poolTable = new Vector<Integer>();

    void initialize_tables() {
        imperative.put("STOP", "00");
        imperative.put("ADD", "01");
        imperative.put("SUB", "02");
        imperative.put("MULT", "03");
        imperative.put("MOVER", "04");
        imperative.put("MOVEM", "05");
        imperative.put("COMP", "06");
        imperative.put("BC", "07");
        imperative.put("DIV", "08");
        imperative.put("READ", "09");
        imperative.put("PRINT", "10");
        assembler.put("START", "01");
        assembler.put("END", "02");
        assembler.put("ORIGIN", "03");
        assembler.put("EQU", "04");
        assembler.put("LTORG", "05");
        declaration.put("DS", "01");
        declaration.put("DC", "02");
        register.put("AREG", "01");
        register.put("BREG", "02");
        register.put("CREG", "03");
        //register.put("ANY", "06");
        comparison.put("EQ", "01");
        comparison.put("LT", "02");
        comparison.put("GT", "03");
        comparison.put("LE", "04");
        comparison.put("GE", "05");
        comparison.put("NE", "06");
        comparison.put("ANY", "07");
    }

    void readFile(int caseNumber) {
        File myfile = new File("test-cases/testCase"+caseNumber+".txt");
        String[] mnemonic_opcode = { "STOP", "ADD", "SUB", "MULT", "MOVER", "MOVEM", "COMP", "BC", "DIV", "READ",
                "PRINT", "START", "END", "ORIGIN", "EQU", "LTORG", "DS",
                "DC" };
        try {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                line.trim();
                Vector<String> v = new Vector<String>();
                String[] arrofStr = line.split(" ", -2);
                for (String a : arrofStr) {
                    v.addElement(a.trim());
                }
                Vector<String> v1 = new Vector<String>();
                if (v.size() == 1) {
                    v1.add(" ");
                    v1.add(v.elementAt(0));
                    v1.add(" ");
                    v1.add(" ");
                } else if (v.size() == 2) {
                    if(v.elementAt(1).equals("STOP")){
                        v1.add(v.elementAt(0));
                        v1.add(v.elementAt(1));
                        v1.add(" ");
                        v1.add(" ");
                    }
                    else{
                        v1.add(" ");
                        v1.add(v.elementAt(0));
                        v1.add(v.elementAt(1));
                        v1.add(" ");
                    }
                } else if (v.size() == 3) {
                    int flag = 0;
                    for (int i = 0; i < mnemonic_opcode.length; i++) {
                        String s = v.elementAt(0);
                        String s1 = mnemonic_opcode[i];
                        if (s1.equals(s)) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1) {
                        // System.out.println("hi");
                        v1.add(" ");
                        v1.add(v.elementAt(0));
                        v1.add(v.elementAt(1));
                        v1.add(v.elementAt(2));
                    } else {
                        // System.out.println("hi");
                        v1.add(v.elementAt(0));
                        v1.add(v.elementAt(1));
                        v1.add(v.elementAt(2));
                        v1.add(" ");
                    }
                } else if (v.size() == 4) {
                    v1.add(v.elementAt(0));
                    v1.add(v.elementAt(1));
                    v1.add(v.elementAt(2));
                    v1.add(v.elementAt(3));
                }
                optable.add(v1);
            }
            sc.close();

            // for (int i = 0; i < optable.size(); i++) {
            //     Vector<String> x = optable.elementAt(i);
            //      System.out.print(i+":");

            //     for (int j = 0; j < 4; j++) {
            //          System.out.print(x.elementAt(j)+" ");
            //     }
            //      System.out.println();
            // }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void output(int caseNumber) {
        initialize_tables();
        readFile(caseNumber);
        int symbol = -1;
        int literal = -1;
        int literal_count = 0;
        char lit='a';
        int ltorg=0;
        HashMap<Integer,String> ltorg_table=new HashMap<Integer,String>();
        try {
            poolTable.add(0);
            FileWriter fileWriter = new FileWriter("output11.txt");
            for (int i = 0; i < optable.size(); i++) {
                Vector<String> x = optable.elementAt(i);
                //System.out.println(i);
                if (x.elementAt(1).equals("START")) {
                    String a = x.elementAt(2);
                    literal_count = Integer.parseInt(a);
                    fileWriter.write("(AD,01) (C," + literal_count + ")\n");
                    continue;
                } else if (x.elementAt(1).equals("END")) {
                    fileWriter.write("(AD,02) ");
                    
                    if(x.elementAt(2).equals(" ")==false){
                        if (symbol_index.containsKey(x.elementAt(2)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(2), symbol);
                        }
                        fileWriter.write("(S,0" + symbol_index.get(x.elementAt(2)) + ") ");
                    }
                    fileWriter.write("\n");
                    if(ltorg>0){
                        //System.out.println("inside");
                        for(int j=0;j<ltorg;j++){
                            String s=ltorg_table.get(j);
                            String s1="";
                            for(int k=2;k<s.length()-2;k++){
                                s1+=s.charAt(k);
                            }
                            literal_table.put(ltorg_table.get(j),literal_count+j);
                            fileWriter.write("(DL,01) (C,"+Integer.parseInt(s1)+")"+"\n");                
                            ltorg_table.remove(s);
                        }
                    }
                    continue;
                }
                else if (x.elementAt(1).equals("ORIGIN")) {
                    
                    fileWriter.write("(AD,03) ");
                    String s=(String)(x.elementAt(2));
                    int j=0;
                    String s1="";
                    // String s1=""+s.charAt(0);
                    // System.out.println(s1);
                    for(int k=0;k<s.length();k++){
                        if(s.charAt(k)=='+' || s.charAt(k)=='-'){
                            j=k;
                        }
                    }
                    for(int k=0;k<j;k++){
                        s1+=s.charAt(k);
                    }
                    //System.out.println(s1);
                    literal_count=(Integer)symbol_table.get(s1);
                    //literal_count=(Integer)symbol_table.get(s1);
                    //System.out.println(literal_count);
                    String s2="";
                    String s3="";
                    for(int k=j;k<s.length();k++){
                        s2+=s.charAt(k);
                        if(s.charAt(k)!='+' && s.charAt(k)!='-'){
                            s3+=s.charAt(k);
                        }
                    }
                    if(s2.charAt(0)=='+'){
                        literal_count+=Integer.parseInt(s3);
                    }
                    else{
                        literal_count-=Integer.parseInt(s3);
                    }
                    fileWriter.write("(S,0" + symbol_index.get(s1) + ")"+s2+"\n");
                    continue;
                }
                else if(x.elementAt(1).equals("EQU")){
                    //System.out.println("equ");
                    String s=(String)(x.elementAt(2));
                    int j=0;
                    String s1="";
                    // String s1=""+s.charAt(0);
                    int flag=0;
                    for(int k=0;k<s.length();k++){
                        if(s.charAt(k)=='+' || s.charAt(k)=='-'){
                            j=k;
                            flag=1;
                        }
                    }
                    for(int k=0;k<j;k++){
                        s1+=s.charAt(k);
                    }
                    if(flag==1){
                        String s2="";
                        String s3="";
                        for(int k=j;k<s.length();k++){
                            s2+=s.charAt(k);
                            if(s.charAt(k)!='+' && s.charAt(k)!='+'){
                                s3+=s.charAt(k);
                            }
                        }
                        // String s1=""+s.charAt(0);
                        // String s2=""+s.charAt(2);
                        // int a= Integer.parseInt(s2);
                        int a=Integer.parseInt(s3);
                        if(s.charAt(1)=='+'){
                            if (symbol_index.containsKey(x.elementAt(0)) == false) {
                                symbol += 1;
                                symbol_index.put(x.elementAt(0), symbol);
                                symbol_table.put(x.elementAt(0), symbol_table.get(s1)+a);
                            } else {
                                symbol_table.put(x.elementAt(0), symbol_table.get(s1)+a);
                            }
                        }
                        else{
                            if (symbol_index.containsKey(x.elementAt(0)) == false) {
                                symbol += 1;
                                symbol_index.put(x.elementAt(0), symbol);
                                symbol_table.put(x.elementAt(0), symbol_table.get(s1)-a);
                            } else {
                                symbol_table.put(x.elementAt(0), symbol_table.get(s1)-a);
                            }
                        }
                    }
                    else{
                        if (symbol_index.containsKey(x.elementAt(0)) == false) {
                                symbol += 1;
                                symbol_index.put(x.elementAt(0), symbol);
                                symbol_table.put(x.elementAt(0), symbol_table.get(x.elementAt(2)));
                            } else {
                                symbol_table.put(x.elementAt(0), symbol_table.get(x.elementAt(2)));
                            } 
                    }
                    continue;
                }
                else if(x.elementAt(1).equals("LTORG")){
                        for(int j=0;j<ltorg;j++){
                            String s=ltorg_table.get(j);
                            //System.out.println(s);
                            String s1="";
                            for(int k=2;k<s.length()-2;k++){
                                s1+=s.charAt(k);
                            }
                            literal_table.put(ltorg_table.get(j),literal_count+j);
                            fileWriter.write(literal_count+" (DL,01) (C,"+Integer.parseInt(s1)+")"+"\n");
                            ltorg_table.remove(s);
                        }
                        poolTable.add(literal+1);
                        literal_count+=ltorg;
                        ltorg=0;
                        continue;
                }
                else if (imperative.containsKey(x.elementAt(1))) {
                    fileWriter.write(literal_count+" (IS," + imperative.get(x.elementAt(1)) + ") ");
                    if (register.containsKey(x.elementAt(2))) {
                        fileWriter.write("(" + register.get(x.elementAt(2)) + ") ");
                    } else if (comparison.containsKey(x.elementAt(2))) {
                        // System.out.println("hi2");
                        fileWriter.write("(" + comparison.get(x.elementAt(2)) + ") ");
                    } else if (x.elementAt(1).equals("READ")) {
                        if (symbol_index.containsKey(x.elementAt(2)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(2), symbol);
                        }
                        literal_count+=1;
                        fileWriter.write("(S,0" + symbol_index.get(x.elementAt(2)) + ") \n");
                        continue;
                    } else if (x.elementAt(1).equals("PRINT")) {
                        if (symbol_index.containsKey(x.elementAt(2)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(2), symbol);
                        }
                        if (symbol_index.containsKey(x.elementAt(0)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(0), symbol);
                            symbol_table.put(x.elementAt(0), literal_count);
                        } else {
                            symbol_table.put(x.elementAt(0), literal_count);
                        }
                        literal_count+=1;
                        fileWriter.write("(S,0" + symbol_index.get(x.elementAt(2)) + ") \n");
                        continue;
                    }
                    if (x.elementAt(0).equals(" ") == false) {
                        if (symbol_index.containsKey(x.elementAt(0)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(0), symbol);
                            symbol_table.put(x.elementAt(0), literal_count);
                        } else {
                            symbol_table.put(x.elementAt(0), literal_count);
                        }
                    }
                    if (x.elementAt(3).equals(" ") == false) {
                        String s = x.elementAt(3);
                        // System.out.println(s);
                        if (s.startsWith("=")) {
                            s+=lit;
                            lit+=1;
                            //System.out.println(s);
                            if (literal_index.containsKey(x.elementAt(3)) == false) {
                                literal += 1;
                                //System.out.println(s);
                                literal_index.put(s, literal);
                                //literal_table.put(s, literal_count);
                                ltorg_table.put(ltorg, s);
                                ltorg+=1;
                            } else {
                                System.out.println(s);
                                ltorg_table.put(ltorg, s);
                                ltorg+=1;
                                //literal_table.put(s, literal_count);
                            }
                            fileWriter.write("(L,0" + literal_index.get(s) + ") ");
                        } else {
                            if (symbol_index.containsKey(x.elementAt(3)) == false) {
                                symbol += 1;
                                symbol_index.put(x.elementAt(3), symbol);
                            }

                            fileWriter.write("(S,0" + symbol_index.get(s) + ") ");
                        }
                    }
                } else if (x.elementAt(1).equals("DS")) {

                    fileWriter.write(literal_count+" (DL," + declaration.get(x.elementAt(1)) + ") ");
                    if (x.elementAt(0).equals(" ") == false) {
                        if (symbol_index.containsKey(x.elementAt(0)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(0), symbol);
                            symbol_table.put(x.elementAt(0), literal_count);
                        } else {
                            symbol_table.put(x.elementAt(0), literal_count);
                        }
                    }
                    String a = x.elementAt(2);
                    //System.out.println("DS "+a);
                    literal_count+= Integer.parseInt(a);
                    fileWriter.write("(C," + x.elementAt(2) + ")");
                    fileWriter.write("\n");
                    continue;
                } else if (x.elementAt(1).equals("DC")) {
                    fileWriter.write(literal_count+" (DL," + declaration.get(x.elementAt(1)) + ") ");
                    if (x.elementAt(0).equals(" ") == false) {
                        if (symbol_index.containsKey(x.elementAt(0)) == false) {
                            symbol += 1;
                            symbol_index.put(x.elementAt(0), symbol);
                            symbol_table.put(x.elementAt(0), literal_count);
                        } else {
                            symbol_table.put(x.elementAt(0), literal_count);
                        }
                    }
                    String s = x.elementAt(2);
                    fileWriter.write("(C," + s.substring(1, s.length() - 1) + ")");
                }
                fileWriter.write("\n");
                literal_count += 1;
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        // Integer[] arr=new Integer[symbol_table.size()+1];
        Iterator it = symbol_table.entrySet().iterator();
        Iterator it1 = symbol_index.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry mapElement = (Map.Entry) it.next();
            Map.Entry mapElement1 = (Map.Entry) it1.next();
            // System.out.println(mapElement.getKey()+" "+mapElement.getValue());
            // arr[(int) mapElement1.getValue()]=(Integer) mapElement.getValue();
        }
        System.out.println("***************************************************************");
        System.out.println("Symbol Table:");
        for (int i = 0; i < symbol_table.size(); i++) {
            it1 = symbol_index.entrySet().iterator();
            String[] s = null;
            while (it1.hasNext()) {
                Map.Entry mapElement1 = (Map.Entry) it1.next();
                if ((int) mapElement1.getValue() == i) {
                    System.out.println(mapElement1.getKey() + " " + symbol_table.get(mapElement1.getKey()));
                    symbol_value.put((int)mapElement1.getValue(),symbol_table.get(mapElement1.getKey()));
                    break;
                }
            }
        }
        Iterator it2 = literal_table.entrySet().iterator();
        Iterator it3 = literal_index.entrySet().iterator();
        System.out.println("*****************************************************************");
        System.out.println("Literal Table:");
        for (int i = 0; i < literal_table.size(); i++) {
            it3 = literal_index.entrySet().iterator();
            String[] s = null;
            while (it3.hasNext()) {
                Map.Entry mapElement1 = (Map.Entry) it3.next();
                if ((int) mapElement1.getValue() == i) {
                    String s2=(String)mapElement1.getKey();
                    String s1="";
                    for(int j=0;j<s2.length()-1;j++){
                        s1+=s2.charAt(j);
                    }
                    System.out.println(s1 + " " + literal_table.get(mapElement1.getKey()));
                    literal_value.put((int)mapElement1.getValue(),literal_table.get(mapElement1.getKey()));
                    break;
                }
            }
        }
        System.out.println("*****************************************************************");
        System.out.println("POOL TABLE");
        for(int i=0;i<poolTable.size();i++){
            System.out.println(poolTable.get(i));
        }
        //System.out.println(literal_count);
        //System.out.println(symbol_index.get("LAST"));
    }
    void pass2(){
        File myfile = new File("output11.txt");
        try {
            Scanner sc = new Scanner(myfile);
            FileWriter fileWriter = new FileWriter("output12.txt");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                line.trim();
                System.out.println(line);
                Vector<String> v = new Vector<String>();
                String[] arrofStr = line.split(" ", -2);
                for (String a : arrofStr) {
                    v.addElement(a.trim());
                    //System.out.print(a+" ");
                }
                //System.out.println();
                if(v.elementAt(0).equals("(AD,01)") || v.elementAt(0).equals("(AD,02)") || v.elementAt(1).equals("(DL,02)")){
                    //System.out.println("hi");
                    continue;
                }
                else if(v.elementAt(1).equals("(IS,00)")){
                    fileWriter.write(v.elementAt(0)+" "+"00 "+"0"+" 000\n");
                }
                else if(v.size()==3 && v.elementAt(1).equals("(DL,01)")){
                    int x=(int)Integer.parseInt(v.elementAt(2).substring(3, 4));
                    int y=Integer.parseInt(v.elementAt(0))+x;
                    // fileWriter.write(v.elementAt(0)+" "+v.elementAt(1).substring(4, 6)+" 0 "+v.elementAt(0)+"-"+String.valueOf(y)+" \n");
                    fileWriter.write(v.elementAt(0)+" "+v.elementAt(1).substring(4, 6)+" 0 "+String.valueOf(x)+" \n");
                }
                else if(v.size()==4){
                    
                    int x=symbol_value.get((int)Integer.parseInt(v.elementAt(2).substring(4, 5)));
                    fileWriter.write(v.elementAt(0)+" "+v.elementAt(1).substring(4, 6)+" 0 "+String.valueOf(x)+" \n");
                    
                }
                else if(v.size()==5){
                    if(v.elementAt(3).substring(1, 2).equals("S")){
                        int x=symbol_value.get((int)Integer.parseInt(v.elementAt(3).substring(4, 5)));
                        //System.out.println(x);
                        fileWriter.write(v.elementAt(0)+" "+v.elementAt(1).substring(4, 6)+" "+v.elementAt(2).substring(2, 3)+" "+String.valueOf(x)+" \n");
                    }    
                    else{
                        int x=literal_value.get((int)Integer.parseInt(v.elementAt(3).substring(4, 5)));
                        //System.out.println(x);
                        fileWriter.write(v.elementAt(0)+" "+v.elementAt(1).substring(4, 6)+" "+v.elementAt(2).substring(2, 3)+" "+String.valueOf(x)+" \n");
                    }
                }
            }
            fileWriter.close();
        }      
        catch (Exception e) {
            System.out.println(e);
        } 
    }

    
};

public class pass2 {
    public static void print(Object o){
        System.out.println(o.toString());
    }
    public static void main(String[] args) {
        file f = new file();
        //f.readFile();
        Scanner sc = new Scanner(System.in);
        while(true){
            print("Please enter test case number: ");
            int choice = sc.nextInt();
            if(choice > 3){ 
                print("Bye.");
                break;
            }
            f.output(choice);
            f.pass2();
        }
    }
}
