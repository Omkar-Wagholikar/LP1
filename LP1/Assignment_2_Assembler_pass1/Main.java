
package LP1.Assignment_2_Assembler_pass1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void print(Object param){
        System.out.println(param);
    }
    public static void main(String [] args){
        List<String> commands = FileHandler.readProgram();
        Map<String, List<String>> map = FileHandler.createOptab();
        Map<String, Integer> SymTab = new HashMap<>();

        StringTokenizer token = new StringTokenizer(commands.get(0));
        int lc=0;
        if(token.countTokens() > 1){
            token.nextToken();
            lc = Integer.parseInt(token.nextToken());
        }
        print(map.get("EQU").get(0).equals("AD"));
        for(String s: commands){
            token = new StringTokenizer(s); 
            
            String cmd = token.nextToken();
            if(map.containsKey(cmd)){
                
                if(map.get(cmd).get(0).equals("AD")){

                }
                else {
                    lc++;
                }
            }
            else {
                SymTab.put(cmd, lc);
                lc++;
            }
        }
        print(SymTab.toString());
    }
}
