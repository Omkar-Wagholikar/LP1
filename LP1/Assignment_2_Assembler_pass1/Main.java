
package Assignment_2_Assembler_pass1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String [] args){
        List<String> commands = FileHandler.readProgram();
        List<String> ops =FileHandler.readOptab();

        Map<String, List<String>> map = new HashMap<>();

        StringTokenizer token = new StringTokenizer(ops.get(0));
        System.out.println(ops.get(0)+ " -> "+token.nextToken()+ " "+ token.nextToken());
    }
}
